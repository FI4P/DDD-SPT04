package com.springapp.redeAncora.service;

import com.springapp.redeAncora.domain.Product;
import com.springapp.redeAncora.dto.product.ProductRequestDTO;
import com.springapp.redeAncora.dto.product.ProductResponseDTO;
import com.springapp.redeAncora.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    private ProductResponseDTO responseDTO(Product product) {
        return new ProductResponseDTO(product.getId(), product.getName(), product.getBrand(), product.getVehicleCode(), product.getPrice());
    }

    public ProductResponseDTO save(ProductRequestDTO body) {
        Product product = new Product(null, body.name(), body.brand(), body.vehicleCode(),body.price());

        Product savedProduct = productRepository.save(product);

        return responseDTO(savedProduct);
    }

    public ProductResponseDTO update(Long id, ProductRequestDTO body) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        product.setName(body.name() != null ? body.name() : product.getName());
        product.setBrand(body.brand() != null ? body.brand() : product.getBrand());
        product.setVehicleCode(body.vehicleCode() != null ? body.vehicleCode() : product.getVehicleCode());
        product.setPrice(body.price() != null ? body.price() : product.getPrice());

        Product saved = productRepository.save(product);

        return responseDTO(saved);
    }

    public String delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        productRepository.delete(product);

        return "Product deleted";

    }

    public ProductResponseDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        return responseDTO(product);
    }

    public List<ProductResponseDTO> findAll() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::responseDTO).toList();
    }
}

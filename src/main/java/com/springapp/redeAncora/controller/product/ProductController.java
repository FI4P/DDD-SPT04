package com.springapp.redeAncora.controller.product;

import com.springapp.redeAncora.dto.product.ProductRequestDTO;
import com.springapp.redeAncora.dto.product.ProductResponseDTO;
import com.springapp.redeAncora.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll(){
        List<ProductResponseDTO> products = productService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id){
        ProductResponseDTO product = productService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(product);

    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> save(@RequestBody ProductRequestDTO body){
        ProductResponseDTO product = productService.save(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody ProductRequestDTO body){
        ProductResponseDTO product = productService.update(id, body);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        String message = productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}

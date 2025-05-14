package com.springapp.redeAncora.service;


import com.springapp.redeAncora.domain.ItemQuote;
import com.springapp.redeAncora.domain.Product;
import com.springapp.redeAncora.domain.Quote;
import com.springapp.redeAncora.dto.itemQuote.ItemQuoteRequestDTO;
import com.springapp.redeAncora.dto.itemQuote.ItemQuoteResponseDTO;
import com.springapp.redeAncora.repository.ItemQuoteRepository;
import com.springapp.redeAncora.repository.ProductRepository;
import com.springapp.redeAncora.repository.QuoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemQuoteService {
    private final ItemQuoteRepository itemQuoteRepository;
    private final QuoteRepository quoteRepository;
    private final ProductRepository productRepository;

    public ItemQuoteService(ItemQuoteRepository itemQuoteRepository, QuoteRepository quoteRepository, ProductRepository productRepository) {
        this.itemQuoteRepository = itemQuoteRepository;
        this.quoteRepository = quoteRepository;
        this.productRepository = productRepository;
    }

    public ItemQuoteResponseDTO responseDTO(ItemQuote itemQuote) {
        return new ItemQuoteResponseDTO(itemQuote.getId(), itemQuote.getProduct().getName(), itemQuote.getQuantity(), itemQuote.getPrice());
    }

    public ItemQuoteResponseDTO save(ItemQuoteRequestDTO body) {
        Quote quote = quoteRepository.findById(body.quoteId()).orElseThrow(() -> new EntityNotFoundException("Quote not found"));
        Product product = productRepository.findById(body.productId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        ItemQuote itemQuote = new ItemQuote(null, quote, product, body.quantity(), body.price());


        ItemQuote saved = itemQuoteRepository.save(itemQuote);

        return responseDTO(saved);
    }

    public List<ItemQuoteResponseDTO> findByQuoteId(Long quoteId) {
       return  itemQuoteRepository.findByQuoteId(quoteId).stream().map(this::responseDTO).toList();
    }

    public ItemQuoteResponseDTO update(Long id, ItemQuoteRequestDTO body) {
        ItemQuote itemQuote = itemQuoteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Quote not found"));

        itemQuote.setQuantity(body.quantity() != null ? body.quantity() : itemQuote.getQuantity());

        ItemQuote saved = itemQuoteRepository.save(itemQuote);
        return responseDTO(saved);

    }

    public void delete(Long id){
        ItemQuote itemQuote = itemQuoteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item quote not found"));
        itemQuoteRepository.delete(itemQuote);

    }
}

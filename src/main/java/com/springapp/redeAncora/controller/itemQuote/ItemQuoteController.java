package com.springapp.redeAncora.controller.itemQuote;

import com.springapp.redeAncora.domain.ItemQuote;
import com.springapp.redeAncora.dto.itemQuote.ItemQuoteRequestDTO;
import com.springapp.redeAncora.dto.itemQuote.ItemQuoteResponseDTO;
import com.springapp.redeAncora.service.ItemQuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
public class ItemQuoteController {

    private final ItemQuoteService itemQuoteService;

    public ItemQuoteController(ItemQuoteService itemQuoteService) {
        this.itemQuoteService = itemQuoteService;
    }

    @PostMapping
    public ResponseEntity<ItemQuoteResponseDTO> createItemQuote(@RequestBody ItemQuoteRequestDTO body) {
        ItemQuoteResponseDTO item = itemQuoteService.save(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(item);

    }

    @GetMapping("/{quoteId}")
    public ResponseEntity<List<ItemQuoteResponseDTO>> findByQuote(@PathVariable Long quoteId) {
        List<ItemQuoteResponseDTO> items = itemQuoteService.findByQuoteId(quoteId);

        return ResponseEntity.status(HttpStatus.OK).body(items);
    }
}

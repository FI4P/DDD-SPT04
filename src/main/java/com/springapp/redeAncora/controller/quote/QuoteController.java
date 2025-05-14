package com.springapp.redeAncora.controller.quote;

import com.springapp.redeAncora.dto.quote.QuoteRequestDTO;
import com.springapp.redeAncora.dto.quote.QuoteResponseDTO;
import com.springapp.redeAncora.service.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quote")
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteResponseDTO> findById(@PathVariable("id") Long id) {
        QuoteResponseDTO quote = quoteService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(quote);
    }

    @GetMapping()
    public ResponseEntity<List<QuoteResponseDTO>> findAll() {
        List<QuoteResponseDTO> quote = quoteService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(quote);
    }

    @PostMapping
    public ResponseEntity<QuoteResponseDTO> save(@RequestBody QuoteRequestDTO body) {
        QuoteResponseDTO quote = quoteService.save(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(quote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuoteResponseDTO> update(@PathVariable Long id, @RequestBody QuoteRequestDTO body) {
        QuoteResponseDTO quote = quoteService.update(id, body);

        return ResponseEntity.status(HttpStatus.OK).body(quote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        quoteService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

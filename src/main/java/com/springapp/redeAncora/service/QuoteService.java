package com.springapp.redeAncora.service;

import com.springapp.redeAncora.domain.Quote;
import com.springapp.redeAncora.domain.User;
import com.springapp.redeAncora.dto.quote.QuoteRequestDTO;
import com.springapp.redeAncora.dto.quote.QuoteResponseDTO;
import com.springapp.redeAncora.repository.QuoteRepository;
import com.springapp.redeAncora.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;


    public QuoteService(QuoteRepository quoteRepository, UserRepository userRepository) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
    }
    public QuoteResponseDTO responseDTO(Quote quote) {
        return new QuoteResponseDTO(quote.getId(), quote.getUser().getId(),quote.getStatus(), quote.getCreatedAt());
    }


    public List<QuoteResponseDTO> findAll(){
        return quoteRepository.findAll().stream().map(this::responseDTO).toList();
    }

    public QuoteResponseDTO findById(Long id) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Quote not found"));

        return responseDTO(quote);
    }

    public QuoteResponseDTO save(QuoteRequestDTO body) {
        User user = userRepository.findById(body.userId()).orElseThrow(() -> new EntityNotFoundException("User not found"));

        Quote quote = new Quote(null, user, body.date(), body.status());

        Quote saved = quoteRepository.save(quote);

        return responseDTO(saved);
    }

    public QuoteResponseDTO update(Long id, QuoteRequestDTO body) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Quote not found"));

        if(body.userId() != null){
            User user = userRepository.findById(body.userId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
            quote.setUser(user);
        }

        quote.setStatus(body.status() != null ? body.status() : quote.getStatus());
        Quote saved = quoteRepository.save(quote);
        return responseDTO(saved);
    }

    public void delete(Long id) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Quote not found"));

        quoteRepository.delete(quote);

    }
}

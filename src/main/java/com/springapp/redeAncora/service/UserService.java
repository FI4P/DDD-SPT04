package com.springapp.redeAncora.service;


import com.springapp.redeAncora.domain.User;
import com.springapp.redeAncora.dto.user.UserRequestDTO;
import com.springapp.redeAncora.dto.user.UserResponseDTO;
import com.springapp.redeAncora.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private UserResponseDTO responseDto(User user){
        return new UserResponseDTO(user.getId(),user.getName(), user.getEmail(), user.getRole());
    }

    public UserResponseDTO save(UserRequestDTO body){
        User user = new User(null, body.name(), body.email(), body.password(), body.role());
        User savedUser = userRepository.save(user);
        return responseDto(savedUser);
    }

    public UserResponseDTO update(Long id, UserRequestDTO body){
        User user = userRepository.findById(id).orElse(null);

        if(user == null){
            return null;
        }

        user.setName(body.name() != null ? body.name() : user.getName());
        user.setRole(body.role() != null ? body.role() : user.getRole());
        user.setEmail(body.email() != null ? body.email() : user.getEmail());
        user.setPassword(body.password() != null ? body.password() : user.getPassword());


        User saved = userRepository.save(user);

        return responseDto(saved);

    }

    public UserResponseDTO findById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        return responseDto(user);

    }

    public List<UserResponseDTO> findAll(){
        List<User> users = userRepository.findAll();

        return users.stream().map(this::responseDto).toList();
    }

    public void delete(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        userRepository.delete(user);

    }
}

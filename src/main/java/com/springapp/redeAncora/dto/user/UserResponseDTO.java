package com.springapp.redeAncora.dto.user;

import com.springapp.redeAncora.domain.User;

public record UserResponseDTO(Long id, String name, String email, User.Role role) {}

package com.springapp.redeAncora.dto.user;

import com.springapp.redeAncora.domain.User;

public record UserRequestDTO( String name, String email, String password, User.Role role) {}

package com.example.cashsplash.services.user;

import com.example.cashsplash.enums.UserType;
import com.example.cashsplash.models.User;

public record UserResponseDTO(Long id, String name, String email, String password, UserType userType) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getUserType());
    }
}


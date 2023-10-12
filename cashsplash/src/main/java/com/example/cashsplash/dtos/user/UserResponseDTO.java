package com.example.cashsplash.dtos;

import com.example.cashsplash.enums.UserType;
import com.example.cashsplash.models.User;

import java.util.UUID;

public record UserResponseDTO(Long id, UUID uuid, String name, String email, String password, UserType userType) {
    public UserResponseDTO(User user) {
        this(user.getId(), user.getUuid(), user.getName(), user.getEmail(), user.getPassword(), user.getUserType());
    }
}


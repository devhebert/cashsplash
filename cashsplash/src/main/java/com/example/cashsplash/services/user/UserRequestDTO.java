package com.example.cashsplash.services.user;

import com.example.cashsplash.enums.UserType;

public record UserRequestDTO(String name, String email, String password, UserType userType) {}
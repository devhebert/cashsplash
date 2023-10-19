package com.example.cashsplash.dtos.user;

import com.example.cashsplash.enums.UserType;
import com.example.cashsplash.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private UUID uuid;
    private String name;
    private String email;
    private String password;
    private UserType userType;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.uuid = user.getUuid();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userType = user.getUserType();
    }
}
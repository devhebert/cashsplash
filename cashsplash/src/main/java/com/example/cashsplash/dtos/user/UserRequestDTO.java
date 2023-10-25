package com.example.cashsplash.dtos.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequestDTO {
    private String username;
    private String email;
    private String password;
}
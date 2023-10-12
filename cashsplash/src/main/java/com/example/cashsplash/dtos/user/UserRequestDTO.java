package com.example.cashsplash.dtos.user;

import com.example.cashsplash.enums.UserType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private UserType userType;
}
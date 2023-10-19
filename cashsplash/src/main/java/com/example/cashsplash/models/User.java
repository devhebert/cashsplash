package com.example.cashsplash.models;

import com.example.cashsplash.enums.UserType;
import jakarta.persistence.*;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    @Email
    private String email;
    private String password;
    private UserType userType;
}




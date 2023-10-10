package com.example.cashsplash.models;

import com.example.cashsplash.enums.UserType;
import com.example.cashsplash.services.user.UserRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserType userType;

    public User(UserRequestDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.userType = data.userType();
    }
}

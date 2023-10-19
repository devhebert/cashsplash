package com.example.cashsplash.models;

import com.example.cashsplash.dtos.customer.CustomerRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String name;
    @Column(unique = true)
    private String cpfCnpj;
    @Embedded
    private Address address;
}
package com.example.cashsplash.dtos.customer;

import com.example.cashsplash.models.Address;
import com.example.cashsplash.models.Customer;

import java.util.UUID;

public record CustomerResponseDTO(Long id, UUID uuid, String name, String cpfCnpj, Address address) {
    public CustomerResponseDTO(Customer customer) {

        this(customer.getId(), customer.getUuid(), customer.getName(), customer.getCpfCnpj(), customer.getAddress());
    }
}

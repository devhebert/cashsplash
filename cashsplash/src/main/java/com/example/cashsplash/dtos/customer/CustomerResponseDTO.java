package com.example.cashsplash.dtos.customer;

import com.example.cashsplash.models.Address;
import com.example.cashsplash.models.Customer;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {
    private Long id;
    private UUID uuid;
    private String name;
    private String cpfCnpj;
    private Address address;

    public CustomerResponseDTO(Customer customer) {
        this.id = customer.getId();
        this.uuid = customer.getUuid();
        this.name = customer.getName();
        this.cpfCnpj = customer.getCpfCnpj();
        this.address = customer.getAddress();
    }
}

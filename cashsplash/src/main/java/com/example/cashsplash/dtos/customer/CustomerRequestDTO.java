package com.example.cashsplash.dtos.customer;

import com.example.cashsplash.models.Address;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerRequestDTO {
    private String name;
    private String cpfCnpj;
    private Address address;
}

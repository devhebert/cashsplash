package com.example.cashsplash.converters;

import com.example.cashsplash.common.Converter;
import com.example.cashsplash.dtos.customer.CustomerRequestDTO;
import com.example.cashsplash.dtos.customer.CustomerResponseDTO;
import com.example.cashsplash.models.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter implements Converter<Customer, CustomerRequestDTO, CustomerResponseDTO> {
    private final ModelMapper modelMapper;

    public CustomerConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Customer requestDTOToEntity(CustomerRequestDTO request) {
        return this.modelMapper.map(request, Customer.class);
    }

    public Customer responseDTOToEntity(CustomerResponseDTO response) {
        return this.modelMapper.map(response, Customer.class);
    }

    public CustomerRequestDTO entityToRequestDTO(Customer customer) {
        return this.modelMapper.map(customer, CustomerRequestDTO.class);
    }

    public CustomerResponseDTO entityToResponseDTO(Customer customer) {
        return this.modelMapper.map(customer, CustomerResponseDTO.class);
    }
}

package com.example.cashsplash.controllers;

import com.example.cashsplash.converters.CustomerConverter;
import com.example.cashsplash.dtos.customer.CustomerResponseDTO;
import com.example.cashsplash.models.Customer;
import com.example.cashsplash.repositories.CustomerRepository;
import com.example.cashsplash.dtos.customer.CustomerRequestDTO;
import com.example.cashsplash.services.customer.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return this.customerService.findAll();
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerRequestDTO data) {
        Customer customerEntity = this.customerConverter.requestDTOToEntity(data);
        Optional<Customer> savedCustomer = this.customerService.save(customerEntity);

        if (savedCustomer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        CustomerResponseDTO responseDTO = this.customerConverter.entityToResponseDTO(savedCustomer.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable UUID uuid, @RequestBody @Valid CustomerRequestDTO data) {
        Customer customerEntity = this.customerConverter.requestDTOToEntity(data);
        Optional<Customer> updatedCustomer = customerService.update(uuid, customerEntity);

        if (updatedCustomer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        CustomerResponseDTO responseDTO = this.customerConverter.entityToResponseDTO(updatedCustomer.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDTO);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<CustomerResponseDTO> deleteCustomer(@PathVariable UUID uuid) {
        boolean deleted = customerService.delete(uuid);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}


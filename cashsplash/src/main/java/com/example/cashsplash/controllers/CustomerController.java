package com.example.cashsplash.controllers;

import com.example.cashsplash.converters.CustomerConverter;
import com.example.cashsplash.models.Customer;
import com.example.cashsplash.repositories.CustomerRepository;
import com.example.cashsplash.dtos.customer.CustomerRequestDTO;
import com.example.cashsplash.services.customer.CustomerService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final CustomerService customerService;

    public CustomerController(CustomerRepository customerRepository, CustomerConverter customerConverter, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
        this.customerService = customerService;
    }


    @GetMapping //GET "http://localhost:8080/customers/"
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @PostMapping //POST "http://localhost:8080/customers/"
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRequestDTO data) {
        Customer customerEntity = this.customerConverter.requestDTOToEntity(data);
        boolean created = this.customerService.saveCustomer(customerEntity);
        if (!created) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao cadastrar cliente.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso!");
    }

    @PutMapping("/{uuid}") //PUT "http://localhost:8080/customers/6a669eb6-fc41-49f0-b61b-9240ef34205f"
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> updateCustomer(@PathVariable UUID uuid, @RequestBody @Valid CustomerRequestDTO data) {
        Customer customerEntity = this.customerConverter.requestDTOToEntity(data);
        boolean updated = customerService.updateCustomer(uuid, customerEntity);
        if (!updated) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao atualizar usuário.");
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário atualizado com sucesso!");
    }

    @DeleteMapping("/{uuid}") //DELETE "http://localhost:8080/customers/6a669eb6-fc41-49f0-b61b-9240ef34205f"
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteCustomer(@PathVariable UUID uuid) {
        boolean deleted = customerService.deleteCustomer(uuid);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao excluir usuário.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário excluído com sucesso!");
    }
}


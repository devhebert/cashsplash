package com.example.cashsplash.services.customer;

import com.example.cashsplash.feign.FeignZipCode;
import com.example.cashsplash.models.Address;
import com.example.cashsplash.models.Customer;
import com.example.cashsplash.repositories.CustomerRepository;
import com.example.cashsplash.dtos.viacep.ViaCepResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FeignZipCode feignZipCode;

    public CustomerService(CustomerRepository customerRepository, FeignZipCode feignZipCode) {
        this.customerRepository = customerRepository;
        this.feignZipCode = feignZipCode;
    }

    public Optional<Customer> saveCustomer(Customer data) {
        Optional<Customer> existingCustomer = customerRepository.findByUuid(data.getUuid());
        if (existingCustomer.isPresent()) {
            return Optional.empty();
        }

        try {
            if (data.getAddress() != null && data.getAddress().getCep() != null) {
                ViaCepResponseDTO response = feignZipCode.fetchCep(data.getAddress().getCep());
                data.getAddress().setLogradouro(response.logradouro());
                data.getAddress().setComplemento(response.complemento());
                data.getAddress().setBairro(response.bairro());
                data.getAddress().setLocalidade(response.localidade());
                data.getAddress().setUf(response.uf());
            }
        } catch (Exception e) {
            data.setAddress(null);
        }

        data.setUuid(UUID.randomUUID());
        Customer savedCustomer = customerRepository.save(data);
        return Optional.of(savedCustomer);
    }

    public Optional<Customer> updateCustomer(UUID uuid, Customer data) {
        Optional<Customer> existingCustomer = customerRepository.findByUuid(uuid);
        if (existingCustomer.isEmpty()) {
            return Optional.empty();
        }

        Customer customer = existingCustomer.get();
        customer.setName(data.getName() != null ? data.getName() : customer.getName());
        customer.setCpfCnpj(data.getCpfCnpj() != null ? data.getCpfCnpj() : customer.getCpfCnpj());

        Address newAddress = data.getAddress();
        Address existingAddress = customer.getAddress();

        existingAddress.setCep(newAddress.getCep() != null ? newAddress.getCep() : existingAddress.getCep());
        existingAddress.setLogradouro(newAddress.getLogradouro() != null ? newAddress.getLogradouro() : existingAddress.getLogradouro());
        existingAddress.setNumero(newAddress.getNumero() != null ? newAddress.getNumero() : existingAddress.getNumero());
        existingAddress.setComplemento(newAddress.getComplemento() != null ? newAddress.getComplemento() : existingAddress.getComplemento());
        existingAddress.setBairro(newAddress.getBairro() != null ? newAddress.getBairro() : existingAddress.getBairro());
        existingAddress.setLocalidade(newAddress.getLocalidade() != null ? newAddress.getLocalidade() : existingAddress.getLocalidade());
        existingAddress.setUf(newAddress.getUf() != null ? newAddress.getUf() : existingAddress.getUf());

        Customer updatedCustomer = this.customerRepository.save(customer);
        return Optional.of(updatedCustomer);
    }

    public boolean deleteCustomer(UUID uuid) {
        Optional<Customer> existingUser = this.customerRepository.findByUuid(uuid);

        if (existingUser.isEmpty()) {
            return false;
        }

        customerRepository.delete(existingUser.get());
        return true;
    }
}

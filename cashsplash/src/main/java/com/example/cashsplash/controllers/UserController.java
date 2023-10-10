package com.example.cashsplash.controllers;

import com.example.cashsplash.repositories.UserRepository;
import com.example.cashsplash.services.user.UserRequestDTO;
import com.example.cashsplash.services.user.UserResponseDTO;
import com.example.cashsplash.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping  //POST "http://localhost:8080/users/"
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRequestDTO data) {
        boolean create = userService.createUser(data);
        if (!create) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já cadastrado.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping //GET "http://localhost:8080/users/"
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDTO::new).toList();
    }

    @PutMapping("/{id}") //PUT "http://localhost:8080/users/1"
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody @Valid UserRequestDTO data) {
        boolean updated = userService.updateUser(id, data);
        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}") //DELETE "http://localhost:8080/users/1"
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        boolean deleted = userService.deleteUser(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}

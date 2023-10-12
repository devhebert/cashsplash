package com.example.cashsplash.controllers;

import com.example.cashsplash.converters.UserConverter;
import com.example.cashsplash.models.User;
import com.example.cashsplash.repositories.UserRepository;
import com.example.cashsplash.dtos.user.UserRequestDTO;
import com.example.cashsplash.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserConverter userConverter, UserService userService) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.userService = userService;
    }

    @GetMapping //GET "http://localhost:8080/users/"
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @PostMapping  //POST "http://localhost:8080/users/"
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRequestDTO data) {
        User userEntity = this.userConverter.requestDTOToEntity(data);
        boolean created = this.userService.saveUser(userEntity);
        if (!created) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao cadastrar usuário.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }

    @PutMapping("/{uuid}") //PUT "http://localhost:8080/users/6a669eb6-fc41-49f0-b61b-9240ef34205f"
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> updateUser(@PathVariable UUID uuid, @RequestBody @Valid UserRequestDTO data) {
        User userEntity = this.userConverter.requestDTOToEntity(data);
        boolean updated = userService.updateUser(uuid, userEntity);
        if (!updated) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao atualizar usuário.");
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário atualizado com sucesso!");
    }

    @DeleteMapping("/{uuid}") //DELETE "http://localhost:8080/users/6a669eb6-fc41-49f0-b61b-9240ef34205f"
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteUser(@PathVariable UUID uuid) {
        boolean deleted = userService.deleteUser(uuid);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao excluir usuário.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário excluído com sucesso!");
    }
}

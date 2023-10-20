package com.example.cashsplash.controllers;

import com.example.cashsplash.converters.UserConverter;
import com.example.cashsplash.dtos.user.UserResponseDTO;
import com.example.cashsplash.models.User;
import com.example.cashsplash.repositories.UserRepository;
import com.example.cashsplash.dtos.user.UserRequestDTO;
import com.example.cashsplash.services.user.UserService;
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
@RequestMapping("users")
public class UserController {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO data) {
        User userEntity = this.userConverter.requestDTOToEntity(data);
        Optional<User> savedUser = this.userService.save(userEntity);

        if (savedUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        UserResponseDTO responseDTO = this.userConverter.entityToResponseDTO(savedUser.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID uuid, @RequestBody @Valid UserRequestDTO data) {
        User userEntity = this.userConverter.requestDTOToEntity(data);
        Optional<User> updatedUser = userService.update(uuid, userEntity);

        if (updatedUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        UserResponseDTO responseDTO = this.userConverter.entityToResponseDTO(updatedUser.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDTO);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable UUID uuid) {
        boolean deleted = userService.delete(uuid);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}

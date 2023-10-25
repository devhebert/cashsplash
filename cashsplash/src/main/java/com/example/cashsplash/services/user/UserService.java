package com.example.cashsplash.services.user;

import com.example.cashsplash.common.CrudService;
import com.example.cashsplash.models.User;
import com.example.cashsplash.repositories.UserRepository;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements CrudService<User> {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public Optional<User> save(User data) {
        boolean isValidUser = validateUser(data);

        if (!isValidUser) return Optional.empty();

        Optional<User> existingUser = this.userRepository.findByEmail(data.getEmail());
        if (existingUser.isPresent()) {
            return Optional.empty();
        }

        data.setUuid(UUID.randomUUID());
        User savedUser = this.userRepository.save(data);
        return Optional.of(savedUser);
    }

    public Optional<User> findById(UUID uuid) {
        return this.userRepository.findByUuid(uuid);
    }

    public Optional<User> update(UUID uuid, User data) {
        Optional<User> existingUser = this.findById(uuid);
        if (existingUser.isEmpty()) {
            return Optional.empty();
        }

        User user = existingUser.get();
        user.setUsername(data.getUsername() != null ? data.getUsername() : user.getUsername());
        user.setEmail(data.getEmail() != null ? data.getEmail() : user.getEmail());
        user.setPassword(data.getPassword() != null ? data.getPassword() : user.getPassword());

        User updatedUser = this.userRepository.save(user);
        return Optional.of(updatedUser);
    }

    public boolean delete(UUID uuid) {
        Optional<User> existingUser = this.findById(uuid);

        if (existingUser.isEmpty()) {
            return false;
        }

        this.userRepository.delete(existingUser.get());
        return true;
    }

    private boolean validateUser(User user) {
        if (user == null) return false;

        return user.getUsername() != null && !user.getUsername().trim().isEmpty() &&
                user.getEmail() != null && !user.getEmail().trim().isEmpty() &&
                user.getPassword() != null && !user.getPassword().trim().isEmpty();
    }

    public User getByUsernameEntity(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new ExpressionException("Usuário não encontrado"));
    }
}

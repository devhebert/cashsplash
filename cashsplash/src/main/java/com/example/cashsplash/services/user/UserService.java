package com.example.cashsplash.services.user;

import com.example.cashsplash.models.User;
import com.example.cashsplash.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> saveUser(User data) {
        boolean isValidUser = validateUser(data);

        if (!isValidUser) return Optional.empty();

        Optional<User> existingUser = userRepository.findByEmail(data.getEmail());
        if (existingUser.isPresent()) {
            return Optional.empty();
        }

        data.setUuid(UUID.randomUUID());
        User savedUser = this.userRepository.save(data);
        return Optional.of(savedUser);
    }


    public Optional<User> updateUser(UUID uuid, User data) {
        Optional<User> existingUser = userRepository.findByUuid(uuid);
        if (existingUser.isEmpty()) {
            return Optional.empty();
        }

        User user = existingUser.get();
        user.setName(data.getName() != null ? data.getName() : user.getName());
        user.setEmail(data.getEmail() != null ? data.getEmail() : user.getEmail());
        user.setPassword(data.getPassword() != null ? data.getPassword() : user.getPassword());
        user.setUserType(data.getUserType() != null ? data.getUserType() : user.getUserType());

        User updatedUser = this.userRepository.save(user);
        return Optional.of(updatedUser);
    }

    public boolean deleteUser(UUID uuid) {
        Optional<User> existingUser = this.userRepository.findByUuid(uuid);

        if (existingUser.isEmpty()) {
            return false;
        }

        userRepository.delete(existingUser.get());
        return true;
    }

    private boolean validateUser(User user) {
        if (user == null) return false;

        return user.getName() != null && !user.getName().trim().isEmpty() &&
                user.getEmail() != null && !user.getEmail().trim().isEmpty() &&
                user.getPassword() != null && !user.getPassword().trim().isEmpty() &&
                user.getUserType() != null;
    }
}

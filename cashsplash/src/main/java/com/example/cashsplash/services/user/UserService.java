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

    public boolean saveUser(User data) {
        boolean isValidUser = validateUser(data);

        if (!isValidUser) return false;

        Optional<User> existingUser = userRepository.findByEmail(data.getEmail());
        if (existingUser.isPresent()) {
            return false;
        }

        data.setUuid(UUID.randomUUID());
        this.userRepository.save(data);
        return true;
    }

    public boolean updateUser(UUID uuid, User data) {
        Optional<User> existingUser = userRepository.findByUuid(uuid);
        if (existingUser.isEmpty()) {
            return false;
        }

        User user = existingUser.get();
        user.setName(data.getName() != null ? data.getName() : user.getName());
        user.setEmail(data.getEmail() != null ? data.getEmail() : user.getEmail());
        user.setPassword(data.getPassword() != null ? data.getPassword() : user.getPassword());
        user.setUserType(data.getUserType() != null ? data.getUserType() : user.getUserType());

        this.userRepository.save(user);
        return true;
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

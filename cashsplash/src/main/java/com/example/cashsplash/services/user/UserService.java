package com.example.cashsplash.services.user;

import com.example.cashsplash.models.User;
import com.example.cashsplash.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(UserRequestDTO data) {
        boolean isValidUser = validateUser(data);

        if (!isValidUser) return false;

        Optional<User> existingUser = userRepository.findByEmail(data.email());
        if (existingUser.isPresent()) {
            return false;
        }

        User user = new User(data);
        userRepository.save(user);
        return true;
    }

    public boolean updateUser(String id, UserRequestDTO data) {
        Long convertedId = Long.parseLong(id);
        Optional<User> existingUser = userRepository.findById(convertedId);
        if (existingUser.isEmpty()) {
            return false;
        }

        User user = existingUser.get();
        user.setName(data.name() != null ? data.name() : user.getName());
        user.setEmail(data.email() != null ? data.email() : user.getEmail());
        user.setPassword(data.password() != null ? data.password() : user.getPassword());
        user.setUserType(data.userType() != null ? data.userType() : user.getUserType());

        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(String id) {
        Long convertedId = Long.parseLong(id);
        Optional<User> existingUser = userRepository.findById(convertedId);

        if (existingUser.isEmpty()) {
            return false;
        }

        userRepository.delete(existingUser.get());
        return true;
    }

    private boolean validateUser(UserRequestDTO user) {
        if (user == null) return false;

        return user.name() != null && !user.name().trim().isEmpty() &&
                user.email() != null && !user.email().trim().isEmpty() &&
                user.password() != null && !user.password().trim().isEmpty() &&
                user.userType() != null;
    }
}

package com.foodordering.service;

import com.foodordering.model.Role;
import com.foodordering.model.User;
import com.foodordering.repository.UserRepository;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerCustomer(String name, String email, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            return false; // Email already taken
        }
        User user = new User(userRepository.getNextId(), name, email, password, Role.CUSTOMER);
        userRepository.save(user);
        return true;
    }

    public User login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            return userOpt.get();
        }
        return null;
    }
}

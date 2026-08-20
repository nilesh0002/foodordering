package com.foodordering.repository;

import com.foodordering.model.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
    void save(User user);
    int getNextId();
}

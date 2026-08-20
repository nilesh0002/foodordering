package com.foodordering.repository;

import com.foodordering.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> findAll();
    List<Order> findByUserId(int userId);
    Optional<Order> findById(int id);
    void save(Order order);
    int getNextId();
}

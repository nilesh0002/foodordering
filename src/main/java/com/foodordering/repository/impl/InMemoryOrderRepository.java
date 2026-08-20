package com.foodordering.repository.impl;

import com.foodordering.model.Order;
import com.foodordering.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryOrderRepository implements OrderRepository {
    private final Map<Integer, Order> orders = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1001); // Starting order ID from 1001

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public List<Order> findByUserId(int userId) {
        return orders.values().stream()
                .filter(o -> o.getUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> findById(int id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    @Override
    public int getNextId() {
        return idGenerator.getAndIncrement();
    }
}

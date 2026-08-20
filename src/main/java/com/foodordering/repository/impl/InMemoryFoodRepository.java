package com.foodordering.repository.impl;

import com.foodordering.model.FoodItem;
import com.foodordering.repository.FoodRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryFoodRepository implements FoodRepository {
    private final Map<Integer, FoodItem> foods = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    @Override
    public List<FoodItem> findAll() {
        return new ArrayList<>(foods.values());
    }

    @Override
    public Optional<FoodItem> findById(int id) {
        return Optional.ofNullable(foods.get(id));
    }

    @Override
    public void save(FoodItem foodItem) {
        foods.put(foodItem.getId(), foodItem);
    }

    @Override
    public void update(FoodItem foodItem) {
        foods.put(foodItem.getId(), foodItem);
    }

    @Override
    public void delete(int id) {
        foods.remove(id);
    }

    @Override
    public int getNextId() {
        return idGenerator.getAndIncrement();
    }
}

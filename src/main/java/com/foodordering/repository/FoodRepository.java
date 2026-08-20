package com.foodordering.repository;

import com.foodordering.model.FoodItem;
import java.util.List;
import java.util.Optional;

public interface FoodRepository {
    List<FoodItem> findAll();
    Optional<FoodItem> findById(int id);
    void save(FoodItem foodItem);
    void update(FoodItem foodItem);
    void delete(int id);
    int getNextId();
}

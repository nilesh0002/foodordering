package com.foodordering.service;

import com.foodordering.model.FoodItem;
import com.foodordering.repository.FoodRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FoodService {
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<FoodItem> getAllFoods() {
        return foodRepository.findAll();
    }

    public List<FoodItem> getAvailableFoods() {
        return foodRepository.findAll().stream()
                .filter(FoodItem::isAvailable)
                .collect(Collectors.toList());
    }

    public FoodItem getFoodById(int id) {
        return foodRepository.findById(id).orElse(null);
    }

    public List<FoodItem> searchFoods(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAvailableFoods();
        }
        String lowerKeyword = keyword.toLowerCase();
        return getAvailableFoods().stream()
                .filter(f -> f.getName().toLowerCase().contains(lowerKeyword) ||
                             f.getCategory().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }

    public void addFood(String name, String description, String category, double price, boolean available) {
        FoodItem food = new FoodItem(foodRepository.getNextId(), name, description, category, price, available);
        foodRepository.save(food);
    }

    public void updateFood(int id, String name, String description, String category, double price, boolean available) {
        FoodItem food = foodRepository.findById(id).orElse(null);
        if (food != null) {
            food.setName(name);
            food.setDescription(description);
            food.setCategory(category);
            food.setPrice(price);
            food.setAvailable(available);
            foodRepository.update(food);
        }
    }

    public void deleteFood(int id) {
        foodRepository.delete(id);
    }
}

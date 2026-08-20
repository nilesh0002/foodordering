package com.foodordering.service;

import com.foodordering.model.CartItem;
import com.foodordering.model.FoodItem;
import com.foodordering.repository.CartRepository;
import com.foodordering.repository.FoodRepository;

import java.util.List;

public class CartService {
    private final CartRepository cartRepository;
    private final FoodRepository foodRepository;

    public CartService(CartRepository cartRepository, FoodRepository foodRepository) {
        this.cartRepository = cartRepository;
        this.foodRepository = foodRepository;
    }

    public List<CartItem> getCart(int userId) {
        return cartRepository.getCartByUserId(userId);
    }

    public void addToCart(int userId, int foodId, int quantity) {
        FoodItem food = foodRepository.findById(foodId).orElse(null);
        if (food != null && food.isAvailable()) {
            CartItem cartItem = new CartItem(food, quantity);
            cartRepository.addCartItem(userId, cartItem);
        }
    }

    public void updateCartItem(int userId, int foodId, int quantity) {
        cartRepository.updateCartItem(userId, foodId, quantity);
    }

    public void removeCartItem(int userId, int foodId) {
        cartRepository.removeCartItem(userId, foodId);
    }

    public void clearCart(int userId) {
        cartRepository.clearCart(userId);
    }

    public double getCartTotal(int userId) {
        return getCart(userId).stream()
                .mapToDouble(CartItem::getItemTotal)
                .sum();
    }
}

package com.foodordering.repository.impl;

import com.foodordering.model.CartItem;
import com.foodordering.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCartRepository implements CartRepository {
    // Maps userId to a list of cart items
    private final Map<Integer, List<CartItem>> userCarts = new ConcurrentHashMap<>();

    @Override
    public List<CartItem> getCartByUserId(int userId) {
        return userCarts.computeIfAbsent(userId, k -> new ArrayList<>());
    }

    @Override
    public void addCartItem(int userId, CartItem cartItem) {
        List<CartItem> cart = getCartByUserId(userId);
        for (CartItem item : cart) {
            if (item.getFoodItem().getId() == cartItem.getFoodItem().getId()) {
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                return;
            }
        }
        cart.add(cartItem);
    }

    @Override
    public void updateCartItem(int userId, int foodId, int quantity) {
        List<CartItem> cart = getCartByUserId(userId);
        if (quantity <= 0) {
            removeCartItem(userId, foodId);
        } else {
            for (CartItem item : cart) {
                if (item.getFoodItem().getId() == foodId) {
                    item.setQuantity(quantity);
                    return;
                }
            }
        }
    }

    @Override
    public void removeCartItem(int userId, int foodId) {
        List<CartItem> cart = getCartByUserId(userId);
        cart.removeIf(item -> item.getFoodItem().getId() == foodId);
    }

    @Override
    public void clearCart(int userId) {
        userCarts.remove(userId);
    }
}

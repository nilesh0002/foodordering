package com.foodordering.repository;

import com.foodordering.model.CartItem;
import java.util.List;

public interface CartRepository {
    List<CartItem> getCartByUserId(int userId);
    void addCartItem(int userId, CartItem cartItem);
    void updateCartItem(int userId, int foodId, int quantity);
    void removeCartItem(int userId, int foodId);
    void clearCart(int userId);
}

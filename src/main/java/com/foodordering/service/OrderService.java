package com.foodordering.service;

import com.foodordering.model.CartItem;
import com.foodordering.model.Order;
import com.foodordering.model.OrderStatus;
import com.foodordering.repository.OrderRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

    public Order placeOrder(int userId) {
        List<CartItem> cartItems = cartService.getCart(userId);
        if (cartItems.isEmpty()) {
            return null; // Cannot place an empty order
        }
        
        // Copy the cart items so they aren't tied to the live cart
        List<CartItem> orderItems = new ArrayList<>(cartItems);
        double totalAmount = cartService.getCartTotal(userId);

        Order order = new Order(
                orderRepository.getNextId(),
                userId,
                orderItems,
                totalAmount,
                new Date(),
                OrderStatus.PLACED
        );

        orderRepository.save(order);
        cartService.clearCart(userId);
        
        return order;
    }

    public List<Order> getUserOrders(int userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}

package com.foodordering.config;

import com.foodordering.repository.*;
import com.foodordering.repository.impl.*;
import com.foodordering.service.*;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationConfig implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initialize Repositories
        UserRepository userRepository = new InMemoryUserRepository();
        FoodRepository foodRepository = new InMemoryFoodRepository();
        CartRepository cartRepository = new InMemoryCartRepository();
        OrderRepository orderRepository = new InMemoryOrderRepository();

        // Initialize Services
        UserService userService = new UserService(userRepository);
        FoodService foodService = new FoodService(foodRepository);
        CartService cartService = new CartService(cartRepository, foodRepository);
        OrderService orderService = new OrderService(orderRepository, cartService);

        // Store services in ServletContext
        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("foodService", foodService);
        sce.getServletContext().setAttribute("cartService", cartService);
        sce.getServletContext().setAttribute("orderService", orderService);

        // --- SAMPLE DATA ---
        // Admin
        userRepository.save(new com.foodordering.model.User(userRepository.getNextId(), "Admin", "admin@food.com", "admin123", com.foodordering.model.Role.ADMIN));
        // Customer
        userRepository.save(new com.foodordering.model.User(userRepository.getNextId(), "Customer", "customer@food.com", "customer123", com.foodordering.model.Role.CUSTOMER));

        // Foods
        foodRepository.save(new com.foodordering.model.FoodItem(foodRepository.getNextId(), "Margherita Pizza", "Classic cheese pizza", "PIZZA", 199.00, true));
        foodRepository.save(new com.foodordering.model.FoodItem(foodRepository.getNextId(), "Chicken Pizza", "Loaded with chicken and cheese", "PIZZA", 299.00, true));
        foodRepository.save(new com.foodordering.model.FoodItem(foodRepository.getNextId(), "Veg Burger", "Crispy veg patty with fresh veggies", "BURGER", 99.00, true));
        foodRepository.save(new com.foodordering.model.FoodItem(foodRepository.getNextId(), "Chicken Burger", "Juicy chicken burger with fresh vegetables", "BURGER", 149.00, true));
        foodRepository.save(new com.foodordering.model.FoodItem(foodRepository.getNextId(), "French Fries", "Crispy salted potato fries", "SNACKS", 79.00, true));
        foodRepository.save(new com.foodordering.model.FoodItem(foodRepository.getNextId(), "Pasta", "Creamy white sauce pasta", "MAIN_COURSE", 159.00, true));
        foodRepository.save(new com.foodordering.model.FoodItem(foodRepository.getNextId(), "Cold Coffee", "Refreshing chilled coffee", "DRINKS", 110.00, true));
        foodRepository.save(new com.foodordering.model.FoodItem(foodRepository.getNextId(), "Chocolate Cake", "Rich chocolate dessert", "DESSERT", 130.00, true));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup if needed
    }
}

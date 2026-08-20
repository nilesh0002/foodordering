package com.foodordering.servlet.admin;

import com.foodordering.service.FoodService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/add-food")
public class AddFoodServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/add-food.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String category = req.getParameter("category");
            double price = Double.parseDouble(req.getParameter("price"));
            boolean available = "on".equals(req.getParameter("available")) || "true".equals(req.getParameter("available"));

            if (name == null || name.trim().isEmpty() || category == null || category.trim().isEmpty() || price <= 0) {
                req.setAttribute("error", "Invalid input data. Name, Category, and positive price are required.");
                req.getRequestDispatcher("/admin/add-food.jsp").forward(req, resp);
                return;
            }

            FoodService foodService = (FoodService) getServletContext().getAttribute("foodService");
            foodService.addFood(name, description, category, price, available);

            req.getSession().setAttribute("message", "Food item added successfully.");
            resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Price must be a valid number.");
            req.getRequestDispatcher("/admin/add-food.jsp").forward(req, resp);
        }
    }
}

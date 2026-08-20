package com.foodordering.servlet.admin;

import com.foodordering.model.FoodItem;
import com.foodordering.service.FoodService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/edit-food")
public class EditFoodServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            FoodService foodService = (FoodService) getServletContext().getAttribute("foodService");
            FoodItem food = foodService.getFoodById(id);

            if (food != null) {
                req.setAttribute("food", food);
                req.getRequestDispatcher("/admin/edit-food.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String category = req.getParameter("category");
            double price = Double.parseDouble(req.getParameter("price"));
            boolean available = "on".equals(req.getParameter("available")) || "true".equals(req.getParameter("available"));

            if (name == null || name.trim().isEmpty() || category == null || category.trim().isEmpty() || price <= 0) {
                req.setAttribute("error", "Invalid input data.");
                doGet(req, resp); // Go back to form
                return;
            }

            FoodService foodService = (FoodService) getServletContext().getAttribute("foodService");
            foodService.updateFood(id, name, description, category, price, available);

            req.getSession().setAttribute("message", "Food item updated successfully.");
            resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid number format.");
            doGet(req, resp);
        }
    }
}

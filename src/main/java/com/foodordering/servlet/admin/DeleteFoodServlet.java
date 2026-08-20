package com.foodordering.servlet.admin;

import com.foodordering.service.FoodService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/delete-food")
public class DeleteFoodServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            FoodService foodService = (FoodService) getServletContext().getAttribute("foodService");
            foodService.deleteFood(id);
            req.getSession().setAttribute("message", "Food item deleted successfully.");
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("error", "Failed to delete food item.");
        }
        resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
    }
}

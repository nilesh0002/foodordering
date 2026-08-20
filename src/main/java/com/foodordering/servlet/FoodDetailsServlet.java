package com.foodordering.servlet;

import com.foodordering.model.FoodItem;
import com.foodordering.service.FoodService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/food-details")
public class FoodDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/foods");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            FoodService foodService = (FoodService) getServletContext().getAttribute("foodService");
            FoodItem food = foodService.getFoodById(id);

            if (food != null) {
                req.setAttribute("food", food);
                req.getRequestDispatcher("/food-details.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Food item not found.");
                req.getRequestDispatcher("/foods.jsp").forward(req, resp);
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/foods");
        }
    }
}

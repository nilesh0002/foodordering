package com.foodordering.servlet;

import com.foodordering.model.FoodItem;
import com.foodordering.service.FoodService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/foods")
public class FoodListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FoodService foodService = (FoodService) getServletContext().getAttribute("foodService");
        
        String keyword = req.getParameter("search");
        List<FoodItem> foods;

        if (keyword != null && !keyword.trim().isEmpty()) {
            foods = foodService.searchFoods(keyword);
            req.setAttribute("searchKeyword", keyword);
        } else {
            foods = foodService.getAvailableFoods();
        }

        req.setAttribute("foods", foods);
        req.getRequestDispatcher("/foods.jsp").forward(req, resp);
    }
}

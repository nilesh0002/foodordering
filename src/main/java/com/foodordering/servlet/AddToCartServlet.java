package com.foodordering.servlet;

import com.foodordering.model.User;
import com.foodordering.service.CartService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedInUser = (User) req.getSession().getAttribute("loggedInUser");
        if (loggedInUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        try {
            int foodId = Integer.parseInt(req.getParameter("foodId"));
            CartService cartService = (CartService) getServletContext().getAttribute("cartService");
            
            cartService.addToCart(loggedInUser.getId(), foodId, 1); // default quantity 1
            
            resp.sendRedirect(req.getContextPath() + "/cart");
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/foods");
        }
    }
}

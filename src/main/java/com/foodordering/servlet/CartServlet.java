package com.foodordering.servlet;

import com.foodordering.model.CartItem;
import com.foodordering.model.User;
import com.foodordering.service.CartService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedInUser = (User) req.getSession().getAttribute("loggedInUser");
        if (loggedInUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        CartService cartService = (CartService) getServletContext().getAttribute("cartService");
        List<CartItem> cartItems = cartService.getCart(loggedInUser.getId());
        double total = cartService.getCartTotal(loggedInUser.getId());

        req.setAttribute("cartItems", cartItems);
        req.setAttribute("cartTotal", total);
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }
}

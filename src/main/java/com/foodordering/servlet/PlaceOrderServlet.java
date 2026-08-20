package com.foodordering.servlet;

import com.foodordering.model.Order;
import com.foodordering.model.User;
import com.foodordering.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/place-order")
public class PlaceOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedInUser = (User) req.getSession().getAttribute("loggedInUser");
        if (loggedInUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        OrderService orderService = (OrderService) getServletContext().getAttribute("orderService");
        Order order = orderService.placeOrder(loggedInUser.getId());

        if (order != null) {
            req.setAttribute("order", order);
            req.getRequestDispatcher("/order-success.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("error", "Your cart is empty.");
            resp.sendRedirect(req.getContextPath() + "/cart");
        }
    }
}

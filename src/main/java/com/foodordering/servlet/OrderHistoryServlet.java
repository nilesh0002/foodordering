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
import java.util.List;

@WebServlet("/order-history")
public class OrderHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedInUser = (User) req.getSession().getAttribute("loggedInUser");
        if (loggedInUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        OrderService orderService = (OrderService) getServletContext().getAttribute("orderService");
        List<Order> orders = orderService.getUserOrders(loggedInUser.getId());

        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/order-history.jsp").forward(req, resp);
    }
}

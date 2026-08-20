package com.foodordering.filter;

import com.foodordering.model.Role;
import com.foodordering.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("loggedInUser") != null);
        
        if (!isLoggedIn) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("loggedInUser");
        if (user.getRole() != Role.ADMIN) {
            req.setAttribute("error", "Access Denied. Admin privileges required.");
            req.getRequestDispatcher("/foods.jsp").forward(req, resp);
            return;
        }

        chain.doFilter(request, response);
    }
}

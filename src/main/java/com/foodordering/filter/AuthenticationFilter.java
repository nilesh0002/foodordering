package com.foodordering.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    // Endpoints that require login for customers/users
    private static final List<String> PROTECTED_URLS = Arrays.asList(
            "/foods",
            "/food-details",
            "/add-to-cart",
            "/cart",
            "/update-cart",
            "/remove-from-cart",
            "/place-order",
            "/order-history",
            "/logout"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getServletPath();

        boolean isProtected = PROTECTED_URLS.stream().anyMatch(path::equals);

        if (isProtected) {
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("loggedInUser") == null) {
                req.getSession(true).setAttribute("error", "Please login first.");
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Food Ordering</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <h1><a href="${pageContext.request.contextPath}/" style="color:white; text-decoration:none;">FOOD ORDERING</a></h1>
        <nav>
            <a href="${pageContext.request.contextPath}/foods">Menu</a>
            <a href="${pageContext.request.contextPath}/register">Register</a>
        </nav>
    </header>

    <div class="container" style="max-width: 400px;">
        <h2>Login</h2>
        
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty sessionScope.error}">
            <div class="error">${sessionScope.error}</div>
            <c:remove var="error" scope="session" />
        </c:if>
        <c:if test="${not empty sessionScope.message}">
            <div class="success">${sessionScope.message}</div>
            <c:remove var="message" scope="session" />
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div>
                <label>Email</label>
                <input type="email" name="email" required>
            </div>
            <div>
                <label>Password</label>
                <input type="password" name="password" required>
            </div>
            <button type="submit">Login</button>
        </form>
        <p>Don't have an account? <a href="${pageContext.request.contextPath}/register">Register here</a></p>
    </div>
</body>
</html>

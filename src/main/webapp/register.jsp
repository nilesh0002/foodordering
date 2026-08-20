<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Food Ordering</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <h1><a href="${pageContext.request.contextPath}/" style="color:white; text-decoration:none;">FOOD ORDERING</a></h1>
        <nav>
            <a href="${pageContext.request.contextPath}/foods">Menu</a>
            <a href="${pageContext.request.contextPath}/login">Login</a>
        </nav>
    </header>

    <div class="container" style="max-width: 400px;">
        <h2>Register</h2>
        
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/register" method="post">
            <div>
                <label>Name</label>
                <input type="text" name="name" required>
            </div>
            <div>
                <label>Email</label>
                <input type="email" name="email" required>
            </div>
            <div>
                <label>Password</label>
                <input type="password" name="password" required minlength="6">
            </div>
            <div>
                <label>Confirm Password</label>
                <input type="password" name="confirmPassword" required minlength="6">
            </div>
            <button type="submit">Register</button>
        </form>
        <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Login here</a></p>
    </div>
</body>
</html>

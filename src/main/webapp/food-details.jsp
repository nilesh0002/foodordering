<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Food Details - Food Ordering</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="WEB-INF/header.jsp" />

    <div class="container" style="max-width: 600px; text-align: center;">
        <h2>${food.name}</h2>
        
        <p style="font-size: 18px; color: #555;">${food.description}</p>
        <p><strong>Category:</strong> ${food.category}</p>
        <div class="price" style="font-size: 24px; margin: 20px 0;">₹${food.price}</div>
        
        <c:if test="${food.available}">
            <p style="color: green;">Available</p>
            <form action="${pageContext.request.contextPath}/add-to-cart" method="post" style="margin-top: 20px;">
                <input type="hidden" name="foodId" value="${food.id}">
                <button type="submit" style="font-size: 18px; padding: 15px 30px;">Add to Cart</button>
            </form>
        </c:if>
        <c:if test="${not food.available}">
            <p style="color: red; font-size: 18px;">Currently unavailable</p>
        </c:if>

        <div style="margin-top: 30px;">
            <a href="${pageContext.request.contextPath}/foods" class="btn" style="background: #6c757d;">Back to Menu</a>
        </div>
    </div>
</body>
</html>

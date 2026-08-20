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
        
        <p style="font-size: 18px; color: var(--text-muted);">${food.description}</p>
        <p style="margin-top: 10px;"><strong style="color: var(--accent-color);">CATEGORY:</strong> <span style="text-transform: uppercase; letter-spacing: 1px;">${food.category}</span></p>
        <div class="price" style="font-size: 32px; font-weight: 800; color: var(--primary-color); margin: 20px 0;">₹${food.price}</div>
        
        <c:if test="${food.available}">
            <p style="color: var(--success-color); font-weight: bold; letter-spacing: 1px;">AVAILABLE</p>
            <form action="${pageContext.request.contextPath}/add-to-cart" method="post" style="margin-top: 20px;">
                <input type="hidden" name="foodId" value="${food.id}">
                <button type="submit" style="font-size: 18px; padding: 15px 40px; border-radius: 30px;">Add to Cart</button>
            </form>
        </c:if>
        <c:if test="${not food.available}">
            <p style="color: var(--danger-color); font-size: 18px; font-weight: bold;">Currently unavailable</p>
        </c:if>

        <div style="margin-top: 40px;">
            <a href="${pageContext.request.contextPath}/foods" class="btn btn-secondary">Back to Menu</a>
        </div>
    </div>
</body>
</html>

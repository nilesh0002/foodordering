<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Success - Food Ordering</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="WEB-INF/header.jsp" />

    <div class="container" style="text-align: center; max-width: 600px;">
        <h2 style="color: green;">Order placed successfully!</h2>
        
        <div style="margin: 30px 0; padding: 20px; border: 1px solid #ddd; background: #f9f9f9; border-radius: 8px;">
            <p><strong>Order ID:</strong> ${order.id}</p>
            <p><strong>Total Amount:</strong> ₹${order.totalAmount}</p>
            <p><strong>Status:</strong> ${order.status}</p>
            <p><strong>Date:</strong> ${order.orderDate}</p>
        </div>

        <a href="${pageContext.request.contextPath}/order-history" class="btn">View Order History</a>
        <a href="${pageContext.request.contextPath}/foods" class="btn" style="background: #17a2b8;">Order More Food</a>
    </div>
</body>
</html>

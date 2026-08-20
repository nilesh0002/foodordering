<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard - Food Ordering</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .stat-card {
            background: #f8f9fa;
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 20px;
            text-align: center;
            flex: 1;
        }
        .stat-card h3 {
            margin: 0;
            font-size: 36px;
            color: #007bff;
        }
        .stats-container {
            display: flex;
            gap: 20px;
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
    <jsp:include page="../WEB-INF/header.jsp" />

    <div class="container">
        <h2>Admin Dashboard</h2>
        
        <c:if test="${not empty sessionScope.message}">
            <div class="success">${sessionScope.message}</div>
            <c:remove var="message" scope="session" />
        </c:if>

        <div class="stats-container">
            <div class="stat-card">
                <h3>${totalCustomers}</h3>
                <p>Customers</p>
            </div>
            <div class="stat-card">
                <h3>${totalFoods}</h3>
                <p>Food Items</p>
            </div>
            <div class="stat-card">
                <h3>${totalOrders}</h3>
                <p>Orders</p>
            </div>
        </div>

        <div>
            <a href="${pageContext.request.contextPath}/admin/add-food" class="btn">Add Food</a>
            <a href="${pageContext.request.contextPath}/foods" class="btn" style="background:#17a2b8;">Manage Food</a>
            <a href="${pageContext.request.contextPath}/admin/orders" class="btn" style="background:#ffc107; color:black;">View Orders</a>
        </div>
    </div>
</body>
</html>

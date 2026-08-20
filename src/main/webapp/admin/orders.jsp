<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Orders - Food Ordering</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="../WEB-INF/header.jsp" />

    <div class="container">
        <h2>All Customer Orders</h2>

        <c:if test="${empty orders}">
            <p>No orders have been placed yet.</p>
        </c:if>

        <c:if test="${not empty orders}">
            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>User ID</th>
                        <th>Date</th>
                        <th>Total Amount</th>
                        <th>Status</th>
                        <th>Items</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.userId}</td>
                            <td>${order.orderDate}</td>
                            <td>₹${order.totalAmount}</td>
                            <td>${order.status}</td>
                            <td>
                                <ul>
                                    <c:forEach var="item" items="${order.items}">
                                        <li>${item.foodItem.name} x ${item.quantity}</li>
                                    </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>

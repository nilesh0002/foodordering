<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Orders - Food Ordering</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="WEB-INF/header.jsp" />

    <div class="container">
        <h2>Order History</h2>
        
        <c:if test="${empty orders}">
            <p>You have not placed any orders yet.</p>
        </c:if>

        <c:if test="${not empty orders}">
            <c:forEach var="order" items="${orders}">
                <div style="border: 1px solid #ccc; margin-bottom: 20px; padding: 15px; border-radius: 5px;">
                    <h3>Order #${order.id}</h3>
                    <p><strong>Date:</strong> ${order.orderDate}</p>
                    <p><strong>Status:</strong> ${order.status}</p>
                    
                    <table style="margin-top: 10px;">
                        <thead>
                            <tr>
                                <th>Item</th>
                                <th>Qty</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${order.items}">
                                <tr>
                                    <td>${item.foodItem.name}</td>
                                    <td>${item.quantity}</td>
                                    <td>₹${item.itemTotal}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2" style="text-align:right; font-weight:bold;">Total:</td>
                                <td style="font-weight:bold;">₹${order.totalAmount}</td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </c:forEach>
        </c:if>
    </div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cart - Food Ordering</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="WEB-INF/header.jsp" />

    <div class="container">
        <h2>YOUR CART</h2>

        <c:if test="${not empty sessionScope.error}">
            <div class="error">${sessionScope.error}</div>
            <c:remove var="error" scope="session" />
        </c:if>

        <c:if test="${empty cartItems}">
            <p>Your cart is empty.</p>
            <a href="${pageContext.request.contextPath}/foods" class="btn">Browse Food</a>
        </c:if>

        <c:if test="${not empty cartItems}">
            <table>
                <thead>
                    <tr>
                        <th>Food</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Item Total</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${cartItems}">
                        <tr>
                            <td>${item.foodItem.name}</td>
                            <td>₹${item.foodItem.price}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/update-cart" method="post" style="display: flex; gap: 10px; align-items: center; flex-direction: row;">
                                    <input type="hidden" name="foodId" value="${item.foodItem.id}">
                                    <input type="number" name="quantity" value="${item.quantity}" min="0" style="width: 80px; text-align: center;">
                                    <button type="submit" class="btn btn-secondary" style="padding: 10px 15px;">Update</button>
                                </form>
                            </td>
                            <td style="font-weight: bold; color: var(--primary-color);">₹${item.itemTotal}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/remove-from-cart" method="post">
                                    <input type="hidden" name="foodId" value="${item.foodItem.id}">
                                    <button type="submit" class="btn-danger" style="padding: 10px 15px;">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="3" style="text-align: right; font-weight: 800; color: var(--text-muted);">TOTAL</td>
                        <td colspan="2" style="font-weight: 800; font-size: 22px; color: var(--primary-color);">₹${cartTotal}</td>
                    </tr>
                </tfoot>
            </table>

            <div style="margin-top: 30px; text-align: right; display: flex; justify-content: space-between; align-items: center;">
                <a href="${pageContext.request.contextPath}/foods" class="btn btn-secondary">Continue Shopping</a>
                <form action="${pageContext.request.contextPath}/place-order" method="post">
                    <button type="submit" style="font-size: 18px; padding: 10px 20px;">Place Order</button>
                </form>
            </div>
        </c:if>
    </div>
</body>
</html>

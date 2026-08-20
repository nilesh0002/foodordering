<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header>
    <h1><a href="${pageContext.request.contextPath}/" style="color:white; text-decoration:none;">FOOD ORDERING</a></h1>
    <nav>
        <a href="${pageContext.request.contextPath}/">Home</a>
        <c:choose>
            <c:when test="${not empty sessionScope.loggedInUser}">
                <c:if test="${sessionScope.loggedInUser.role == 'CUSTOMER'}">
                    <a href="${pageContext.request.contextPath}/foods">Menu</a>
                    <a href="${pageContext.request.contextPath}/cart">Cart</a>
                    <a href="${pageContext.request.contextPath}/order-history">My Orders</a>
                </c:if>
                <c:if test="${sessionScope.loggedInUser.role == 'ADMIN'}">
                    <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
                    <a href="${pageContext.request.contextPath}/foods">Food Items</a>
                    <a href="${pageContext.request.contextPath}/admin/orders">Orders</a>
                </c:if>
                <a href="${pageContext.request.contextPath}/logout">Logout</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/foods">Menu</a>
                <a href="${pageContext.request.contextPath}/login">Login</a>
                <a href="${pageContext.request.contextPath}/register">Register</a>
            </c:otherwise>
        </c:choose>
    </nav>
</header>

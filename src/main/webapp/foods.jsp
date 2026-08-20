<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Menu - Food Ordering</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="WEB-INF/header.jsp" />

    <div class="container">
        <h2>Menu</h2>
        
        <form class="search-box" action="${pageContext.request.contextPath}/foods" method="get">
            <input type="text" name="search" placeholder="Search by name or category..." value="${searchKeyword}">
            <button type="submit">Search</button>
            <c:if test="${not empty searchKeyword}">
                <a href="${pageContext.request.contextPath}/foods" class="btn" style="background:#6c757d;">Clear</a>
            </c:if>
        </form>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <div class="food-grid">
            <c:forEach var="food" items="${foods}">
                <div class="food-card">
                    <h3>${food.name}</h3>
                    <p>${food.description}</p>
                    <p><em>${food.category}</em></p>
                    <div class="price">₹${food.price}</div>
                    
                    <div style="margin-top: 15px;">
                        <a href="${pageContext.request.contextPath}/food-details?id=${food.id}" class="btn" style="background:#17a2b8;">View Details</a>
                        
                        <c:if test="${food.available}">
                            <form action="${pageContext.request.contextPath}/add-to-cart" method="post" style="display:inline-block; margin-top: 10px;">
                                <input type="hidden" name="foodId" value="${food.id}">
                                <button type="submit">Add to Cart</button>
                            </form>
                        </c:if>
                        <c:if test="${not food.available}">
                            <p style="color:red; margin-top:10px;">Currently unavailable</p>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${empty foods}">
                <p>No food items found.</p>
            </c:if>
        </div>
    </div>
</body>
</html>

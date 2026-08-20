<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Food - Food Ordering</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="../WEB-INF/header.jsp" />

    <div class="container" style="max-width: 500px;">
        <h2>Edit Food Item</h2>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/admin/edit-food" method="post">
            <input type="hidden" name="id" value="${food.id}">
            <div>
                <label>Food Name</label>
                <input type="text" name="name" value="${food.name}" required>
            </div>
            <div>
                <label>Description</label>
                <input type="text" name="description" value="${food.description}" required>
            </div>
            <div>
                <label>Category</label>
                <input type="text" name="category" value="${food.category}" required>
            </div>
            <div>
                <label>Price (₹)</label>
                <input type="number" name="price" step="0.01" min="1" value="${food.price}" required>
            </div>
            <div>
                <label style="display:inline-block; margin-right: 10px;">Available</label>
                <input type="checkbox" name="available" ${food.available ? 'checked' : ''}>
            </div>
            
            <button type="submit">Save Changes</button>
            <a href="${pageContext.request.contextPath}/foods" class="btn" style="background: #6c757d;">Cancel</a>
        </form>
        
        <form action="${pageContext.request.contextPath}/admin/delete-food" method="post" style="margin-top:20px;">
            <input type="hidden" name="id" value="${food.id}">
            <button type="submit" class="btn-danger" onclick="return confirm('Are you sure you want to delete this food item?');">Delete Food Item</button>
        </form>
    </div>
</body>
</html>

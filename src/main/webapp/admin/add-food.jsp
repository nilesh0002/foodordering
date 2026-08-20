<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Food - Food Ordering</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="../WEB-INF/header.jsp" />

    <div class="container" style="max-width: 500px;">
        <h2>Add Food Item</h2>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/admin/add-food" method="post">
            <div>
                <label>Food Name</label>
                <input type="text" name="name" required>
            </div>
            <div>
                <label>Description</label>
                <input type="text" name="description" required>
            </div>
            <div>
                <label>Category</label>
                <input type="text" name="category" required>
            </div>
            <div>
                <label>Price (₹)</label>
                <input type="number" name="price" step="0.01" min="1" required>
            </div>
            <div>
                <label style="display:inline-block; margin-right: 10px;">Available</label>
                <input type="checkbox" name="available" checked>
            </div>
            <button type="submit">Add Food Item</button>
            <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn" style="background: #6c757d;">Cancel</a>
        </form>
    </div>
</body>
</html>

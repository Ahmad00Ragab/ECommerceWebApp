<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update User</title>
</head>
<body>
<h2>Update User</h2>

<!-- Display validation errors, if any -->
<c:if test="${not empty errors}">
    <ul>
        <c:forEach var="error" items="${errors}">
            <li>${error}</li>
        </c:forEach>
    </ul>
</c:if>

<!-- User update form -->
<form action="${pageContext.request.contextPath}/user" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="userId" value="${user.id}">

    <label for="username">Username:</label>
    <input type="text" id="username" name="username" value="${user.username}" required><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${user.email}" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" value="${user.phone}"><br>

    <label for="city">City:</label>
    <input type="text" id="city" name="city" value="${user.city}"><br>

    <label for="country">Country:</label>
    <input type="text" id="country" name="country" value="${user.country}"><br>

    <label for="street">Street:</label>
    <input type="text" id="street" name="street" value="${user.street}"><br>

    <button type="submit">Update User</button>
</form>
</body>
</html>
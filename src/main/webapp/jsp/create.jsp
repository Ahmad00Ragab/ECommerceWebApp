<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<h2>Create New User</h2>

<!-- Display validation errors, if any -->
<c:if test="${not empty errors}">
    <ul">
        <c:forEach var="error" items="${errors}">
            <li>${error}</li>
        </c:forEach>
    </ul>
</c:if>

<!-- User creation form -->
<form action="${pageContext.request.contextPath}/user" method="post">
    <input type="hidden" name="action" value="create">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <button type="submit">Create User</button>
</form>
</body>
</html>
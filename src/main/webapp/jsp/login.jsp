<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Homepage</title>
</head>
<body>


<!-- Display error message if exists -->
<c:if test="${not empty errorMessage}">
    <span style="color:red;">${errorMessage}</span><br>
</c:if>

<form method="POST" action="${pageContext.request.contextPath}/login">
    <br> Email: <input type="email" name="email">
    <br> Password: <input type="password" name="password">
    <br> <input type="submit" value="Login">
</form>

<p>Don't have an account? <a href="register.jsp">Register here</a></p>

</body>
<hr>
<footer>
    <p>&copy; 2024 My Application. All rights reserved.</p>
</footer>
</html>
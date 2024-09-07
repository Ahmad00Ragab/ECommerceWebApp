<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List Users</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
<h1>Users List</h1>
<c:if test="${not empty users}">
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/user?action=view&userId=${user.id}">View</a>
                    <a href="${pageContext.request.contextPath}/user?action=update&userId=${user.id}">Update</a>
                    <a href="${pageContext.request.contextPath}/user?action=delete&userId=${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>
<a href="${pageContext.request.contextPath}/user?action=create">Create New User</a>
</body>
</html>
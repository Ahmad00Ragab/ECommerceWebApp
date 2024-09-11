<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List Users</title>
</head>
<body>

<h1>Users List</h1>

<c:if test="${not empty error}">
    <div class="alert alert-danger text-center" style="width: fit-content; margin: 0 auto;">
            ${error}
    </div>
</c:if>

<c:if test="${empty users}">
    <p>No users available.</p>
</c:if>

<c:if test="${not empty users}">
    <table>
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
                    <a href="${pageContext.request.contextPath}/user?action=updateForm&userId=${user.id}">Update</a>
                    <a href="${pageContext.request.contextPath}/user?action=confirmDelete&userId=${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<a href="${pageContext.request.contextPath}/user?action=create">Create New User</a>

</body>
</html>
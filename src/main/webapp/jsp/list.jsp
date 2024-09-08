<%@ page import="gov.iti.jets.models.User" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List Users</title>
</head>
<body>

<h1>Users List</h1>

<%
    Set<User> users = (Set<User>) request.getAttribute("users");
    if (users == null || users.isEmpty()) {
%>
<p>No users available.</p>
<%
} else {
%>
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
    <%
        for (User user : users) {
    %>
    <tr>
        <td><%= user.getId() %></td>
        <td><%= user.getUsername() %></td>
        <td><%= user.getEmail() %></td>
        <td>
            <a href="<%= request.getContextPath() %>/user?action=view&userId=<%= user.getId() %>">View</a>
            <a href="<%= request.getContextPath() %>/user?action=update&userId=<%= user.getId() %>">Update</a>
            <a href="<%= request.getContextPath() %>/user?action=delete&userId=<%= user.getId() %>">Delete</a>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<%
    }
%>
<a href="<%= request.getContextPath() %>/user?action=create">Create New User</a>

</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - List Users</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" />
    
    <style>
        body {
            background-color: #f4f7f9;
        }

        /* Sidebar Style */
        .sidebar {
            width: 250px;
            background-color: #343a40;
            position: fixed;
            height: 100vh;
            left: 0;
            top: 0;
            padding-top: 20px;
            color: white;
        }

        .sidebar a {
            color: white;
            padding: 10px;
            display: block;
            text-decoration: none;
        }

        .sidebar a:hover {
            background-color: #495057;
        }

        .main-content {
            margin-left: 250px;
            padding: 20px;
        }

        .card {
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        footer {
            text-align: center;
            background-color: #343a40;
            color: white;
            padding: 10px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
            box-shadow: 0 -4px 6px rgba(0, 0, 0, 0.1);
        }

        .table th, .table td {
            text-align: center;
        }

        .alert {
            width: fit-content;
            margin: 20px auto;
        }

        a.btn {
            margin-right: 10px;
        }
    </style>
</head>

<body>

    <!-- Sidebar Section -->
    <div class="sidebar">
        <h2>Admin Panel</h2>
        <a href="${pageContext.request.contextPath}/ProductController">Manage Products</a>
        <a href="${pageContext.request.contextPath}/AdminController">my profile</a>
        <a href="${pageContext.request.contextPath}/user?action=list">Customer Profiles</a>
        <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
    </div>

    <!-- Main Content Section -->
    <div class="main-content">
        <div class="container-fluid">
            <div class="card p-4">
                <h1 class="text-center">Users List</h1>

                <!-- Error message -->
                <c:if test="${not empty error}">
                    <div class="alert alert-danger text-center">${error}</div>
                </c:if>

                <!-- No users message -->
                <c:if test="${empty users}">
                    <p class="text-center">No users available.</p>
                </c:if>

                <!-- Users Table -->
                <c:if test="${not empty users}">
                    <table class="table table-bordered table-hover mt-4">
                        <thead class="thead-dark">
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
                                        <a href="${pageContext.request.contextPath}/user?action=view&userId=${user.id}" class="btn btn-info btn-sm">View</a>
                                        <a href="${pageContext.request.contextPath}/user?action=updateForm&userId=${user.id}" class="btn btn-warning btn-sm">Update</a>
                                        <a href="${pageContext.request.contextPath}/user?action=confirmDelete&userId=${user.id}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
                                        <!-- Add the Order History button -->
                                        <a href="${pageContext.request.contextPath}/user?action=viewOrderHistory&userId=${user.id}" class="btn btn-secondary btn-sm">Order History</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </div>

    <footer>
        &copy; 2024 Your Admin Panel. All Rights Reserved for shoesly.
    </footer>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>

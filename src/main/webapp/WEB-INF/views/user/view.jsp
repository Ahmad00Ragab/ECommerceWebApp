<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<<<<<<< HEAD
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Details</title>

    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" />
    
    <style>
        body {
            background-color: #f4f7f9;
        }
        .card {
            margin-top: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .card-header {
            background-color: #343a40;
            color: white;
        }
        .main-content {
            margin-left: 270px; /* Adjusted to leave space for the sidebar */
            padding: 20px;
            padding-bottom: 80px; /* Adds extra space at the bottom for the button */
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
        .btn {
            margin-top: 20px; /* Adds space between the button and table */
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

    </style>
</head>

<body>

    <!-- Sidebar Section -->
    <div class="sidebar">
        <h2>Admin Panel</h2>
        <a href="${pageContext.request.contextPath}/ProductController">Manage Products</a>
        <a href="${pageContext.request.contextPath}/AdminController">My Profile</a>
        <a href="<c:url value='/user?action=list'/>">Customer Profile</a>
        <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
    </div>

    <!-- Main Content Section -->
    <div class="main-content">
        <div class="container">
            <div class="card">
                <div class="card-header">
                    <h2>User Details</h2>
                </div>
                <div class="card-body">
                    <table class="table table-striped">
                        <tr>
                            <th>Username</th>
                            <td>${user.username}</td>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <td>${user.email}</td>
                        </tr>
                        <tr>
                            <th>First Name</th>
                            <td>${user.firstName}</td>
                        </tr>
                        <tr>
                            <th>Last Name</th>
                            <td>${user.lastName}</td>
                        </tr>
                        <tr>
                            <th>Phone</th>
                            <td>${user.phone}</td>
                        </tr>
                        <tr>
                            <th>City</th>
                            <td>${user.city}</td>
                        </tr>
                        <tr>
                            <th>Country</th>
                            <td>${user.country}</td>
                        </tr>
                        <tr>
                            <th>Street</th>
                            <td>${user.street}</td>
                        </tr>
                        <tr>
                            <th>Credit Limit</th>
                            <td>${user.creditLimit}</td>
                        </tr>
                        <tr>
                            <th>Birthdate</th>
                            <td>${user.birthdate}</td>
                        </tr>
                        <tr>
                            <th>Date Created</th>
                            <td>${user.dateCreated}</td>
                        </tr>
                        <tr>
                            <th>Last Updated</th>
                            <td>${user.lastUpdated}</td>
                        </tr>
                    </table>

                    <!-- Back button -->
                    <a href="${pageContext.request.contextPath}/user?action=list" class="btn btn-primary">Back to User List</a>

                    <!-- Display error messages -->
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger mt-3">${error}</div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <footer>
        &copy; 2024 Your Admin Panel. All Rights Reserved For Shoesly.
    </footer>

    <!-- Include Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

</body>

</html>
=======
<html>
<head>
    <meta charset="UTF-8">
    <title>View User</title>
</head>
<body>
<h1>User Details</h1>

    <table>
        <tr>
            <th>Username</th>
            <td>${user.username}</td>
        </tr>
        <tr>
            <th>Email</th>
            <td>${user.email}</td>
        </tr>
        <tr>
            <th>Phone</th>
            <td>${user.phone}</td>
        </tr>
        <tr>
            <th>City</th>
            <td>${user.city}</td>
        </tr>
        <tr>
            <th>Country</th>
            <td>${user.country}</td>
        </tr>
        <tr>
            <th>Street</th>
            <td>${user.street}</td>
        </tr>
    </table>
    <a href="${pageContext.request.contextPath}/user?action=list">Back to User List</a>

<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>
</body>
</html>
>>>>>>> 304efc51c02b9d3b2b1b85f2c04c68dbdabe50de

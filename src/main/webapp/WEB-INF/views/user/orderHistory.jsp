<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order History</title>
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
            background-color:  #343a40;
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
    </style>
</head>
<body>

    <!-- Sidebar Section -->
    <div class="sidebar">
        <h2>Admin Panel</h2>
        <a href="${pageContext.request.contextPath}/ProductController">Manage Products</a>
        <a href="${pageContext.request.contextPath}/AdminController">My Profile</a>
        <a href="${pageContext.request.contextPath}/user?action=list">Customer Profiles</a>
        <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
    </div>

    <!-- Main Content Section -->
    <div class="main-content">
        <div class="container-fluid">
            <div class="card p-4">
                <h1 class="text-center">Order History for ${user.username}</h1>

                <!-- No orders message -->
                <c:if test="${empty orders}">
                    <p class="text-center">No orders found for this user.</p>
                </c:if>

                <!-- Orders Table -->
                <c:if test="${not empty orders}">
                    <table class="table table-bordered table-hover mt-4">
                        <thead class="thead-dark">
                            <tr>
                                <th>Order ID</th>
                                <th>Total Price</th>
                                <th>Date Created</th>
                                <th>Order Items</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" items="${orders}">
                                <tr>
                                    <td>${order.id}</td>
                                    <td>$${order.totalPrice}</td>
                                    <td class="orderDate">${order.dateCreated}</td>
                                    <td>
                                        <c:forEach var="item" items="${order.orderItems}">
                                            ${item.product.name} (x${item.quantity}) - $${item.price}<br/>
                                        </c:forEach>
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

    <script>
        // Date formatting for all order dates
        document.querySelectorAll('.orderDate').forEach(function(dateElement) {
            let rawDate = new Date(dateElement.textContent.trim());
            let formattedDate = rawDate.toLocaleString("en-US", {
                year: "numeric",
                month: "2-digit",
                day: "2-digit",
                hour: "2-digit",
                minute: "2-digit",
                second: "2-digit",
                hour12: true
            });
            dateElement.textContent = formattedDate;
        });
    </script>
</body>
</html>

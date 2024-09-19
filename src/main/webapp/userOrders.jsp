<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order History</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="css/linearicons.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/nouislider.min.css">
    <link rel="stylesheet" href="css/ion.rangeSlider.css" />
    <link rel="stylesheet" href="css/ion.rangeSlider.skinFlat.css" />
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/main.css">

    <style>
        /* Restore gap between Contacts and icons */
        .navbar-nav.navbar-right {
            margin-left: 600px; /* Adjust this value for the desired gap */
        }

        /* Optionally, increase space between the individual icons (e.g., search, cart) */
        .navbar-nav.navbar-right .nav-item {
            margin-left: 10px;
        }
    </style>


    <style>

        .main-content {
            margin-top: 100px; /* Adjust the size of the margin as needed */
        }
        /* Professional table styling */
        .table {
            border-collapse: collapse;
            width: 100%;
            font-family: 'Arial', sans-serif;
            color: #333;
        }

        /* Table headers */
        .table thead th {
            background-color: #f8f9fa; /* Light grey background for headers */
            color: #333;
            text-align: left;
            padding: 12px;
            border-bottom: 2px solid #dee2e6; /* Slightly darker border */
            font-size: 16px;
            font-weight: 600; /* Bold headers */
        }

        /* Table rows */
        .table tbody td {
            padding: 12px;
            border-bottom: 1px solid #dee2e6; /* Light border for rows */
            font-size: 14px;
            vertical-align: middle; /* Center text vertically */
        }

        /* Alternate row colors */
        .table tbody tr:nth-child(even) {
            background-color: #f8f9fa; /* Alternate row color */
        }

        /* Hover effect for rows */
        .table tbody tr:hover {
            background-color: #e9ecef; /* Hover background color */
            cursor: pointer;
        }

        /* Styling for links in table */
        .table a {
            color: #007bff;
            text-decoration: none;
        }

        .table a:hover {
            text-decoration: underline;
        }

        /* Table caption */
        .table caption {
            caption-side: top;
            font-size: 20px;
            font-weight: 700;
            margin-bottom: 15px;
        }

        /* Responsive adjustments for smaller screens */
        @media (max-width: 768px) {
            .table thead {
                display: none; /* Hide table headers on small screens */
            }

            .table, .table tbody, .table tr, .table td {
                display: block; /* Make table rows stack vertically */
                width: 100%;
            }

            .table tbody tr {
                margin-bottom: 15px; /* Space between rows */
                border: 1px solid #dee2e6;
            }

            .table td {
                text-align: right;
                padding-left: 50%;
                position: relative;
            }

            .table td::before {
                content: attr(data-label);
                position: absolute;
                left: 0;
                width: 50%;
                padding-left: 15px;
                font-weight: 600;
                text-align: left;
                color: #495057;
            }
        }

        /* Ensure the search bar is hidden initially */
        .search_input {
            display: none;
        }

        /* Open search bar when triggered */
        .search_input.open {
            display: block;
        }

        /* Fix alignment and layout of search and toggle button */
        .search {
            position: relative;
            cursor: pointer;
        }

        .search_input_box {
            position: absolute;
            top: 100%;
            left: 0;
            right: 0;
            padding: 10px;
            background-color: white;
            border: 1px solid #ddd;
        }

        /* Make search close button larger */
        #close_search {
            font-size: 20px;
            cursor: pointer;
        }
    </style>
</head>
<body>

<%@ include file="/common/header.jsp" %>

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

<%@ include file="/common/footer.jsp" %>

<!-- Updated JS for Date Formatting and Search Bar -->
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

    // Search bar toggle functionality
    document.querySelector('#search').addEventListener('click', function() {
        document.querySelector('.search_input').classList.toggle('open');
    });

    // Close search functionality
    document.querySelector('#close_search').addEventListener('click', function() {
        document.querySelector('.search_input').classList.remove('open');
    });
</script>


<!-- Latest JS Files -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

</body>
</html>

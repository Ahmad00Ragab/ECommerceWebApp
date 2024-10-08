<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Details</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        body {
            background-color: #f4f7f9;
        }
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
    </style>
</head>

<body>

    <!-- Sidebar Section -->
    <div class="sidebar">
        <h2>Admin Panel</h2>
            
        <a href="${pageContext.request.contextPath}/ProductController">Manage Products</a>

        <a href="${pageContext.request.contextPath}/AdminController">my profile</a>

        <a href="<c:url value='/user?action=list'/>">Customer Profile</a>
    
        <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
    </div>

    <!-- Main Content Section -->
    <div class="main-content">
        <div class="container">
            <h1>Admin Details</h1>
            <div class="card">
                <div class="card-body">
                    <table class="table table-striped">
                        <tr>
                            <th>ID</th>
                            <td>${admin.id}</td>
                        </tr>
                        <tr>
                            <th>Name</th>
                            <td>${admin.name}</td>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <td>${admin.email}</td>
                        </tr>
                        <tr>
                            <th>Date Created</th>
                            <td>${admin.dateCreated}</td>
                        </tr>
                        <tr>
                            <th>Last Updated</th>
                            <td>${admin.lastUpdated}</td>
                        </tr>
                        <tr>
                            <th>Created By</th>
                            <td>${admin.createdBy}</td>
                        </tr>
                    </table>
                        <a href="${pageContext.request.contextPath}/ProductController" class="btn btn-primary"> Back to Product List </a>
                </div>
            </div>

            <c:if test="${not empty error}">
                <p style="color: red;">${error}</p>
            </c:if>
        </div>
    </div>

    <footer>
        &copy; 2024 Your Admin Panel. All Rights Reserved for shoesly.
    </footer>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</body>
</html>

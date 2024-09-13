<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update User</title>

    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" />

    <style>
        body {
            background-color: #f4f7f9;
        }
        .form-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        .form-header {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .btn-custom {
            background-color: #343a40;
            color: white;
        }
        .btn-custom:hover {
            background-color: #495057;
        }
    </style>
</head>

<body>

<div class="main-content">
    <div class="container">
        <!-- User Update Form -->
        <div class="form-container">
            <h2 class="form-header">Update User Information</h2>

            <!-- Display validation errors, if any -->
            <c:if test="${not empty errors}">
                <div class="alert alert-danger">
                    <ul>
                        <c:forEach var="error" items="${errors}">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>


            <!-- User update form -->
            <form action="${pageContext.request.contextPath}/user" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="userId" value="${user.id}">

                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" class="form-control" value="${user.username}" required>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" class="form-control" value="${user.email}" required>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="phone">Phone:</label>
                    <input type="text" id="phone" name="phone" class="form-control" value="${user.phone}">
                </div>

                <div class="form-group">
                    <label for="city">City:</label>
                    <input type="text" id="city" name="city" class="form-control" value="${user.city}">
                </div>

                <div class="form-group">
                    <label for="country">Country:</label>
                    <input type="text" id="country" name="country" class="form-control" value="${user.country}">
                </div>

                <div class="form-group">
                    <label for="street">Street:</label>
                    <input type="text" id="street" name="street" class="form-control" value="${user.street}">
                </div>

                <div class="form-group">
                    <label for="creditLimit">Credit Limit:</label>
                    <input type="text" id="creditLimit" name="creditLimit" class="form-control" value="${user.creditLimit}">
                </div>

                <div class="form-group">
                    <label for="birthdate">Birthdate:</label>
                    <input type="date" id="birthdate" name="birthdate" class="form-control" value="${user.birthdate}">
                </div>

                <button type="submit" class="btn btn-custom btn-block">Update User</button>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>

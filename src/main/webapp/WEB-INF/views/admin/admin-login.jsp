<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<!-- ======= Head ========= -->
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Admin Login</title>
    
    <!-- Bootstrap CSS for styling -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" />

    <style>
        body {
            background-color: #f4f7f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        
        .login-container {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }
        
        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #343a40;
        }

        .form-group label {
            font-weight: bold;
            color: #343a40;
        }

        .btn-submit {
            width: 100%;
            background-color: #007bff;
            color: white;
        }

        .btn-submit:hover {
            background-color: #0056b3;
        }

        #err {
            color: red;
            font-size: 0.9em;
        }

        footer {
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            text-align: center;
            background-color: #343a40;
            color: white;
            padding: 10px 0;
        }

        .error-message {
            color: red;
            font-weight: bold;
            margin-bottom: 15px;
        }

        .btn-home {
            margin-top: 15px;
            width: 100%;
            background-color: #28a745;
            color: white;
        }

        .btn-home:hover {
            background-color: #218838;
        }
    </style>
</head>

<!-- ======= Body ========= -->
<body>

    <div class="login-container">
        <h1>Admin Login</h1>

        <!-- Display Error Message -->
        <c:if test="${not empty errorMessage}">
            <div class="error-message">
                ${errorMessage}
            </div>
        </c:if>

        <form id="loginForm" action="AdminLogin" method="POST">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" id="email" name="email" placeholder="Enter your email" required>
                <span id="err"></span>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
            </div>

            <button type="submit" class="btn btn-submit btn-primary">Submit</button>
        </form>

        <!-- Home Page Button -->
        <a href="/ECommerceWebApp/index.jsp" class="btn btn-home">Back to Home Page</a>
    </div>

    <footer>
        &copy; 2024 Your Admin Panel. All Rights Reserved for shoesly.
    </footer>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</body>
</html>

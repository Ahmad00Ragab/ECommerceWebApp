<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">

    <!-- CSS Styling -->
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            background-color: #f4f7fc;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background: #fff;
            padding: 30px;
            width: 400px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            font-weight: 600;
            font-size: 14px;
            color: #333;
        }

        .form-group input, .form-group select {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-top: 5px;
            font-size: 14px;
        }

        .form-group input:focus {
            outline: none;
            border-color: #007bff;
        }

        .error-message {
            color: red;
            font-size: 12px;
            display: none;
        }

        .form-group input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
            transition: background-color 0.3s;
        }

        .form-group input[type="submit"]:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>

<div class="form-container">
    <h2>Register</h2>
    <form id="registrationForm" action="register" method="POST" onsubmit="return validateForm()">

        <!-- Username -->
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required>
        </div>

        <!-- First Name -->
        <div class="form-group">
            <label for="firstname">First Name</label>
            <input type="text" id="firstname" name="firstname" required>
        </div>

        <!-- Last Name -->
        <div class="form-group">
            <label for="lastname">Last Name</label>
            <input type="text" id="lastname" name="lastname" required>
        </div>

        <!-- Birthdate -->
        <div class="form-group">
            <label for="birthdate">Birthdate</label>
            <input type="date" id="birthdate" name="birthdate" required>
        </div>

        <!-- Email -->
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
            <span id="emailError" class="error-message">Please enter a valid email address.</span>
        </div>

        <!-- Password -->
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
            <span id="passwordError" class="error-message">Password must be at least 8 characters and contain a number.</span>
        </div>

        <!-- Confirm Password -->
        <div class="form-group">
            <label for="confirmPassword">Confirm Password</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
            <span id="confirmPasswordError" class="error-message">Passwords do not match.</span>
        </div>

        <!-- Country -->
        <div class="form-group">
            <label for="country">Country</label>
            <input type="text" id="country" name="country" required>
        </div>

        <!-- City -->
        <div class="form-group">
            <label for="city">City</label>
            <input type="text" id="city" name="city" required>
        </div>

        <!-- Street -->
        <div class="form-group">
            <label for="street">Street</label>
            <input type="text" id="street" name="street" required>
        </div>

        <div class="form-group">
            <label for="phone">Phone</label>
            <input type="tel" id="phone" name="phone" required>
        </div>

        <!-- Credit Limit -->
        <div class="form-group">
            <label for="creditLimit">Credit Limit</label>
            <input type="number" id="creditLimit" name="creditLimit" required>
        </div>

        <!-- Submit Button -->
        <div class="form-group">
            <input type="submit" value="Register">
        </div>
    </form>
</div>

<!-- JavaScript Validation -->
<script>
    function validateForm() {
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        // Email validation
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!emailPattern.test(email)) {
            document.getElementById('emailError').style.display = 'block';
            return false;
        } else {
            document.getElementById('emailError').style.display = 'none';
        }

        // Password validation
        const passwordPattern = /^(?=.*[0-9]).{8,}$/;
        if (!passwordPattern.test(password)) {
            document.getElementById('passwordError').style.display = 'block';
            return false;
        } else {
            document.getElementById('passwordError').style.display = 'none';
        }

        // Confirm password validation
        if (password !== confirmPassword) {
            document.getElementById('confirmPasswordError').style.display = 'block';
            return false;
        } else {
            document.getElementById('confirmPasswordError').style.display = 'none';
        }

        return true; // Allow form submission if all validations pass
    }
</script>

</body>
</html>

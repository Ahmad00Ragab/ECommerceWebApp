<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Email Verification</title>

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

        .form-group input {
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

        .error-message {
            color: red;
            font-size: 12px;
            display: none;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Email Verification</h2>

    <form action="verify" method="POST" onsubmit="return validateForm()">
        <!-- Verification Code -->
        <div class="form-group">
            <label for="verificationCode">Enter Verification Code</label>
            <input type="text" id="verificationCode" name="verificationCode" required>
            <span id="verificationError" class="error-message">Invalid verification code.</span>
        </div>

        <!-- Submit Button -->
        <div class="form-group">
            <input type="submit" value="Verify Email">
        </div>
    </form>
</div>

<!-- JavaScript Validation -->
<script>
    function validateForm() {
        const verificationCode = document.getElementById('verificationCode').value;

        // Basic validation for code (e.g., length should be 6 characters, numeric)
        if (verificationCode.length !== 6 || isNaN(verificationCode)) {
            document.getElementById('verificationError').style.display = 'block';
            return false;
        } else {
            document.getElementById('verificationError').style.display = 'none';
        }

        return true; // Allow form submission if validation passes
    }
</script>

</body>
</html>

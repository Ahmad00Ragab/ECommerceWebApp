<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Account</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .form-group label {
            font-weight: bold;
        }
        fieldset {
            margin-bottom: 20px;
        }
        legend {
            font-size: 1.2rem;
            margin-bottom: 15px;
            font-weight: bold;
        }
        .btn-custom {
            background-color: #ffc107;
            border-color: #ffc107;
            color: white;
        }
        .btn-custom:hover {
            background-color: #e0a800;
            border-color: #e0a800;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">My Account</h1>
    <form action="/update-account" method="POST">
        <!-- Personal Information -->
        <fieldset>
            <legend>Personal Information</legend>
            <div class="mb-3">
                <label for="first-name" class="form-label">First Name</label>
                <input type="text" class="form-control" id="first-name" name="first_name" value="John" required>
            </div>
            <div class="mb-3">
                <label for="last-name" class="form-label">Last Name</label>
                <input type="text" class="form-control" id="last-name" name="last_name" value="Doe" required>
            </div>
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" value="johndoe123" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="john.doe@example.com" required>
            </div>
            <div class="mb-3">
                <label for="birthdate" class="form-label">Birthdate</label>
                <input type="date" class="form-control" id="birthdate" name="birthdate" value="1990-01-01" required>
            </div>
            <div class="mb-3">
                <label for="credit-limit" class="form-label">Credit Limit</label>
                <input type="number" class="form-control" id="credit-limit" name="credit_limit" step="0.01" value="5000.00" required>
            </div>
            <div class="mb-3">
                <label for="country" class="form-label">Country</label>
                <input type="text" class="form-control" id="country" name="country" value="USA" required>
            </div>
            <div class="mb-3">
                <label for="city" class="form-label">City</label>
                <input type="text" class="form-control" id="city" name="city" value="New York" required>
            </div>
            <div class="mb-3">
                <label for="street" class="form-label">Street</label>
                <input type="text" class="form-control" id="street" name="street" value="123 Main St" required>
            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="tel" class="form-control" id="phone" name="phone" value="+1-234-567-8901" required>
            </div>
        </fieldset>

        <!-- Password Update -->
        <fieldset>
            <legend>Update Password</legend>
            <div class="mb-3">
                <label for="current-password" class="form-label">Current Password</label>
                <input type="password" class="form-control" id="current-password" name="current_password" required>
            </div>
            <div class="mb-3">
                <label for="new-password" class="form-label">New Password</label>
                <input type="password" class="form-control" id="new-password" name="new_password" required>
            </div>
        </fieldset>

        <!-- Submit Button -->
        <div class="d-grid">
            <button type="submit" class="btn btn-custom">Update Account</button>
        </div>
    </form>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

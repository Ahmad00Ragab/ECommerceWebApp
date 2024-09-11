<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="css/style.css">
	<title>Registration Form</title>
</head>
<body>
<div class="wrapper" style="background-image: url('img/img.png');">
	<div class="inner">
		<form action="../../ECommerceWebApp/register" method="post">
			<h3>Sign Up</h3>

			<!-- First and Last Name Fields -->
			<div class="form-group">
				<div class="form-wrapper">
					<label for="firstName">First Name</label>
					<div class="form-holder">
						<i class="fas fa-user"></i>
						<input type="text" id="firstName" name="firstName" class="form-control" required placeholder="First Name">
					</div>
				</div>
				<div class="form-wrapper">
					<label for="lastName">Last Name</label>
					<div class="form-holder">
						<i class="fas fa-user"></i>
						<input type="text" id="lastName" name="lastName" class="form-control" required placeholder="Last Name">
					</div>
				</div>
			</div>

			<!-- Username -->
			<div class="form-wrapper">
				<label for="username">Username</label>
				<div class="form-holder">
					<i class="fas fa-user"></i>
					<input type="text" id="username" name="username" class="form-control" required placeholder="Username">
				</div>
			</div>

			<!-- Email -->
			<div class="form-wrapper">
				<label for="email">Email</label>
				<div class="form-holder">
					<i class="fas fa-envelope"></i>
					<input type="email" id="email" name="email" class="form-control" required placeholder="Email Address">
				</div>
			</div>

			<!-- Password -->
			<div class="form-wrapper">
				<label for="password">Password</label>
				<div class="form-holder">
					<i class="fas fa-lock"></i>
					<input type="password" id="password" name="password" class="form-control" required placeholder="Password">
				</div>
			</div>

			<!-- Birthdate -->
			<div class="form-wrapper">
				<label for="birthdate">Date of Birth</label>
				<div class="form-holder">
					<i class="fas fa-calendar-alt"></i>
					<input type="date" id="birthdate" name="birthdate" class="form-control" required>
				</div>
			</div>

			<!-- Credit Limit -->
			<div class="form-wrapper">
				<label for="creditLimit">Credit Limit</label>
				<div class="form-holder">
					<i class="fas fa-dollar-sign"></i>
					<input type="number" id="creditLimit" name="creditLimit" class="form-control" required placeholder="Credit Limit">
				</div>
			</div>

			<!-- Address Section (Country, City, Street) -->
			<div class="form-group">
				<div class="form-wrapper">
					<label for="country">Country</label>
					<div class="form-holder">
						<i class="fas fa-flag"></i>
						<input type="text" id="country" name="country" class="form-control" required placeholder="Country">
					</div>
				</div>
				<div class="form-wrapper">
					<label for="city">City</label>
					<div class="form-holder">
						<i class="fas fa-city"></i>
						<input type="text" id="city" name="city" class="form-control" required placeholder="City">
					</div>
				</div>
			</div>

			<div class="form-wrapper">
				<label for="street">Street</label>
				<div class="form-holder">
					<i class="fas fa-road"></i>
					<input type="text" id="street" name="street" class="form-control" required placeholder="Street Address">
				</div>
			</div>

			<!-- Phone -->
			<div class="form-wrapper">
				<label for="phone">Phone Number</label>
				<div class="form-holder">
					<i class="fas fa-phone"></i>
					<input type="tel" id="phone" name="phone" class="form-control" required placeholder="Phone Number">
				</div>
			</div>

			<!-- Submit Button -->
			<div class="form-end">
				<button type="submit">Register</button>
			</div>
		</form>
	</div>
</div>

<!-- Load Font Awesome icons (for form icons) -->
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>
</html>
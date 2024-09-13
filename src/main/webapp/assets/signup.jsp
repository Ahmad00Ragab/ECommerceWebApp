<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="css/style.css">
	<title>Registration Form</title>

	<link rel="stylesheet" href="css/linearicons.css">
	<link rel="stylesheet" href="css/themify-icons.css">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/magnific-popup.css">
	<link rel="stylesheet" href="css/main.css">
</head>
<body>
<!-- Start Header Area -->
<!-- Start Header Area -->
<header class="header_area sticky-header">
	<div class="main_menu">
		<nav class="navbar navbar-expand-lg navbar-light main_box">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<a class="navbar-brand logo_h" href="../index.jsp"><img src="img/logo.png" alt=""></a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
					<ul class="nav navbar-nav menu_nav ml-auto">
						<li class="nav-item submenu dropdown">
							<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
							   aria-expanded="false">Shop</a>
							<ul class="dropdown-menu">
								<li class="nav-item"><a class="nav-link" href="category.html">Shop Category</a></li>
								<li class="nav-item"><a class="nav-link" href="single-product.html">Product Details</a></li>
								<li class="nav-item"><a class="nav-link" href="checkout.html">Product Checkout</a></li>
								<li class="nav-item"><a class="nav-link" href="cart.html">Shopping Cart</a></li>
								<li class="nav-item"><a class="nav-link" href="confirmation.html">Confirmation</a></li>
							</ul>
						</li>
						<li class="nav-item submenu dropdown">
							<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
							   aria-expanded="false">Blog</a>
							<ul class="dropdown-menu">
								<li class="nav-item"><a class="nav-link" href="blog.html">Blog</a></li>
								<li class="nav-item"><a class="nav-link" href="single-blog.html">Blog Details</a></li>
							</ul>
						</li>
						<li class="nav-item submenu dropdown">
							<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
							   aria-expanded="false">Pages</a>
							<ul class="dropdown-menu">
								<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
								<li class="nav-item"><a class="nav-link" href="tracking.html">Tracking</a></li>
								<li class="nav-item"><a class="nav-link" href="elements.html">Elements</a></li>
							</ul>
						</li>
						<li class="nav-item"><a class="nav-link" href="contact.html">Contact</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="nav-item"><a href="#" class="cart"><span class="ti-bag"></span></a></li>
						<li class="nav-item dropdown">
							<a href="#" class="profile"><span class="lnr lnr-user"></span></a>
							<ul class="dropdown-menu">
								<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
								<li class="nav-item"><a class="nav-link" href="../My-Account.jsp">My Account</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	</div>

</header>

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

<script src="js/vendor/jquery-2.2.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
		crossorigin="anonymous"></script>
<script src="js/vendor/bootstrap.min.js"></script>
<script src="js/jquery.ajaxchimp.min.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/jquery.sticky.js"></script>
<script src="js/nouislider.min.js"></script>
<script src="js/countdown.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<!--gmaps Js-->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
<script src="js/gmaps.min.js"></script>
<script src="js/main.js"></script>

<!-- Load Font Awesome icons (for form icons) -->
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zxx" class="no-js">

<head>
	<!-- Mobile Specific Meta -->
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- Favicon-->
	<link rel="shortcut icon" href="img/fav.png">
	<!-- Author Meta -->
	<meta name="author" content="CodePixar">
	<!-- Meta Description -->
	<meta name="description" content="">
	<!-- Meta Keyword -->
	<meta name="keywords" content="">
	<!-- meta character set -->
	<meta charset="UTF-8">
	<!-- Site Title -->
	<title>Shoesly Shop</title>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<!--
		CSS
		============================================= -->
	<link rel="stylesheet" href="css/linearicons.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/themify-icons.css">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/owl.carousel.css">
	<link rel="stylesheet" href="css/nice-select.css">
	<link rel="stylesheet" href="css/nouislider.min.css">
	<link rel="stylesheet" href="css/ion.rangeSlider.css" />
	<link rel="stylesheet" href="css/ion.rangeSlider.skinFlat.css" />
	<link rel="stylesheet" href="css/magnific-popup.css">
	<link rel="stylesheet" href="css/main.css">
</head>
<style>


.add-to-cart:hover {
background-color: #2980b9; /* Change color on hover */
cursor: pointer; /* Show pointer on hover */
	transition: background-color 1s ease; /* Smooth transition */
	color: white;

}
</style>
<body>
<!-- Start Header Area -->
<%@ include file="common/header.jsp" %>
<!-- End Header Area -->

<!-- start banner Area -->
<section class="banner-area">
	<div class="container">
		<div class="row fullscreen align-items-center justify-content-start">
			<div class="col-lg-12">
				<div class="active-banner-slider owl-carousel">
					<!-- single-slide -->
					<div class="row single-slide align-items-center d-flex">
						<div class="col-lg-5 col-md-6">
							<div class="banner-content">
								<h1>Nike New <br>Collection!</h1>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
									dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation.</p>
								<div class="add-bag d-flex align-items-center">
									<a href="javascript:void(0);" class="add-btn add-to-cart" data-product-id="1"><span class="lnr lnr-cross"></span></a>
									<span class="add-text text-uppercase">Add to Bag</span>
								</div>
							</div>
						</div>
						<div class="col-lg-7">
							<div class="banner-img">
								<img class="img-fluid" src="img/banner/banner-img.png" alt="">
							</div>
						</div>
					</div>
					<!-- single-slide -->
					<div class="row single-slide">
						<div class="col-lg-5">
							<div class="banner-content">
								<h1>Nike New <br>Collection!</h1>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
									dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation.</p>
								<div class="add-bag d-flex align-items-center">
									<a href="javascript:void(0);" class="add-btn add-to-cart" data-product-id="1"><span class="lnr lnr-cross"></span></a>
									<span class="add-text text-uppercase">Add to Bag</span>
								</div>
							</div>
						</div>
						<div class="col-lg-7">
							<div class="banner-img">
								<img class="img-fluid" src="img/banner/banner-img.png" alt="">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- End banner Area -->

<!-- start features Area -->
<section class="features-area section_gap">
	<div class="container">
		<div class="row features-inner">
			<!-- single features -->
			<div class="col-lg-3 col-md-6 col-sm-6">
				<div class="single-features">
					<div class="f-icon">
						<img src="img/features/f-icon1.png" alt="">
					</div>
					<h6>Free Delivery</h6>
					<p>Free Shipping on all order</p>
				</div>
			</div>
			<!-- single features -->
			<div class="col-lg-3 col-md-6 col-sm-6">
				<div class="single-features">
					<div class="f-icon">
						<img src="img/features/f-icon2.png" alt="">
					</div>
					<h6>Return Policy</h6>
					<p>Free Shipping on all order</p>
				</div>
			</div>
			<!-- single features -->
			<div class="col-lg-3 col-md-6 col-sm-6">
				<div class="single-features">
					<div class="f-icon">
						<img src="img/features/f-icon3.png" alt="">
					</div>
					<h6>24/7 Support</h6>
					<p>Free Shipping on all order</p>
				</div>
			</div>
			<!-- single features -->
			<div class="col-lg-3 col-md-6 col-sm-6">
				<div class="single-features">
					<div class="f-icon">
						<img src="img/features/f-icon4.png" alt="">
					</div>
					<h6>Secure Payment</h6>
					<p>Free Shipping on all order</p>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- end features Area -->

<!-- Start category Area -->
<section class="category-area">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-12">
				<div class="row">
					<div class="col-lg-8 col-md-8">
						<div class="single-deal">
							<div class="overlay"></div>
							<img class="img-fluid w-100" src="img/category/c1.jpg" alt="">
							<a href="img/category/c1.jpg" class="img-pop-up" target="_blank">
								<div class="deal-details">
									<h6 class="deal-title">Sneaker for Sports</h6>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-4 col-md-4">
						<div class="single-deal">
							<div class="overlay"></div>
							<img class="img-fluid w-100" src="img/category/c2.jpg" alt="">
							<a href="img/category/c2.jpg" class="img-pop-up" target="_blank">
								<div class="deal-details">
									<h6 class="deal-title">Sneaker for Sports</h6>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-4 col-md-4">
						<div class="single-deal">
							<div class="overlay"></div>
							<img class="img-fluid w-100" src="img/category/c3.jpg" alt="">
							<a href="img/category/c3.jpg" class="img-pop-up" target="_blank">
								<div class="deal-details">
									<h6 class="deal-title">Product for Couple</h6>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-8 col-md-8">
						<div class="single-deal">
							<div class="overlay"></div>
							<img class="img-fluid w-100" src="img/category/c4.jpg" alt="">
							<a href="img/category/c4.jpg" class="img-pop-up" target="_blank">
								<div class="deal-details">
									<h6 class="deal-title">Sneaker for Sports</h6>
								</div>
							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-md-6">
				<div class="single-deal">
					<div class="overlay"></div>
					<img class="img-fluid w-100" src="img/category/c5.jpg" alt="">
					<a href="img/category/c5.jpg" class="img-pop-up" target="_blank">
						<div class="deal-details">
							<h6 class="deal-title">Sneaker for Sports</h6>
						</div>
					</a>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- End category Area -->


<!-- end product Area -->

<!-- Start exclusive deal Area -->
<section class="exclusive-deal-area">
	<div class="container-fluid">
		<div class="row justify-content-center align-items-center">
			<div class="col-lg-6 no-padding exclusive-left">
				<div class="row clock_sec clockdiv" id="clockdiv">
					<div class="col-lg-12">
						<h1>Exclusive Hot Deal Ends Soon!</h1>
						<p>Who are in extremely love with eco friendly system.</p>
					</div>
					<div class="col-lg-12">
						<div class="row clock-wrap">
							<div class="col clockinner1 clockinner">
								<h1 class="days">150</h1>
								<span class="smalltext">Days</span>
							</div>
							<div class="col clockinner clockinner1">
								<h1 class="hours">23</h1>
								<span class="smalltext">Hours</span>
							</div>
							<div class="col clockinner clockinner1">
								<h1 class="minutes">47</h1>
								<span class="smalltext">Mins</span>
							</div>
							<div class="col clockinner clockinner1">
								<h1 class="seconds">59</h1>
								<span class="smalltext">Secs</span>
							</div>
						</div>
					</div>
				</div>
				<a href="products" class="primary-btn">Shop Now</a>
			</div>
			<div class="col-lg-6 no-padding exclusive-right">
				<div class="active-exclusive-product-slider">
					<!-- single exclusive carousel -->
					<div class="single-exclusive-slider">
						<img class="img-fluid" src="img/product/e-p1.png" alt="">
						<div class="product-details">
							<div class="price">
								<h6>$120.00</h6>
								<h6 class="l-through">$210.00</h6>
							</div>
							<h4>addidas New Hammer sole
								for Sports person</h4>
							<div class="add-bag d-flex align-items-center justify-content-center">
								<a href="javascript:void(0);" class="add-btn add-to-cart" data-product-id="3"><span class="ti-bag"></span></a>
								<span class="add-text text-uppercase">Add to Bag</span>
							</div>
						</div>
					</div>
					<!-- single exclusive carousel -->
					<div class="single-exclusive-slider">
						<img class="img-fluid" src="img/product/e-p1.png" alt="">
						<div class="product-details">
							<div class="price">
								<h6>$120.00</h6>
								<h6 class="l-through">$210.00</h6>
							</div>
							<h4>addidas New Hammer sole
								for Sports person</h4>
							<div class="add-bag d-flex align-items-center justify-content-center">
								<a href="javascript:void(0);" class="add-btn add-to-cart" data-product-id="3"><span class="ti-bag"></span></a>
								<span class="add-text text-uppercase">Add to Bag</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- End exclusive deal Area -->

<!-- Start brand Area -->
<section class="brand-area section_gap">
	<div class="container">
		<div class="row">
			<a class="col single-img" href="#">
				<img class="img-fluid d-block mx-auto" src="img/brand/1.png" alt="">
			</a>
			<a class="col single-img" href="#">
				<img class="img-fluid d-block mx-auto" src="img/brand/2.png" alt="">
			</a>
			<a class="col single-img" href="#">
				<img class="img-fluid d-block mx-auto" src="img/brand/3.png" alt="">
			</a>
			<a class="col single-img" href="#">
				<img class="img-fluid d-block mx-auto" src="img/brand/4.png" alt="">
			</a>
			<a class="col single-img" href="#">
				<img class="img-fluid d-block mx-auto" src="img/brand/5.png" alt="">
			</a>
		</div>
	</div>
</section>
<!-- End brand Area -->
<!-- Custom JavaScript for AJAX -->

<script>
	$(document).ready(function() {
		$('.add-to-cart').click(function() {
			var productId = $(this).data('product-id');

			$.ajax({
				url: 'cart', // Ensure this URL is correct
				method: 'POST',
				data: {
					action: 'add',
					productId: productId
				},
				success: function(response) {
					// Create a temporary DOM element to parse the response HTML
					var tempDiv = document.createElement('div');
					tempDiv.innerHTML = response;

					// Check if the response contains an error page (for example, the h1 element with "Oops!")
					var errorHeading = $(tempDiv).find('h1').text();
					if (errorHeading === "Oops! Something went wrong.") {
						// Handle the error case
						alert("Product is Coming Soon!");
					} else {
						// Handle the success case (you might need to customize this based on your real success response)
						alert("Item added to cart successfully!");
					}
				},
				error: function(xhr, status, error) {
					if (xhr.status === 401) { // If status is 401 Unauthorized
						alert("Please log in to add items to the cart.");
						window.location.href = "login"; // Redirect to login page
					} else {
						// Other error
						alert("Error: " + (xhr.responseText || "Failed to add item to cart. Please try again."));
					}
				}
			});
		});
	});

</script>
<!-- start footer Area -->

<%@ include file="common/footer.jsp" %>
</body>

</html>
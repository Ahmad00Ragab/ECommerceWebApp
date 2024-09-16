<header class="header_area sticky-header">
	<div class="main_menu">
		<nav class="navbar navbar-expand-lg navbar-light main_box">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<a class="navbar-brand logo_h" href="/ECommerceWebApp/"><img src="<%= request.getContextPath() %>/img/logo.png" alt=""></a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
					<ul class="nav navbar-nav menu_nav ml-auto">
						<li class="nav-item submenu dropdown active">
							<a href="products" class="nav-link dropdown-toggle">Shop</a>
							<ul class="dropdown-menu">
							</ul>
						</li>
						<li class="nav-item submenu dropdown">
							<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
							   aria-expanded="false">Blog</a>
							<ul class="dropdown-menu">
								<li class="nav-item"><a class="nav-link" href="blog.jsp">Blog</a></li>
							</ul>
						</li>
						<li class="nav-item submenu dropdown">
							<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
							   aria-expanded="false">Pages</a>
							<ul class="dropdown-menu">
								<%--								<li class="nav-item"><a class="nav-link" href="login">Login</a></li>--%>
							</ul>
						</li>
						<li class="nav-item"><a class="nav-link" href="contact.jsp">Contact</a></li>

					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li class="nav-item">
							<button class="search"><span class="lnr lnr-magnifier" id="search"></span></button>
						</li>
						<li class="nav-item dropdown">
							<a href="cart?action=listByUser" class="cart">
								<span class="ti-bag"></span>
							</a>
							<ul class="dropdown-menu">
								<li class="nav-item"><a class="nav-link" href="cart?action=listByUser">Cart</a></li>
								<li class="nav-item"><a class="nav-link" href="checkout">Checkout</a></li>
							</ul>
						</li>
						<li class="nav-item dropdown">
							<a href="userAcc" class="profile"><span class="lnr lnr-user"></span></a>
							<ul class="dropdown-menu">
								<li class="nav-item"><a class="nav-link" href="/ECommerceWebApp/login">Login</a></li>
								<li class="nav-item"><a class="nav-link" href="userAcc">My Account</a></li>
							</ul>
						</li>

					</ul>
				</div>
			</div>
		</nav>
	</div>
	<div class="search_input" id="search_input_box">
		<div class="container">
			<form class="d-flex justify-content-between">
				<input type="text" name="searchShoes" class="form-control" id="searchShoes" placeholder="Search Here">
				<button type="button" class="btn"></button>
				<span class="lnr lnr-cross" id="close_search" title="Close Search"></span>
			</form>
		</div>
	</div>
</header>
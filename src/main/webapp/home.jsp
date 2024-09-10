<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="gov.iti.jets.dtos.ProductDto" %>
<%@ page import="java.util.Set" %>

<html lang="en">
<head>
    <title>Products</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* General styling */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }

        /* Navbar and search bar */
        .navbar {
            background-color: #fff;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .navbar h2 {
            margin: 0;
            font-size: 24px;
        }

        .navbar form {
            width: 50%;
        }

        .navbar input[type="search"] {
            width: 80%;
            padding: 8px;
            font-size: 16px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .navbar button {
            padding: 8px 16px;
            font-size: 16px;
            background-color: #000;
            color: #fff;
            border: none;
            cursor: pointer;
        }

        /* Filter section */
        .filters {
            display: flex;
            justify-content: space-between;
            padding: 20px;
            align-items: center;
            background-color: #fff;
        }

        .filters select {
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        /* Product grid styling */
        .product-category {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            gap: 20px;
            padding: 20px;
            margin: 0 auto;
            max-width: 1200px;
        }

        .product-item {
            background-color: #fff;
            border-radius: 10px;
            padding: 20px;
            text-align: center;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .product-item img {
            width: 100%;
            height: auto;
            border-radius: 10px;
        }

        .product-name {
            font-size: 18px;
            margin: 10px 0;
            font-weight: bold;
        }

        .product-price, .product-old-price, .product-discount {
            font-size: 16px;
        }

        .product-old-price {
            text-decoration: line-through;
            color: #999;
        }

        .product-discount {
            color: red;
        }

        .product-actions {
            margin-top: 10px;
        }

        .product-actions a {
            text-decoration: none;
            color: #007BFF;
            font-weight: bold;
        }

        .no-products {
            text-align: center;
            font-size: 18px;
            color: #999;
            padding: 50px;
        }

        /* Pagination styling */
        .pagination {
            display: flex;
            justify-content: center;
            padding: 20px;
            background-color: #fff;
        }

        .pagination a {
            margin: 0 5px;
            padding: 10px 15px;
            background-color: #000;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }

        .pagination a.disabled {
            background-color: #ccc;
            cursor: not-allowed;
        }

    </style>
</head>
<body>

<!-- Navbar with search bar -->
<div class="navbar">
    <h2>ECommerceWebApp</h2>
    <form action="home" method="POST">
        <input type="search" name="productName" placeholder="Search products...">
        <button type="submit">Search</button>
    </form>
</div>

<!-- Filters Section -->
<div class="filters">
    <!-- Category Filter -->
    <form method="post" action="home">
        <select name="category">
            <option value="All">All Categories</option>
            <option value="Foods">Foods</option>
            <option value="Electronics">Electronics</option>
            <option value="Clothes">Clothes</option>
            <option value="Shoes">Shoes</option>
            <option value="Furniture">Furniture</option>
        </select>

        <!-- Sort by Price -->
        <select name="priceSorting">
            <option value="lowToHigh">Price: Low to High</option>
            <option value="highToLow">Price: High to Low</option>
        </select>

        <button type="submit">Apply</button>
    </form>
</div>

<!-- Products Section -->
<div class="product-category">
    <%
        Set<ProductDto> products = (Set<ProductDto>) request.getAttribute("homeProducts");
        if (products != null && !products.isEmpty()) {
            for (ProductDto product : products) {
    %>
    <div class="product-item">
        <img src="<%= product.imageUrl() %>" alt="<%= product.name() %>"/>
        <div class="product-name"><%= product.name() %></div>
        <div class="product-price">
            <span class="product-discount">EGP <%= product.price() %></span>
        </div>
        <div class="product-actions">
            <a href="add_to_cart.jsp?productId=<%= product.id() %>">Add to Cart</a>
        </div>
    </div>
    <%
        }
    } else {
    %>
    <p class="no-products">No products available at the moment.</p>
    <%
        }
    %>
</div>

<!-- Pagination -->
<div class="pagination">
    <%
        int currentPage = (int) request.getAttribute("currentPage");
        int totalPages = (int) request.getAttribute("totalPages");

        if (currentPage > 1) {
    %>
    <a href="home?pageNumber=<%= currentPage - 1 %>" class="prev-page">Previous</a>
    <%
    } else {
    %>
    <a class="prev-page disabled">Previous</a>
    <%
        }

        for (int i = 1; i <= totalPages; i++) {
            if (i == currentPage) {
    %>
    <a href="home?pageNumber=<%= i %>" class="current-page"><%= i %></a>
    <%
    } else {
    %>
    <a href="home?pageNumber=<%= i %>"><%= i %></a>
    <%
            }
        }

        if (currentPage < totalPages) {
    %>
    <a href="home?pageNumber=<%= currentPage + 1 %>" class="next-page">Next</a>
    <%
    } else {
    %>
    <a class="next-page disabled">Next</a>
    <%
        }
    %>
</div>

</body>
</html>

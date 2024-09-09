<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="gov.iti.jets.dtos.ProductDto" %>

<html>
<head>
    <title>Products</title>
    <style>
        /* General styling */
        body {
            font-family: Arial, sans-serif;
        }

        .section-title {
            font-size: 24px;
            font-weight: bold;
            margin: 20px 0;
        }

        /* Styling for the product categories */
        .product-category {
            display: flex;
            flex-wrap: wrap;
            margin-bottom: 30px;
        }

        .product-category img {
            width: 150px;
            height: auto;
        }

        /* Styling for product items */
        .product-item {
            width: 23%;
            margin: 1%;
            padding: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .product-item img {
            max-width: 100%;
            height: auto;
        }

        .product-name {
            font-size: 16px;
            margin: 10px 0;
        }

        .product-price {
            color: green;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .product-discount {
            color: red;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .product-old-price {
            text-decoration: line-through;
            color: #999;
            margin-bottom: 10px;
        }

        .product-actions {
            margin-top: 10px;
        }
    </style>
</head>
<body>

<!-- Products Section -->
<div class="section-title">Products</div>
<div class="product-category">
    <%
        List<ProductDto> products = (List<ProductDto>) request.getAttribute("homeProducts");

        if (products != null && !products.isEmpty()) {
            for (ProductDto product : products) {
    %>
    <div class="product-item">
        <img src="<%= product.imageUrl() %>" alt="<%= product.name() %>"/>
        <div class="product-name"><%= product.name() %></div>
        <div class="product-price">EGP <%= product.price() %></div>
        <div class="product-actions">
            <a href="add_to_cart.jsp?productId=<%= product.id() %>">Add to Cart</a>
        </div>
    </div>
    <%
   }
    } else {
    %>
    <p>No products available at the moment.</p>
    <%
        }
    %>
</div>

</body>
</html>

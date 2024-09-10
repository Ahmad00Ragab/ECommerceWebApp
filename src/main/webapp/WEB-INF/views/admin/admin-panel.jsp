<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<%-- ===========  Head =========== --%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Manage Products</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f7f9;
        }

        header {
            background-color: #3a3f51;
            color: white;
            padding: 15px 30px;
            text-align: center;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        header h1 {
            margin: 0;
        }

        header nav ul {
            list-style-type: none;
            margin: 10px 0;
            padding: 0;
        }

        header nav ul li {
            display: inline;
            margin-right: 15px;
        }

        header nav ul li a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }

        main {
            padding: 20px;
        }

        h2 {
            font-size: 1.8em;
            margin-bottom: 15px;
            color: #333;
        }

        button {
            background-color: #3498db;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1rem;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #2980b9;
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
        }

        table th, table td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        table th {
            background-color: #34495e;
            color: white;
        }

        table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .modal {
            display: none;
            position: fixed;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.7);
            justify-content: center;
            align-items: center;
        }

        .modal-content {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            width: 400px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            animation: modalFadeIn 0.3s ease-out;
        }

        @keyframes modalFadeIn {
            from {
                transform: translateY(-100px);
                opacity: 0;
            }
            to {
                transform: translateY(0);
                opacity: 1;
            }
        }

        .close {
            color: red;
            float: right;
            font-size: 28px;
            cursor: pointer;
        }

        footer {
            text-align: center;
            background-color: #3a3f51;
            color: white;
            padding: 10px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
            box-shadow: 0 -4px 6px rgba(0, 0, 0, 0.1);
        }

        label {
            font-size: 1rem;
            margin-bottom: 8px;
            display: block;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        input:focus, select:focus {
            border-color: #3498db;
            outline: none;
        }

        @media (max-width: 768px) {
            table, thead, tbody, th, td, tr {
                display: block;
                width: 100%;
            }

            table tr {
                margin-bottom: 15px;
            }

            table th, table td {
                text-align: right;
                padding-left: 50%;
            }

            table td:before {
                content: attr(data-label);
                position: absolute;
                left: 0;
                padding-left: 15px;
                font-weight: bold;
            }
        }
    </style>
</head>

<%-- ===========  Body =========== --%>
<body>
    <header class="admin-header">
        <h1>Admin Dashboard</h1>
        <nav>
            <ul>
                <li><a href="#">Dashboard</a></li>
                <li><a href="#">Customer Profile</a></li>
                <li><a href="#">Logout</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <section class="manage-products">
            <h2>Manage Products</h2>

            <!-- Add New Product Button -->
            <button id="addProductBtn">Add New Product</button>

            <!-- Product List Table -->
            <div class="product-list">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Category</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody id="productList">
                        <c:forEach var="product" items="${productList}">
                            <tr>
                                <td data-label="ID">${product.id}</td>
                                <td data-label="Name">${product.name}</td>
                                <td data-label="Price">${product.price}</td>
                                <td data-label="Quantity">${product.stock}</td>
                                <td data-label="Category">${product.category.name}</td>
                                <td data-label="Actions">
                                    <!-- Edit button -->
                                    <form action="ProductController" method="get" style="display:inline;">
                                        <input type="hidden" name="action" value="edit">
                                        <input type="hidden" name="id" value="${product.id}">
                                        <button type="submit">Edit</button>
                                    </form>

                                    <!-- Delete button -->
                                    <form action="ProductController" method="post" style="display:inline;">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="id" value="${product.id}">
                                        <button type="submit" onclick="return confirm('Are you sure you want to delete this product?');">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Product Modal for Add/Edit -->
            <div id="productModal" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <form id="productForm" action="ProductController" method="post">
                        <input type="hidden" name="productId" id="productId">

                        <label for="name">Product Name:</label>
                        <input type="text" id="name" name="name" required>

                        <label for="price">Price:</label>
                        <input type="number" id="price" name="price" step="0.01" required>

                        <label for="quantity">Quantity:</label>
                        <input type="number" id="quantity" name="quantity" required>

                        <label for="category">Category:</label>
                        <select id="category" name="category" required>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                        </select>

                        <button type="submit" id="submitBtn">Save</button>
                    </form>
                </div>
            </div>
        </section>
    </main>

    <footer>
        <p>Admin Panel © 2024</p>
    </footer>

    <%-- JavaScript for Modal and Form Handling --%>
    <script>
        document.getElementById("addProductBtn").addEventListener("click", function () {
            clearFormData();
            document.getElementById("productModal").style.display = "flex";
        });

        document.querySelector(".close").addEventListener("click", function () {
            document.getElementById("productModal").style.display = "none";
        });

        function clearFormData() {
            document.getElementById("productId").value = '';
            document.getElementById("name").value = '';
            document.getElementById("price").value = '';
            document.getElementById("quantity").value = '';
            document.getElementById("category").value = '';
        }
    </script>
</body>
</html>
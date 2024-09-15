<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Manage Products</title>
    
    <!-- CSS for Admin Panel -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" />
    <%-- <link rel="stylesheet" href="path/to/admin-panel.css"> <!-- Use your CSS file for the panel --> --%>

    <style>
        body {
            background-color: #f4f7f9;
        }
        /* Sidebar Style */
        .sidebar {
            width: 250px;
            background-color: #343a40;
            position: fixed;
            height: 100vh;
            left: 0;
            top: 0;
            padding-top: 20px;
            color: white;
        }
        .sidebar a {
            color: white;
            padding: 10px;
            display: block;
            text-decoration: none;
        }
        .sidebar a:hover {
            background-color: #495057;
        }
        .main-content {
            margin-left: 250px;
            padding: 20px;
        }
        .card {
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        .price {
            font-size: 1.2em;  /* Larger font size */
            color: #28a745;    /* Green color for the price */
            font-weight: bold; /* Bold font */
        }

        footer {
        text-align: center;
        background-color:  #343a40;
        color: white;
        padding: 10px 0;
        position: fixed;
        bottom: 0;
        width: 100%;
        box-shadow: 0 -4px 6px rgba(0, 0, 0, 0.1);
    }
    </style>
</head>

<body>

    <!-- Sidebar Section -->
    <div class="sidebar">

        <h2>Admin Panel</h2>
        
        <a href="${pageContext.request.contextPath}/ProductController">Manage Products</a>

        <a href="${pageContext.request.contextPath}/AdminController">my profile</a>

        <a href="<c:url value='/user?action=list'/>">Customer Profile</a>
    
        <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>


    </div>


    <!-- Main Content Section -->
    <div class="main-content">
        <div class="container-fluid">
            <!-- Dashboard Widgets -->
            <div class="row">
                <div class="col-md-3">
                    <div class="card p-3">
                        <h5>Product Count</h5>
                        <p><strong>${productList.size()}</strong></p>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card p-3">
                        <h5>Active Categories</h5>
                        <p><strong>${categories.size()}</strong></p>
                    </div>
                </div>
            </div>

            <!-- Manage Products Table -->
            <div class="card mt-4">
                <div class="card-body">
                    <h2>Manage Products</h2>
                    <button class="btn btn-primary mb-3" id="addProductBtn">Add New Product</button>

                    <table class="table table-bordered table-hover">
                        <thead class="thead-dark">
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
                                    <td>${product.id}</td>
                                    <td>${product.name}</td>
                                    <td class="price">$${product.price}</td> <!-- Styled price -->
                                    <td>${product.stock}</td>
                                    <td>${product.category.name}</td>
                                    
                                    <td>
                                        <form action="ProductController" method="get" style="display:inline;">
                                            <input type="hidden" name="action" value="edit">
                                            <input type="hidden" name="id" value="${product.id}">
                                            <button type="submit" class="btn btn-warning">Edit</button>
                                        </form>

                                        <form action="ProductController" method="post" style="display:inline;">
                                            <input type="hidden" name="action" value="delete">
                                            <input type="hidden" name="id" value="${product.id}">
                                            <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this product?');">Delete</button>
                                        </form>
                                    </td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>


  <!-- Product Modal -->
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
            
            <!-- New Product Image URL Field -->
            <label for="imageUrl">Product Image URL:</label>
            <input type="url" id="imageUrl" name="imageUrl" required>
            
            <div class="text-right" style="margin-right: 20px;">
                <button type="submit" class="btn btn-success mt-3">Save</button>
            </div>
        </form>
    </div>
</div>

    <footer>
        &copy; 2024 Your Admin Panel. All Rights Reserved for shoesly.
    </footer>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        // Modal open and close logic
        document.getElementById("addProductBtn").addEventListener("click", function () {
            clearFormData();
            document.getElementById("productModal").style.display = "block";
        });
        document.querySelector(".close").addEventListener("click", function () {
            document.getElementById("productModal").style.display = "none";
        });

        function clearFormData() {
            document.getElementById("productId").value = '';   // Clear hidden productId field
            document.getElementById("name").value = '';        // Clear product name field
            document.getElementById("price").value = '';       // Clear price field
            document.getElementById("quantity").value = '';    // Clear quantity field
            document.getElementById("category").value = '';    // Clear category selection
            document.getElementById("imageUrl").value = '';    // Clear image URL field
        }
    </script>
</body>
</html>

















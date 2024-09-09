<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Manage Products</title>
    <link rel="stylesheet" href="admin-styles.css">
</head>

<body>
    <header>
        <h1>Product Management</h1>
    </header>

    <section>
        <button id="addProductBtn">Add New Product</button>
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
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.price}</td>
                        <td>${product.stock}</td>
                        <td>${product.category.name}</td>
                        <td>
                            <button onclick="editProduct(${product.id})">Edit</button>
                            <button onclick="deleteProduct(${product.id})">Delete</button>
                        </td>
                    </tr>
                </c:forEach>    
            </tbody>
        </table>
    </section>

    
    <!-- Modal for Adding/Editing Products -->
    <div id="productModal" class="modal" style="display: none;">
        <div class="modal-content">
            <span class="close">&times;</span>
            <form id="productForm" action="ProductController" method="post">
                <!-- Form Fields -->
                <input type="hidden" name="productId" id="productId">
                <label for="name">Name:</label>
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
            
            <%-- <form id="productForm" action="ProductController" method="post">
                <input type="hidden" name="productId" id="productId">
                
                <label for="name">Name:</label>
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
                </select> --%>                
                <button type="submit" id="submitBtn">Save</button>
            </form>
            
        </div>
    </div>
    
    <%-- ==================== The script =====================  --%>
    <%-- <script>


        document.getElementById("productForm").addEventListener("submit", function(event) {
            event.preventDefault();
            if (validateForm()) {
                let formData = new FormData(this);
                fetch('ProductController', { method: 'POST', body: formData })
                    .then(response => response.text())
                    .then(data => {
                        console.log(data);
                        document.getElementById("productModal").style.display = "none";
                        location.reload();
                    })
                    .catch(error => {
                        console.error('Error submitting product:', error);
                        alert("Failed to submit product. Please check the data and try again.");
                    });
            }
        }); 

        function validateForm() {
            let name = document.getElementById("name").value.trim();
            let price = parseFloat(document.getElementById("price").value);
            let quantity = parseInt(document.getElementById("quantity").value);

            if (!name) {
                alert("Please enter a valid product name.");
                return false;
            }
            if (isNaN(price) || price <= 0) {
                alert("Please enter a valid price.");
                return false;
            }
            if (isNaN(quantity) || quantity < 0) {
                alert("Please enter a valid quantity.");
                return false;
            }
            return true;
        }

        function clearFormData() {
            document.getElementById("productId").value = '';
            document.getElementById("name").value = '';
            document.getElementById("price").value = '';
            document.getElementById("quantity").value = '';
            document.getElementById("category").value = '';
        }

        document.getElementById("addProductBtn").addEventListener("click", function () {
            clearFormData();
            document.getElementById("productModal").style.display = "block";
        });

        document.querySelector(".close").addEventListener("click", function () {
            document.getElementById("productModal").style.display = "none";
        });

        function editProduct(productId) {
            fetch(`ProductController?action=fetch&id=${productId}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById("productId").value = data.id;
                    document.getElementById("name").value = data.name;
                    document.getElementById("price").value = data.price;
                    document.getElementById("quantity").value = data.stock;
                    document.getElementById("category").value = data.categoryId;
                    document.getElementById("productModal").style.display = "block";
                })
                .catch(error => {
                    console.error('Error fetching product details:', error);
                    alert("Failed to fetch product details. Please try again.");
                });
        }

        function deleteProduct(productId) {
            if (confirm("Are you sure you want to delete this product?")) {
                fetch(`ProductController?action=delete&id=${productId}`, { method: 'POST' })
                    .then(response => response.text())
                    .then(data => {
                        console.log(data);
                        location.reload();
                    })
                    .catch(error => {
                        console.error('Error deleting product:', error);
                        alert("Failed to delete product. Please try again.");
                    });
            }
        }


        document.getElementById("addProductBtn").addEventListener("click", function () {
            clearFormData();
            document.getElementById("productModal").style.display = "block";
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

    </script> --%>
<%-- </body>
</html> --%> 


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Manage Products</title>
    <link rel="stylesheet" href="admin-styles.css">
</head>

<body>
    <header>
        <h1>Product Management</h1>
    </header>

    <section>
        <button id="addProductBtn">Add New Product</button>
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
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.price}</td>
                        <td>${product.stock}</td>
                        <td>${product.category.name}</td>
                        <td>
                            <button onclick="editProduct(${product.id})">Edit</button>
                            <button onclick="deleteProduct(${product.id})">Delete</button>
                        </td>
                    </tr>
                </c:forEach>    
            </tbody>
        </table>
    </section>

    <!-- Modal for Adding/Editing Products -->
    <div id="productModal" class="modal" style="display: none;">
        <div class="modal-content">
            <span class="close">&times;</span>
            <form id="productForm" action="ProductController" method="post">
                <input type="hidden" name="productId" id="productId">
                
                <label for="name">Name:</label>
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

    <script>
        document.getElementById("addProductBtn").addEventListener("click", function () {
            clearFormData();
            document.getElementById("productModal").style.display = "block";
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

        function editProduct(productId) {
            fetch(`ProductController?action=fetch&id=${productId}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById("productId").value = data.id;
                    document.getElementById("name").value = data.name;
                    document.getElementById("price").value = data.price;
                    document.getElementById("quantity").value = data.stock;
                    document.getElementById("category").value = data.categoryId;
                    document.getElementById("productModal").style.display = "block";
                })
                .catch(error => {
                    console.error('Error fetching product details:', error);
                    alert("Failed to fetch product details. Please try again.");
                });
        }

        function deleteProduct(productId) {
            if (confirm("Are you sure you want to delete this product?")) {
                fetch(`ProductController?action=delete&id=${productId}`, { method: 'POST' })
                    .then(response => response.text())
                    .then(data => {
                        location.reload();
                    })
                    .catch(error => {
                        console.error('Error deleting product:', error);
                        alert("Failed to delete product. Please try again.");
                    });
            }
        }
    </script>
</body>
</html>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html lang="en">

<%--  ============================ Head ==========================  --%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Manage Products</title>
    <link rel="stylesheet" href="admin-styles.css">
</head>


<%--  ============================ Body ==========================  --%>
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
                            <!-- Edit button as form submission -->
                            <form action="ProductController" method="get" style="display:inline;">
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="id" value="${product.id}">
                                <button type="submit">Edit</button>
                            </form>

                            <!-- Delete button as form submission -->
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
    </section>




    <!-- Modal for Adding Products -->
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


<%--  ============================ The Script ==========================  --%>
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
    </script>

</body>
</html>

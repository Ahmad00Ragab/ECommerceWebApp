<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<%-- ===========  Head =========== --%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Product</title>

    <style>
        /* Your CSS styles */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f7f9;
            padding-bottom: 60px; /* Space for the footer */
        }

        footer {
            text-align: center;
            background-color: #3a3f51;
            color: white;
            padding: 10px 0;
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            box-shadow: 0 -4px 6px rgba(0, 0, 0, 0.1);
            z-index: 100;
        }

        section {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            position: relative;
            z-index: 1;
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

        label {
            display: block;
            margin-bottom: 8px;
            font-size: 1rem;
            color: #333;
        }

        input, select, textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
        }

        input:focus, select:focus, textarea:focus {
            border-color: #3498db;
            outline: none;
        }

       .button-container {
            display: flex;
            justify-content: space-between; /* Moves buttons to opposite sides */
            align-items: center;
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

        #cancelBtn {
            background-color: #e74c3c;
        }

        #cancelBtn:hover {
            background-color: #c0392b;
        }
    </style>
    
</head>

<%-- ===========  Body =========== --%>
<body>

    <header>
        <h1>Add New Product</h1>
    </header>

    <%-- ===========  Product Form =========== --%>
    <section>
        <form action="ProductController" method="post">
            <input type="hidden" name="action" value="create">

            <label for="name">Product Name:</label>
            <input type="text" id="name" name="name" class="form-control" required>

            <label for="price">Price:</label>
            <input type="number" id="price" name="price" class="form-control" step="0.01" required>

            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" class="form-control" required>

            <label for="category">Category:</label>
            <select id="category" name="category" class="form-control" required>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>

            <label for="size">Size:</label>
            <input type="number" id="size" name="size" class="form-control" required>
            
            <label for="brand">Brand:</label>
            <input type="text" id="brand" name="brand" class="form-control" required>

            <label for="color">Color:</label>
            <input type="color" id="color" name="color" class="form-control" required>


            <label for="imageUrl">Product Image URL:</label>
            <input type="text" id="imageUrl" name="imageUrl" class="form-control" required>
            
            <label for="description">Product Description:</label>
            <textarea id="description" name="description" class="form-control" required></textarea> 

            <%-- Button container to separate Save and Cancel buttons --%>
            <div class="button-container">
            <button type="submit" class="btn btn-success">Add Product</button>
                <!-- Cancel button -->
                <button type="button" id="cancelBtn" onclick="window.location.href='${pageContext.request.contextPath}/ProductController';">Cancel</button>
            </div>

        </form>
    </section>

    <footer>
        <p>Admin Panel &copy; 2024 Reserved for shoesly</p>
    </footer>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<%-- ===========  Head =========== --%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product</title>
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

        section {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-size: 1rem;
            color: #333;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
        }

        input:focus, select:focus {
            border-color: #3498db;
            outline: none;
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
    </style>
</head>

<%-- ===========  Body =========== --%>
<body>

    <header>
        <h1>Edit Product</h1>
    </header>

    <%-- ===========  Product Form =========== --%>
    <section>
        <form id="productForm" action="ProductController" method="post">
            <input type="hidden" name="productId" id="productId" value="${product.id}">
            
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${product.name}" required>
            
            <label for="price">Price:</label>
            <input type="number" id="price" name="price" step="0.01" value="${product.price}" required>
            
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" value="${product.stock}" required>
        
            <label for="category">Category:</label>
            <select id="category" name="category" required>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}" ${category.id == product.category.id ? 'selected' : ''}>${category.name}</option>
                </c:forEach>
            </select>
            
            <button type="submit" id="submitBtn">Save</button>
        </form>
    </section>

    <footer>
        <p>Admin Panel & 2024</p>
    </footer>

</body>
</html>

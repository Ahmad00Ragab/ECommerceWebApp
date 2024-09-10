<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 


<!DOCTYPE html>
<html lang="en">

<%-- ===========  Head =========== --%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product</title>
    <link rel="stylesheet" href="admin-styles.css">
</head>


<%-- ===========  Body =========== --%>
<body>

    <header>
        <h1>Edit Product</h1>
    </header>

    <%-- ===========  Prdouct_Form =========== --%>
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
</body>
</html>

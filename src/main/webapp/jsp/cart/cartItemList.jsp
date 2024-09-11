<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart Items</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h1>Your Cart</h1>

<!-- Display cart items using JSTL -->
<table>
    <thead>
    <tr>
        <th>Product</th>
        <th>Quantity</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cartItem" items="${cartItems}">
        <tr>
            <td>${cartItem.product.name}</td>
            <td>
                <input type="number" value="${cartItem.quantity}" id="quantity-${cartItem.product.id}">
            </td>
            <td>
                <button onclick="updateCartItem(${cartItem.product.id})">Update</button>
                <button onclick="deleteCartItem(${cartItem.product.id})">Remove</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<button id="clearCart">Clear Cart</button>

<script>
    // Use jQuery and AJAX to handle cart updates
    function updateCartItem(productId) {
        var userId = ${user.id};  // Assuming user is set in the request scope
        var quantity = $('#quantity-' + productId).val();

        $.ajax({
            url: 'cartItem',
            method: 'POST',
            data: {
                action: 'update',
                userId: userId,
                productId: productId,
                quantity: quantity
            },
            success: function () {
                alert('Cart item updated!');
            },
            error: function () {
                alert('Error updating cart item');
            }
        });
    }

    function deleteCartItem(productId) {
        var userId = ${user.id};

        $.ajax({
            url: 'cartItem',
            method: 'GET',
            data: {
                action: 'delete',
                userId: userId,
                productId: productId
            },
            success: function () {
                alert('Cart item removed!');
                location.reload();  // Reload page to refresh the cart list
            },
            error: function () {
                alert('Error deleting cart item');
            }
        });
    }

    $('#clearCart').on('click', function () {
        var userId = ${user.id};

        $.ajax({
            url: 'cartItem',
            method: 'GET',
            data: {
                action: 'clear',
                userId: userId
            },
            success: function () {
                alert('Cart cleared!');
                location.reload();  // Reload page to refresh the cart list
            },
            error: function () {
                alert('Error clearing cart');
            }
        });
    });
</script>
</body>
</html>
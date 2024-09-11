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
                <button onclick="updateCartItem(${cartItem.user.id}, ${cartItem.product.id})">Update</button>
                <button onclick="deleteCartItem(${cartItem.user.id}, ${cartItem.product.id})">Remove</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<button onclick="clearCartItem(${cartItem.user.id})">Clear</button>

<script>
    // Use jQuery and AJAX to handle cart updates
    function updateCartItem(userId, productId) {
        console.log('Update button clicked');
        console.log('userId:', userId, 'productId:', productId);

        var quantity = $('#quantity-' + productId).val();
        console.log('Quantity:', quantity);

        $.ajax({
            url: 'cart',  // URL should point to the CartController
            method: 'GET', // If using GET
            data: {
                action: 'update',
                userId: userId,
                productId: productId,
                quantity: quantity
            },
            success: function () {
                alert('Cart item updated!');
                // Optionally update the DOM here
            },
            error: function (xhr, status, error) {
                console.error('Error updating cart item:', error);
                alert('Error updating cart item');
            }
        });
    }

    function deleteCartItem(userId, productId) {
        $.ajax({
            url: 'cart', // URL should point to the CartController
            method: 'GET', // If using GET
            data: {
                action: 'delete',
                userId: userId,
                productId: productId
            },
            success: function () {
                alert('Cart item removed!');
                // Optionally update the DOM here
            },
            error: function (xhr, status, error) {
                console.error('Error deleting cart item:', error);
                alert('Error deleting cart item');
            }
        });
    }

    function clearCartItem(userId) {
        $.ajax({
            url: 'cart', // URL should point to the CartController
            method: 'GET', // If using GET
            data: {
                action: 'clear',
                userId: userId,
            },
            success: function () {
                alert('Cart cleared!');
                // Optionally update the DOM here
            },
            error: function (xhr, status, error) {
                console.error('Error clearing cart:', error);
                alert('Error clearing cart');
            }
        });
    }

</script>

</body>
</html>
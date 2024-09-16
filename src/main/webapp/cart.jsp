<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" href="img/fav.png">
    <meta charset="UTF-8">
    <title>Shoesly Shop</title>
    <link rel="stylesheet" href="css/linearicons.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/nouislider.min.css">
    <link rel="stylesheet" href="css/ion.rangeSlider.css" />
    <link rel="stylesheet" href="css/ion.rangeSlider.skinFlat.css" />
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
<%@ include file="/common/header.jsp" %>

<%@ include file="/common/notifications.jsp" %>
<!-- Start Banner Area -->
<section class="banner-area organic-breadcrumb">
    <div class="container">
        <div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
            <div class="col-first">
                <h1>Shopping Cart</h1>
                <nav class="d-flex align-items-center">
                    <a href="index.jsp">Home<span class="lnr lnr-arrow-right"></span></a>
                    <a href="cart">Cart</a>
                </nav>
            </div>
        </div>
    </div>
</section>
<!-- End Banner Area -->

<%--<div id="error-container" style="${not empty errors ? 'display:block;' : 'display:none;'}">--%>
<%--    <h4>Errors:</h4>--%>
<%--    <ul id="error-list">--%>
<%--        <c:forEach var="error" items="${errors}">--%>
<%--            <li>${error}</li>--%>
<%--        </c:forEach>--%>
<%--    </ul>--%>
<%--</div>--%>

<!--================Cart Area =================-->
<section class="cart_area">
    <div class="container">
        <div class="cart_inner">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Product</th>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Total</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${cartItems}">
                        <tr>
                            <td>
                                <div class="media">
                                    <div class="d-flex">
                                        <img src="${item.product.imageUrl}" alt="" style="width: 100px; height: 100px;"> <!-- Product image -->
                                    </div>
                                    <div class="media-body">
                                        <p>${item.product.name}</p> <!-- Product name -->
                                    </div>
                                </div>
                            </td>
                            <td>
                                <h5>${item.product.price}</h5> <!-- Product price -->
                            </td>
                            <td>
                                <!-- Quantity input field with AJAX trigger -->
                                <input type="number" class="quantity" data-productid="${item.product.id}" value="${item.quantity}" min="1" style="width: 60px;" />
                            </td>
                            <td>
                                <h5 class="total-price">${item.product.price * item.quantity}</h5> <!-- Total price -->
                            </td>
                            <td>
                                <!-- Delete item button with AJAX trigger -->
                                <button class="btn btn-danger delete-item" data-productid="${item.product.id}">Remove</button>
                            </td>
                        </tr>
                    </c:forEach>

                    <!-- Subtotal -->
                    <tr>
                        <td></td>
                        <td></td>
                        <td><h5>Subtotal</h5></td>
                        <td><h5 id="total-price">${totalPrice}</h5></td>
                    </tr>

                    <tr class="out_button_area">
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <div class="checkout_btn_inner d-flex align-items-center">
                                <a class="gray_btn" href="products">Continue Shopping</a>
                                <a class="primary-btn" href="checkout">Proceed to checkout</a>
                            </div>
                        </td>
                    </tr>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>
<!--================End Cart Area =================-->

<!-- jQuery AJAX to handle quantity updates and delete actions -->
<script>
    $(document).ready(function () {
        // When the quantity input is changed
        $('.quantity').on('input', function () {
            let productId = $(this).data('productid');
            let quantity = $(this).val();

            if (quantity < 1) {
                quantity = 1; // Ensure minimum quantity is 1
                $(this).val(1);
            }

            $.ajax({
                url: 'cart',
                type: 'GET',
                data: {
                    productId: productId,
                    quantity: quantity,
                    action: 'update'
                },
                success: function (response) {
                    // Update total price for the row
                    let row = $('input[data-productid="' + productId + '"]').closest('tr');
                    row.find('.total-price').text(response.rowTotalPrice);

                    // Update the overall cart total price
                    $('#total-price').text(response.totalPrice);
                },
                error: function () {
                    alert('Error updating quantity. Please try again.');
                }
            });
        });

        // Handle delete button click
        $('.delete-item').on('click', function () {
            let productId = $(this).data('productid');
            let row = $(this).closest('tr'); // Get the table row that contains the item to be deleted

            $.ajax({
                url: 'cart',
                type: 'GET',
                data: {
                    productId: productId,
                    action: 'delete'
                },
                success: function (response) {
                    // Remove the deleted item row from the cart table
                    row.remove();

                    // Update the overall cart total price
                    $('#total-price').text(response.totalPrice);
                },
                error: function () {
                    alert('Error deleting item. Please try again.');
                }
            });
        });

        // Handle checkout form submission
        $('#checkout-form').on('submit', function(e) {
            e.preventDefault(); // Prevent default form submission

            $.ajax({
                url: 'checkout',
                type: 'POST',
                data: $(this).serialize(),
                success: function(response) {
                    // Redirect or update page on success
                    window.location.href = 'confirmation.jsp'; // Example redirect
                },
                error: function(xhr) {
                    // Display errors
                    if (xhr.status === 400) {
                        let response = JSON.parse(xhr.responseText);
                        let errors = response.errors;
                        let errorList = $('#error-list');
                        errorList.empty(); // Clear previous errors

                        errors.forEach(function(error) {
                            errorList.append('<li>' + error + '</li>');
                        });

                        // Show the error list
                        $('#error-container').show();
                    } else {
                        alert('An unexpected error occurred. Please try again.');
                    }
                }
            });
        });
    });
</script>

<%@include file="common/footer.jsp"%>
</body>
</html>

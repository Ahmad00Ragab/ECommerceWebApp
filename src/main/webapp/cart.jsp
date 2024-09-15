<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>
    <!-- Mobile Specific Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" href="img/fav.png">
    <meta charset="UTF-8">
    <title>Karma Shop</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/main.css">
</head>

<body>
<%@include file="common/header.jsp"%>

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
                                <!-- Quantity input and update form -->
                                <form action="cart" method="post">
                                    <input type="hidden" name="productId" value="${item.product.id}" />
                                    <input type="number" name="quantity" value="${item.quantity}" min="1" style="width: 60px;" />
                                    <input type="hidden" name="action" value="update" />
                                    <button type="submit" class="btn btn-primary">Update</button>
                                </form>
                            </td>
                            <td>
                                <h5>${item.product.price * item.quantity}</h5> <!-- Total price -->
                            </td>
                            <td>
                                <!-- Remove item form -->
                                <form action="cart" method="post">
                                    <input type="hidden" name="productId" value="${item.product.id}" />
                                    <input type="hidden" name="action" value="delete" />
                                    <button type="submit" class="btn btn-danger">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                    <!-- Subtotal -->
                    <tr>
                        <td></td>
                        <td></td>
                        <td><h5>Subtotal</h5></td>
                        <td><h5>${totalPrice}</h5></td>
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

<script src="js/vendor/jquery-2.2.4.min.js"></script>
<script src="js/vendor/bootstrap.min.js"></script>

<%@include file="common/footer.jsp"%>
</body>
</html>
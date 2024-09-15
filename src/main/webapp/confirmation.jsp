<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zxx" class="no-js">
<head>
    <!-- Your head content remains the same -->
    <title>Shoesly</title>

    <!-- Include external CSS files -->
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
</head>
<body>

<!-- Start Header Area -->
<%@ include file="/common/header.jsp" %>


<!-- Start Banner Area -->
<section class="banner-area organic-breadcrumb">
    <div class="container">
        <div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
            <div class="col-first">
                <h1>Confirmation</h1>
                <nav class="d-flex align-items-center">
                    <a href="index.jsp">Home<span class="lnr lnr-arrow-right"></span></a>
                    <a href="#">Confirmation</a>
                </nav>
            </div>
        </div>
    </div>
</section>
<!-- End Banner Area -->

<!--================Order Details Area =================-->
<section class="order_details section_gap">
    <div class="container">
        <h3 class="title_confirmation">Thank you. Your order has been received.</h3>
        <div class="row order_d_inner">
            <!-- Order Info -->
            <div class="col-lg-4">
                <div class="details_item">
                    <h4>Order Info</h4>
                    <ul class="list">
                        <li><a href="#"><span>Order number</span> : ${order.id}</a></li>
                        <li><a href="#"><span>Date</span> : ${order.dateCreated}</a></li>
                        <li><a href="#"><span>Total</span> : $${order.totalPrice}</a></li>
                    </ul>
                </div>
            </div>

            <!-- Shipping Address -->
            <div class="col-lg-4">
                <div class="details_item">
                    <h4>Shipping Address</h4>
                    <ul class="list">
                        <li><a href="#"><span>Street</span> : ${order.user.street}</a></li>
                        <li><a href="#"><span>City</span> : ${order.user.city}</a></li>
                        <li><a href="#"><span>Country</span> : ${order.user.country}</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Order Details Table -->
        <div class="order_details_table">
            <h2>Order Details</h2>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Product</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Total</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Iterate over the order items -->
                    <c:forEach var="item" items="${order.orderItems}">
                        <tr>
                            <td><p>${item.product.name}</p></td>
                            <td><h5>x ${item.quantity}</h5></td>
                            <td><p>$${item.price.multiply(item.quantity)}</p></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td><h4>Subtotal</h4></td>
                        <td></td>
                        <td><p>$${order.totalPrice}</p></td>
                    </tr>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>
<!--================End Order Details Area =================-->

<!-- Include Footer -->
<%@ include file="/common/footer.jsp" %>

</body>
</html>

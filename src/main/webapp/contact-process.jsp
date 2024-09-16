<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zxx" class="no-js">

<head>
    <!-- Mobile Specific Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta charset="UTF-8">
    <title>Message Received</title>

    <!-- CSS Files -->
    <link rel="stylesheet" href="css/linearicons.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/nouislider.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>

<body>
    <!-- Start Header Area -->
    <%@include file="common/header.jsp" %>
    <!-- End Header Area -->

    <!-- Start Success Message Area -->
    <section class="section_gap">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="success-message text-center">
                        <h1>Thank You!</h1>
                        <p>Your message has been successfully received. We will get back to you as soon as possible.</p>
                        <a href="/ECommerceWebApp/contact.jsp" class="primary-btn mt-3">Submit Another Message</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- End Success Message Area -->

    <!-- Start Footer Area -->
    <%@include file="common/footer.jsp" %>
    <!-- End Footer Area -->

    <!-- Scripts -->
    <script src="js/vendor/jquery-2.2.4.min.js"></script>
    <script src="js/vendor/bootstrap.min.js"></script>
    <script src="js/jquery.ajaxchimp.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/nouislider.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery.magnific-popup.min.js"></script>
    <script src="js/main.js"></script>
</body>

</html>

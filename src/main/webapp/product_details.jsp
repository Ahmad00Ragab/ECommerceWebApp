<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en" class="no-js">

<head>
    <!-- Mobile Specific Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/fav.png">
    <!-- Author Meta -->
    <meta name="author" content="CodePixar">
    <!-- Meta Description -->
    <meta name="description" content="">
    <!-- Meta Keyword -->
    <meta name="keywords" content="">
    <!-- meta character set -->
    <meta charset="UTF-8">
    <!-- Site Title -->
    <title>Karma Shop</title>
    <!--
            CSS
            ============================================= -->
    <link rel="stylesheet" href="css/linearicons.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/nouislider.min.css">
    <link rel="stylesheet" href="css/ion.rangeSlider.css" />
    <link rel="stylesheet" href="css/ion.rangeSlider.skinFlat.css" />
    <link rel="stylesheet" href="css/main.css">

    <style>
        /* Add padding/margin to the image */
        .single-prd-item img {
            margin-top: 30px;
            margin-bottom: 30px;
            padding: 20px;
            max-width: 100%;
        }

        /* Move product details slightly to the right */
        .s_product_text {
            padding-left: 30px;
        }
    </style>
</head>

<body lang="en" class="no-js">
<%@ include file="common/header.jsp" %>
<%@ include file="common/banner.jsp"%>

<div class="container">
    <div class="row s_product_inner">
        <div class="col-lg-6">
            <!-- Product Image Carousel -->
            <div class="s_Product_carousel owl-carousel owl-theme owl-loaded">
                <div class="owl-stage-outer">
                    <div class="owl-stage">
                        <!-- Loop through the images of the product if there are multiple -->
                        <div class="single-prd-item">
                            <img class="img-fluid" src="${product.imageUrl}" alt="${product.name}">
                        </div>
                    </div>
                </div>
                <!-- Carousel navigation dots can be added here if needed -->
                <div class="owl-controls">
                    <div class="owl-dots">
                        <!-- Dynamic dots based on the number of images -->
                        <%-- <c:forEach var="imageUrl" items="${product.imageUrl}">
                            <div class="owl-dot"><span></span></div>
                        </c:forEach>--%>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-5 offset-lg-1">
            <div class="s_product_text">
                <!-- Product Name -->
                <h3>${product.name}</h3>
                <!-- Product Price -->
                <h2><fmt:formatNumber value="${product.price}" type="currency"/></h2>
                <ul class="list">
                    <!-- Product Category -->
                    <li><a class="active" href=""><span>Category</span> : ${product.category.name}</a></li>
                    <li><a class="active" href=""><span>Brand</span> : ${product.brand}</a></li>
                    <li><a class="active" href=""><span>Color</span> : ${product.shoeColor}</a></li>
                    <li><a class="active" href=""><span>Size</span> : ${product.shoeSize}</a></li>
                    <!-- Product Availability -->
                    <li><a class="active" href=""><span>Availability</span> :
                        <c:choose>
                            <c:when test="${product.stock > 1}">
                                In Stock
                            </c:when>
                            <c:otherwise>
                                Out of Stock
                            </c:otherwise>
                        </c:choose></a>
                    </li>
                </ul>
                <!-- Product Description -->
                <p>${product.description}</p>

                <!-- Quantity Input -->
                <div class="product_count">
                    <label for="sst">Quantity:</label>
                    <input type="text" name="qty" id="sst" maxlength="12" value="1" title="Quantity:" class="input-text qty">
                    <button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;" class="increase items-count" type="button">
                        <i class="lnr lnr-chevron-up"></i>
                    </button>
                    <button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) && sst > 0 ) result.value--;return false;" class="reduced items-count" type="button">
                        <i class="lnr lnr-chevron-down"></i>
                    </button>
                </div>

                <!-- Add to Cart Button -->
                <div class="card_area d-flex align-items-center">
                    <a class="primary-btn" href="cart?action=add&productId=${product.id}">Add to Cart</a>
                    <a class="icon_btn" href="#"><i class="lnr lnr-diamond"></i></a>
                    <a class="icon_btn" href="#"><i class="lnr lnr-heart"></i></a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="common/footer.jsp" %>
</body>

</html>
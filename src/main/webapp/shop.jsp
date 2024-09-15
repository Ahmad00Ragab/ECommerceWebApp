<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" href="img/fav.png">
    <meta charset="UTF-8">
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body id="category">

<!-- Include header -->
<%@ include file="/common/header.jsp" %>

<section class="banner-area organic-breadcrumb">
    <div class="container">
        <div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
            <div class="col-first">
                <h1>Shop Category page</h1>
                <nav class="d-flex align-items-center">
                    <a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
                    <a href="products">Shop<span class="lnr lnr-arrow-right"></span></a>
                    <a href="products">Fashion Category</a>
                </nav>
            </div>
        </div>
    </div>
</section>
<div class="container">
    <div class="row">
        <div class="col-xl-3 col-lg-4 col-md-5">
            <!-- Sidebar with filters -->
            <div class="sidebar-filter mt-50">
                <div class="top-filter-head">Product Filters</div>
                <!-- Shoe Sizes -->
                <div class="common-filter">
                    <div class="head">Size</div>
                    <form action="#">
                        <ul>
                            <li class="filter-list">
                                <input class="pixel-radio" type="radio" id="size7" name="size"><label for="size7">7<span>(20)</span></label>
                            </li>
                            <!-- More size options here -->
                        </ul>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-xl-9 col-lg-8 col-md-7">
            <!-- Filter Bar -->
            <div class="filter-bar d-flex flex-wrap align-items-center">
                <div class="sorting">
                    <select>
                        <option value="1">Default sorting</option>
                    </select>
                </div>
                <div class="sorting mr-auto">
                    <select>
                        <option value="12">Show 12</option>
                        <option value="24">Show 24</option>
                    </select>
                </div>

                <!-- Pagination Controls -->
                <div class="pagination">
                    <c:if test="${page > 1}">
                        <a href="?page=${page - 1}" class="prev-arrow"><i class="fa fa-long-arrow-left" aria-hidden="true"></i></a>
                    </c:if>
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="?page=${i}" class="${page == i ? 'active' : ''}">${i}</a>
                    </c:forEach>
                    <c:if test="${page < totalPages}">
                        <a href="?page=${page + 1}" class="next-arrow"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                    </c:if>
                </div>
            </div>

            <!-- Product Listing -->
            <section class="lattest-product-area pb-40 category-list">
                <div class="row">
                    <c:forEach var="product" items="${homeProducts}">
                        <div class="col-lg-4 col-md-6">
                            <div class="single-product">
                                <img class="img-fluid" src="${product.imageUrl}" alt="${product.name}">
                                <div class="product-details">
                                    <h6>${product.name}</h6>
                                    <div class="price">
                                        <h6><fmt:formatNumber value="${product.price}" type="currency"/></h6>
                                    </div>
                                    <div class="prd-bottom">
                                        <!-- Update the add-to-cart link -->
                                        <a href="javascript:void(0);" class="social-info add-to-cart" data-product-id="${product.id}">
                                            <span class="ti-bag"></span><p class="hover-text">add to bag</p>
                                        </a>
                                        <a href="#" class="social-info"><span class="lnr lnr-heart"></span><p class="hover-text">Wishlist</p></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </section>

            <!-- Pagination (duplicate for bottom) -->
            <div class="filter-bar d-flex flex-wrap align-items-center">
                <div class="sorting mr-auto">
                    <select>
                        <option value="12">Show 12</option>
                        <option value="24">Show 24</option>
                    </select>
                </div>
                <div class="pagination">
                    <c:if test="${page > 1}">
                        <a href="?page=${page - 1}" class="prev-arrow"><i class="fa fa-long-arrow-left" aria-hidden="true"></i></a>
                    </c:if>
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="?page=${i}" class="${page == i ? 'active' : ''}">${i}</a>
                    </c:forEach>
                    <c:if test="${page < totalPages}">
                        <a href="?page=${page + 1}" class="next-arrow"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- AJAX Script for Adding Product to Cart -->
<script>
    $(document).ready(function() {
        $('.add-to-cart').click(function() {
            var productId = $(this).data('product-id');

            $.ajax({
                url: 'cart', // Ensure this URL is correct
                method: 'POST',
                data: {
                    action: 'add',
                    productId: productId
                },
                success: function(response) {
                    // Success message
                    alert(response.message || "Item added to cart successfully!");
                },
                error: function(xhr, status, error) {
                    if (xhr.status === 401) { // If status is 401 Unauthorized
                        alert("Please log in to add items to the cart.");
                        window.location.href = "login"; // Redirect to login page
                    } else {
                        // Other error
                        alert("Error: " + (xhr.responseText || "Failed to add item to cart. Please try again."));
                    }
                }
            });
        });
    });

</script>

<!-- Include footer -->
<%@ include file="/common/footer.jsp" %>

</body>
</html>

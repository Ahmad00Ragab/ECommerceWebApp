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
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/nouislider.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/main.css">
</head>

<body id="category">
<div class="container">
    <div class="row">
        <div class="col-xl-3 col-lg-4 col-md-5">
            <!-- Dynamic categories -->

            <!-- Product Filters -->
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
                            <li class="filter-list">
                                <input class="pixel-radio" type="radio" id="size8" name="size"><label for="size8">8<span>(25)</span></label>
                            </li>
                            <li class="filter-list">
                                <input class="pixel-radio" type="radio" id="size9" name="size"><label for="size9">9<span>(18)</span></label>
                            </li>
                            <li class="filter-list">
                                <input class="pixel-radio" type="radio" id="size10" name="size"><label for="size10">10<span>(30)</span></label>
                            </li>
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
                        <!-- Add sorting logic here -->
                    </select>
                </div>
                <div class="sorting mr-auto">
                    <select>
                        <option value="12">Show 12</option>
                        <option value="24">Show 24</option>
                        <!-- Add logic to show a different number of products -->
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

            <!-- Dynamic Product Listing -->
            <section class="lattest-product-area pb-40 category-list">
                <div class="row">
                    <c:forEach var="product" items="${homeProducts}">
                        <div class="col-lg-4 col-md-6">
                            <div class="single-product">
                                <a href="details?productId=${product.id}">
                                    <img class="img-fluid" src="${product.imageUrl}" alt="${product.name}">
                                </a>
                                <div class="product-details">
                                    <h6>${product.name}</h6>
                                    <div class="price">
                                        <h6><fmt:formatNumber value="${product.price}" type="currency"/></h6>
                                    </div>
                                    <div class="prd-bottom">
                                        <a href="cart?action=add&productId=${product.id}" class="social-info"><span class="ti-bag"></span><p class="hover-text">add to bag</p></a>
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

</body>
</html>

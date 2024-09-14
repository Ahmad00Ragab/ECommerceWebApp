<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
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

    <link rel="stylesheet" href="css/linearicons.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/nouislider.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/main.css">
</head>

<body lang="en" class="no-js">
<%@include file="common/header.jsp" %>

<!-- Start Banner Area -->
<section class="banner-area organic-breadcrumb">
    <div class="container">
        <div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
            <div class="col-first">
                <h1>Shop</h1>
                <nav class="d-flex align-items-center">
                    <a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
                    <a href="#">Shop<span class="lnr lnr-arrow-right"></span></a>
                    <a href="home">Fashon Category</a>
                </nav>
            </div>
        </div>
    </div>
</section>

<div class="container">
    <div class="row">
        <div class="col-xl-3 col-lg-4 col-md-5">
            <!-- Dynamic categories -->
            <div class="sidebar-categories">
                <div class="head">Browse Categories</div>
                <form action="home" method="GET" id="filterForm">
                    <input type="hidden" name="searchShoes" value="${param.searchShoes}" />
                    <ul class="main-categories">
                        <li class="main-nav-list">

                            <input  class="pixel-radio" type="radio" id="all" name="category" value="ALL">
                            <label for="all">ALL</label>
                        </li>
                        <c:forEach var="category" items="${categories}">
                            <li class="main-nav-list">
                                <input class="pixel-radio" type="radio" id="${category.name}" name="category" value="${category.name}">
                                <label for="${category.name}">${category.name.toUpperCase()}</label>
                            </li>
                        </c:forEach>
                    </ul>

                    <!-- Product Filters -->
                    <div class="sidebar-filter mt-50">
                        <div class="top-filter-head">Product Filters</div>

                        <!-- Color Filter -->
                        <div class="common-filter">
                            <div class="top-filter-head">Color</div>
                            <ul>
                                <li class="filter-list">
                                    <input class="pixel-radio" type="radio" id="black" name="color" value="black">
                                    <label for="black">Black</label>
                                </li>
                                <li class="filter-list">
                                    <input class="pixel-radio" type="radio" id="blackleather" name="color" value="blackleather">
                                    <label for="blackleather">White</label>
                                </li>
                                <li class="filter-list">
                                    <input class="pixel-radio" type="radio" id="blackred" name="color" value="blackred">
                                    <label for="blackred">Black with Red</label>
                                </li>
                                <li class="filter-list">
                                    <input class="pixel-radio" type="radio" id="gold" name="color" value="gold">
                                    <label for="gold">Red</label>
                                </li>
                                <li class="filter-list">
                                    <input class="pixel-radio" type="radio" id="spacegrey" name="color" value="spacegrey">
                                    <label for="spacegrey">Spacegrey</label>
                                </li>
                                <li class="filter-list">
                                    <input class="pixel-radio" type="radio" id="gray" name="color" value="gray">
                                    <label for="gray">Gray</label>
                                </li>

                                <li class="filter-list">
                                    <input class="pixel-radio" type="radio" id="blue" name="color" value="blue">
                                    <label for="blue">Blue</label>
                                </li>

                            </ul>
                        </div>

                        <!-- Size Filter -->
                        <div class="common-filter">
                            <div class="top-filter-head">Size</div>
                            <ul>
                                <li class="filter-list">
                                    <input class="pixel-radio" type="radio" id="size38" name="size" value="38">
                                    <label for="size38">38</label>
                                </li>
                                <li class="filter-list">
                                    <input class="pixel-radio" type="radio" id="size39" name="size" value="39">
                                    <label for="size39">39</label>
                                </li>
                                <li class="filter-list">
                                    <input class="pixel-radio" type="radio" id="size40" name="size" value="40">
                                    <label for="size40">40</label>
                                </li>
                                <li class="filter-list">
                                    <input class="pixel-radio" type="radio" id="size41" name="size" value="41">
                                    <label for="size41">41</label>
                                </li>
                                <li class="filter-list">
                                    <input class="pixel-radio" type="radio" id="size42" name="size" value="42">
                                    <label for="size42">42</label>
                                </li>
                                <li class="filter-list">
                                    <input class="pixel-radio" type="radio" id="size43" name="size" value="43">
                                    <label for="size43">43</label>
                                </li>
                                <li class="filter-list">
                                    <input class="pixel-radio" type="radio" id="size44" name="size" value="44">
                                    <label for="size44">44</label>
                                </li>
                                <li class="filter-list">
                                    <input class="pixel-radio" type="radio" id="size45" name="size" value="45">
                                    <label for="size45">45</label>
                                </li>

                            </ul>
                        </div>

                        <!-- Min and Max Price Filter -->
                        <div class="common-filter">
                            <div class="top-filter-head">Price Range</div>
                            <ul>
                                <li class="filter-list">
                                    <label for="minPrice">Min Price</label>
                                    <input type="number" id="minPrice" name="minPrice" class="form-control" placeholder="Min" min="0">
                                </li>
                                <li class="filter-list">
                                    <label for="maxPrice">Max Price</label>
                                    <input type="number" id="maxPrice" name="maxPrice" class="form-control" placeholder="Max" min="0">
                                </li>
                            </ul>
                        </div>


                    </div>
                        <div class="apply-filter">
                            <button type="submit" class="btn primary-btn">Apply Filter</button>
                        </div>
                </form>
            </div>
        </div>

        <div class="col-xl-9 col-lg-8 col-md-7">
            <div class="filter-bar d-flex flex-wrap align-items-center">
                <div class="sorting">
                    <select>
                        <option value="1">Default sorting</option>
                        <option value="1">Default sorting</option>
                        <option value="1">Default sorting</option>
                    </select>
                </div>

                <div class="pagination">
                    <c:if test="${currentPage > 1}">
                        <a href="?pageNumber=${currentPage - 1}" class="prev-arrow"><i class="fa fa-long-arrow-left" aria-hidden="true"></i></a>
                    </c:if>

                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="?pageNumber=${i}" class="${currentPage == i ? 'active' : ''}">${i}</a>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <a href="?pageNumber=${currentPage + 1}" class="next-arrow"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                    </c:if>
                </div>
            </div>


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

                                        <a href="" class="social-info">
                                            <span class="ti-bag">
                                            </span>
                                            <p class="hover-text">add to bag</p>
                                        </a>
                                        <a href="" class="social-info">
                                            <span class="lnr lnr-heart"></span>
                                            <p class="hover-text">Wishlist</p>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="filter-bar d-flex flex-wrap align-items-center">
                    <div class="pagination">
                        <c:if test="${currentPage > 1}">
                            <a href="?pageNumber=${currentPage - 1}" class="prev-arrow"><i class="fa fa-long-arrow-left" aria-hidden="true"></i></a>
                        </c:if>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <a href="?pageNumber=${i}" class="${currentPage == i ? 'active' : ''}">${i}</a>
                        </c:forEach>

                        <c:if test="${currentPage < totalPages}">
                            <a href="?pageNumber=${currentPage + 1}" class="next-arrow"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                        </c:if>
                    </div>
                </div>
            </section>
        </div>
</div>
</div>
<%@include file="common/footer.jsp" %>
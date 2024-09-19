<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="gov.iti.jets.models.User" %>

<%
    // Get the user object from the request
    User user = (User) request.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Settings</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- intl-tel-input CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.19/css/intlTelInput.css">

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

    <style>
        .interest-bubble {
            display: inline-block;
            margin: 5px;
            position: relative;
        }

        .interest-bubble input[type="checkbox"] {
            display: none;
        }

        .interest-bubble label {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            padding: 10px 20px;
            background-color: #f8f9fa;
            border: 2px solid #dee2e6;
            border-radius: 25px;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .interest-bubble input[type="checkbox"]:checked + label {
            background-color: #28a745;
            border-color: #28a745;
            color: white;
        }

        body {
            margin: 0;
            padding-top: 200px; /* Adjust this value as needed */
            color: #2e323c;
            background: #f5f6fa;
            position: relative;
            height: 100%;
        }

        .account-settings .user-profile {
            margin: 0 0 1rem 0;
            padding-bottom: 1rem;
            text-align: center;
        }

        .account-settings .user-profile .user-avatar {
            margin: 0 0 1rem 0;
        }

        .account-settings .user-profile .user-avatar img {
            width: 90px;
            height: 90px;
            border-radius: 100px;
        }

        .account-settings .user-profile h5.user-name {
            margin: 0 0 0.5rem 0;
        }

        .account-settings .user-profile h6.user-email {
            margin: 0;
            font-size: 0.8rem;
            font-weight: 400;
            color: #9fa8b9;
        }

        .form-control {
            border: 1px solid #cfd1d8;
            border-radius: 2px;
            font-size: .825rem;
            background: #ffffff;
            color: #2e323c;
        }

        .card {
            background: #ffffff;
            border-radius: 5px;
            border: 0;
            margin-bottom: 1rem;
        }
    </style>
</head>

<body>

<%@ include file="/common/header.jsp" %>

<div class="container">
    <div class="row gutters">
        <!-- Profile Section -->
        <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
            <div class="card h-100">
                <div class="card-body">
                    <div class="account-settings">
                        <div class="user-profile">
                            <div class="user-avatar">
                                <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="User Avatar">
                            </div>
                            <!-- Display Username and Email (Fetched from database) -->
                            <h5 class="user-name">${user.username}</h5>
                            <h6 class="user-email">${user.email}</h6>
                        </div>
                    </div>
                    <!-- Button to View Past Orders -->
                    <div class="mt-3 text-center">
                        <form action="/ECommerceWebApp/userAcc?action=viewOrders" method="POST">
                            <button type="submit" class="btn btn-primary">View Orders</button>
                        </form>
                    </div>

                </div>
            </div>
        </div>

        <!-- Editable Personal Details Form Section -->
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
            <div class="card h-100">
                <div class="card-body">
                    <form action="/ECommerceWebApp/userAcc?action=update" method="POST">
                        <div class="row gutters">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <h6 class="mb-2 text-primary">Personal Details</h6>
                            </div>

                            <!-- First Name and Last Name -->
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="firstName">First Name</label>
                                    <input type="text" class="form-control" id="firstName" name="firstname" value="${user.firstName}" required>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="lastName">Last Name</label>
                                    <input type="text" class="form-control" id="lastName" name="lastname" value="${user.lastName}" required>
                                </div>
                            </div>

                            <!-- Date of Birth -->
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="birthdate">Date of Birth</label>
                                    <input type="date" class="form-control" id="birthdate" name="birthdate" value="${user.birthdate}" required>
                                </div>
                            </div>

                            <!-- Credit Limit -->
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="creditLimit">Credit Limit</label>
                                    <input type="number" class="form-control" id="creditLimit" name="creditlimit" value="${user.creditLimit}" min="0" step="0.01" required>
                                </div>
                            </div>

                            <!-- Country, City, Street, Phone -->
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="country">Country</label>
                                    <input type="text" class="form-control" id="country" name="country" value="${user.country}" required>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="city">City</label>
                                    <input type="text" class="form-control" id="city" name="city" value="${user.city}" required>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="street">Street</label>
                                    <input type="text" class="form-control" id="street" name="street" value="${user.street}" required>
                                </div>
                            </div>

                            <!-- Phone with Country Code Integration -->
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="phone">Phone</label>
                                    <input type="tel" class="form-control" id="phone" name="phone" value="${user.phone}" required>
                                </div>
                            </div>
                        </div>
                        <!-- Interests (Categories) -->
                        <div class="form-group">
                            <label><b>Select Your Interests</b></label>
                            <div class="d-flex flex-wrap">
                                <c:forEach items="${categories}" var="category">
                                    <div class="interest-bubble">
                                        <input type="checkbox"
                                               id="category${category.id}"
                                               name="categories"
                                               value="${category.id}"
                                               <c:if test="${selectedInterestIds.contains(category.id)}">
                                                   checked
                                               </c:if> />
                                        <label for="category${category.id}">${category.name}</label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        
                        
                        

                        <!-- Form Buttons -->
                        <div class="row gutters">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="text-right">
                                    <button type="submit" class="btn btn-primary">Update Profile</button>
                                    <button type="reset" class="btn btn-secondary">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>


		<!-- Separate Password Form Section -->
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">
            <div class="card h-100">
                <div class="card-body">
                    <form action="/ECommerceWebApp/userAcc?action=changePassword" method="POST">
                        <div class="row gutters">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <h6 class="mb-2 text-primary">Change Password</h6>
                            </div>

                            <!-- Old Password Input -->
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="oldPassword">Old Password</label>
                                    <input type="password" class="form-control" id="oldPassword" name="oldPassword" placeholder="Enter old password" required>
                                </div>
                            </div>

                            <!-- New Password Input -->
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="password">New Password</label>
                                    <input type="password" class="form-control" id="password" name="newPassword" placeholder="Enter new password" minlength="6" required>
                                </div>
                            </div>

                            <!-- Confirm Password Input -->
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="confirmPassword">Confirm New Password</label>
                                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm new password" minlength="6" required>
                                </div>
                            </div>
                        </div>

                        <!-- Form Buttons for Password Change -->
                        <div class="row gutters">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="text-right">
                                    <button type="submit" class="btn btn-primary">Update Password</button>
                                    <button type="reset" class="btn btn-secondary">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="/common/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.19/js/intlTelInput.min.js"></script>



<script>

    const phoneInputField = document.querySelector("#phone");
    const phoneInput = window.intlTelInput(phoneInputField, {
        utilsScript: "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.19/js/utils.js", // for formatting/validation etc.
        initialCountry: "auto",
        geoIpLookup: function (callback) {
            fetch('https://ipinfo.io/json?token=c341c7c125c720') // Replace with your own IPinfo token
                    .then((response) => response.json())
                    .then((data) => {
                        callback(data.country);
                    })
                    .catch(() => {
                        callback('us');
                    });
        }
    });
</script>

</body>
</html>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Index Page</title>
</head>
<body>

<h2>Hello World!</h2>

    <section>
        <!-- Form to send a GET request to ProductController -->
        <form action="ProductController" method="get">
            <input type="hidden" name="action" value="viewProducts">
            <button type="submit">Go to Product Management</button>
        </form>

        <!-- Button to go to admin-login.jsp -->
        <form action="GoToAdminLogin" method="get">
            <button type="submit">Go to Admin Login</button>
        </form>

        <!-- Link to list users -->
        <p><a href="<c:url value='/user?action=list'/>">List Users</a></p>

        <!-- Link to view a specific user -->
        <p><a href="<c:url value='/user?action=view&userId=3'/>">View User with ID 3</a></p>


    </section>

<<<<<<< HEAD
=======
<!-- You can add more links as needed -->

<p><a href="<c:url value='/cart?action=listByUser&userId=3'/>">View Cart of User with ID 3</a></p>

>>>>>>> 89090999f01f92703f2815d14b6f9c8b258546aa
</body>
</html>

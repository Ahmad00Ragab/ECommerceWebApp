<<<<<<< HEAD

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Index Page</title>
</head>
<body>
<h2>Hello World!</h2>

<!-- Link to list users -->
<p><a href="<c:url value='/user?action=list'/>">List Users</a></p>

<!-- Link to view a specific user, you might need to replace 'userId' with an actual ID or provide an input form -->
<p><a href="<c:url value='/user?action=view&id=1'/>">View User with ID 1</a></p>

<!-- You can add more links as needed -->
</body>
</html>
=======
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index</title>
</head>

<body>
    <header>
        <h1>Welcome to the Admin Panel</h1>
    </header>


    <section>
        <!-- Form to send a GET request to ProductController -->
        <form action="ProductController" method="get">
            <input type="hidden" name="action" value="viewProducts">
            <button type="submit">Go to Product Management</button>
        </form>

        <!-- Form to send a GET request to ReviewCustomerProfile -->
        <form action="ProductController" method="get">
            <input type="hidden" name="action" value="viewProfile">
            <button type="submit">Go to Customer Profile</button>
        </form>

    </section>
</body>
</html>
>>>>>>> Haroun_Branch2_newStructure

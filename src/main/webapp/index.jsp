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

<!-- Link to list users -->
<p><a href="<c:url value='/user?action=list'/>">List Users</a></p>

<!-- Link to view a specific user, you might need to replace 'userId' with an actual ID or provide an input form -->
<p><a href="<c:url value='/user?action=view&userId=3'/>">View User with ID 3</a></p>

<!-- You can add more links as needed -->
</body>
</html>
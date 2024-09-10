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

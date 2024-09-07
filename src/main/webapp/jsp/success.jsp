<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
<h2>Success</h2>

<p>${successMessage}</p>

<!-- Link to go back to user list or other page -->
<a href="${pageContext.request.contextPath}/user/list">Back to User List</a>
</body>
</html>
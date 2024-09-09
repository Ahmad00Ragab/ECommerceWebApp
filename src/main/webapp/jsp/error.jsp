<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>Oops! Something went wrong.</h1>
<p>${errorMessage}</p>
<a href="${pageContext.request.contextPath}/home">Return to Home</a>
</body>
</html>

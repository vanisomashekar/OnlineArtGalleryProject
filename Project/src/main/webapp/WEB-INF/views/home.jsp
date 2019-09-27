<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<div align="center">
<h1>Online Art Gallery</h1>
<table>
<tr>
<td><a href="${pageContext.request.contextPath}/customer/register">Register</a></td>
</tr>
<tr>
<td><a href="${pageContext.request.contextPath}/customer/login">Login</a></td>
</tr>
</table>
</div>
</body>
</html>

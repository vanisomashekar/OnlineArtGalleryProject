<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<html>
<head>
	<title>Login</title>
</head>
<body>
<div align="center">
	<table>
	<s:form commandName="customerData" method="post">
	<tr>
	<td>Customer Email:</td>
	<td><s:input path="c_email"/></td>
	</tr>
	<tr>
	<td>Customer Password:</td>
	<td><s:input  type="password" path="c_password"/></td>
	</tr>
	<tr>
	<td>Select an Option</td>
	<td><select id="option">
		<option value="Artist">Artist</option>
		<option value="Customer">Customer</option>
		</select>
	</td>
	</tr>
	<tr>
	<td></td>
	<td><input type="submit" value="Login" onclick="document.getElementById('option').value=='Customer' ? this.form.action='${pageContext.request.contextPath}/customer/login': this.form.action='${pageContext.request.contextPath}/artist/login'"/></td>
	</tr>
	<tr>
	<td><a href="${pageContext.request.contextPath}/customer/register">Register</a></td>
	</tr>
	</s:form></table>
	<p style="color:red;">${failed}</p>
</div>
</body>
</html>
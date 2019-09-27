<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	<title>Register</title>
</head>
<body>
<div align="center">
	<table>
	<s:form commandName="customerData" method="post">
	<tr>
	<td>Customer Name:</td>
	<td><s:input path="c_name"/></td>
	<td><s:errors path="c_name" cssStyle="color:red;"/></td>
	</tr>
	<tr>
	<td>Customer Email:</td>
	<td><s:input path="c_email"/></td>
	<td><s:errors path="c_email" cssStyle="color:red;"/></td>
	</tr>
	<tr>
	<td>Customer Password:</td>
	<td><s:input type="password" path="c_password"/></td>
	<td><s:errors path="c_password" cssStyle="color:red;"/></td>
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
	<td><input type="submit" value="Register" onclick="document.getElementById('option').value=='Customer' ? this.form.action='${pageContext.request.contextPath}/customer/register': this.form.action='${pageContext.request.contextPath}/artist/register'"/></td>
	</tr>
	<tr>
<td><a href="${pageContext.request.contextPath}/customer/login">Login</a></td>
</tr>
	</s:form></table>
	<p style="color:red;">${failed}</p>
</div>
</body>
</html>
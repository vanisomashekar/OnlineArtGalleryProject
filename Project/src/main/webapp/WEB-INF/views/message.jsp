<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transactional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<title>Message</title>
</head>
<body>

<h1>Send Message</h1>
<s:form action="${pageContext.request.contextPath}/customer/mess" method="POST" commandName="message1">
<table>
	<tr>
		<td>Artist Name</td>
		<td><s:input path="artist" value="${sessionScope.Currentartist}"/></td>
	</tr>
	<tr>
		<td>Customer Name</td>
		<td><s:input path="customer" value="${sessionScope.customer.getC_name()}"/></td>
	</tr>
	<%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy"); %>
	<tr>
		<td>Message Date</td>
		<td><s:input path="messageDate" value="<%= df.format(new java.util.Date()) %>"/></td>
	</tr>
	<tr>
	<td>Message</td>
	<td><s:textarea path="message"/></td>
	</tr>
	<tr>
		<td colspan="2">
		 <input type="submit" name="action" value="Send"/>
		 </td>
	</tr>
</table>
</s:form>
<p style="color:red;">${failed}</p>
<br>
</body>
</html>
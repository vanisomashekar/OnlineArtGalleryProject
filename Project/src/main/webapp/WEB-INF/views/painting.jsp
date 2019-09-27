<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transactional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<title>Painting management</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/artist/logout"><b>Logout</b></a>
<h1>Painting Management</h1>
<s:form action="${pageContext.request.contextPath}/artist/painting" method="POST" commandName="painting">
<table>
	<tr>
		<td>Painting Name</td>
		<td><s:input path="Name"/></td>
		<td><s:errors path="Name" cssStyle="color:red;"/></td>
	</tr>
	<tr>
		<td>Painting Length</td>
		<td><s:input path="length"/></td>
		<td><s:errors path="length" cssStyle="color:red;"/></td>
	</tr>
	<tr>
		<td>Painting Type</td>
		<td><s:input path="kind"/></td>
		<td><s:errors path="kind" cssStyle="color:red;"/></td>
	</tr>
	<tr>
	<td><s:label path="image">Painting</s:label></td>
	<td><input type="file" name="file" value="file"  accept="image/jpg"/></td>
	<td><c:out value="${sessionScope.error}"/></td>
	<tr>
		<td colspan="2">
		 <input type="submit" name="action" value="Add"/>
		 <input type="submit" name="action" value="View"/>
		 <input type="submit" name="action" value="Searchs"/>
		 <input type="submit" name="action" value="Messages"/>
		 <input type="submit" name="action" value="Sentmessage"/>
		 </td>
	</tr>
</table>
</s:form>
<p style="color:red;">${failed}</p>
<br>
</body>
</html>
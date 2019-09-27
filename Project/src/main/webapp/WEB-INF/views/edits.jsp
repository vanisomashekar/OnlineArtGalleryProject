<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transactional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<title>Painting management</title>
</head>
<body>

<h1>Painting Management</h1>
<s:form action="${pageContext.request.contextPath}/artist/painting" method="POST" commandName="painting">
<table>
	<tr>
	<td><s:input type="hidden" path="PaintingId" value="${sessionScope.search.getPaintingId()}"/></td>
	</tr>
	<tr>
		<td>Painting Name</td>
		<td><s:input path="Name" value="${sessionScope.search.getName()}"/></td>
		<td><s:errors path="Name" cssStyle="color:red;"/></td>
	</tr>
	<tr>
		<td>Painting Length</td>
		<td><s:input path="length" value="${sessionScope.search.getLength()}"/></td>
		<td><s:errors path="length" cssStyle="color:red;"/></td>
	</tr>
	<tr>
		<td>Painting Type</td>
		<td><s:input path="kind" value="${sessionScope.search.getKind()}"/></td>
		<td><s:errors path="kind" cssStyle="color:red;"/></td>
	</tr>
	<tr>
		<td><input type="hidden" name="file" /></td>
	</tr>
		<td>
		 <input type="submit" name="action" value="Edit"/>
		 </td>
		 <td>
		 <input type="submit" name="action" value="Delete"/>
		 </td>
	</tr>
</table>
</s:form>
<a href="${pageContext.request.contextPath}/artist/searchs">Back</a>>
<br>
</body>
</html>
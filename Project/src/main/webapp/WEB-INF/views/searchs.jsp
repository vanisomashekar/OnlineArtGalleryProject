<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<html>
<head>
	<title>Search</title>
</head>
<body>
<h1>Search Paintings</h1>
<s:form action="${pageContext.request.contextPath}/artist/painting" method="POST" commandName="painting">
<table>
<tr>
	<td><s:input path="Name" placeholder="Search by Painting Name"/></td>
	<td><input type="submit" name="action" value="Search"></td>
	<td><input type="hidden" name="file" value="file"/></td>
</tr>
</table>
</s:form>
<p style="color:red;">${failed}</p>
</body>
</html>
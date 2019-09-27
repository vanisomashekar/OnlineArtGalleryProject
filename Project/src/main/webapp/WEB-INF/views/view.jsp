<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transactional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<title>View</title>
</head>
<body>
<h2>Current Paintings</h2>

<table border="1">
            <tbody>
                 	<tr>
	                 	<th>Name</th>
	                 	<th>Kind</th>
	                 	<th>Length</th>
                 	</tr>
            		<c:forEach var="view" items="${sessionScope.view}">
                     <tr>
                     <s:form action="${pageContext.request.contextPath}/artist/painting" method="POST" commandName="painting">
	                    <td><s:input path="Name" value="${view.getName()}" readonly="readonly"/></td>
	                    <td><s:input path="length" value="${view.getLength()}" readonly="readonly"/></td>
	                    </s:form>
	                    <td><img src="data:image/jpg;base64,${view.getEncodedImage()}" width="100" height="100"/></td>
                     </tr>
                 </c:forEach>
                    <a href="${pageContext.request.contextPath}/artist/painting">Back</a>
            </tbody>
</table>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transactional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<title>View</title>
</head>
<body>
<h2>Messages</h2>
<s:form action="${pageContext.request.contextPath}/artist/painting" method="POST" commandName="message">
<table border="1">
            <tbody>
                 	<tr>
	                 	<th>Date</th>
	                 	<th>Customer</th>
	                 	<th>Painting</th>
	                 	<th>Message</th>
                 	</tr>
            		<c:forEach var="sentMessage" items="${sessionScope.sent}">
                     <tr>
	                    <td><c:out value="${sentMessage.getMessageDate()}"/></td>
	                    <td><c:out value="${sentMessage.getCustomer().getC_name()}"/></td>
	                    <td><c:out value="${sentMessage.getPainting().getName()}"/></td>
	                    <td><c:out value="${sentMessage.getMessage()}"/></td>
                     </tr>
                 </c:forEach>
                    <a href="${pageContext.request.contextPath}/artist/seeMessages">Back</a>
            </tbody>
            </table>
</s:form>
</body>
</html>
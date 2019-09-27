<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transactional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<title>Gallery</title>
</head>
<body>
<h2>Gallery</h2>
<br>
<br>
<s:form action="${pageContext.request.contextPath}/customer/mess" method="POST" commandName="message1">
<input type="submit" name="action" value="Recievedmessages"/>
<input type="submit" name="action" value="Sentmessages"/>
<input type="submit" name="action" value="Sortascending"/>
<s:input path="search" placeholder="Search By Painting Type" /><input type="submit" name="action" value="Search"/>
<a href="${pageContext.request.contextPath}/customer/logout"><b>Logout</b></a>
<div class="row">
<c:forEach var="view" items="${sessionScope.views}">
<div class="col-lg-3 col-md-12" style="margin-top:20px;">
	<div class="card border-0">
	<div class="card-header">
	<c:out value="${view.getName()}"/><br>
	</div>
	
	<div class="card-body" >	
	<img src="data:image/jpg;base64,${view.getEncodedImage()}" height="100" width="100"/><br>
		         <c:out value="${view.getKind()}" /><br>
		         <c:out value="${view.getLength()}"/><br>
		         <c:out value="${view.getArtists().getC_name()}"/><br>
		         <a href="${pageContext.request.contextPath}/customer/messag?aname=${view.getArtists().getC_name()}&pname=${view.getName()}">Message Artist</a>
       </div>
      </div>
      </div>
 </c:forEach>
   </div>
   </s:form>
</body>
</html>







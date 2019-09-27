<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Welcome</title>
</head>
<body>

<div>
<div align="right">
<a href="${pageContext.request.contextPath}/artist/logout"><b>Logout</b></a>
</div>
<div align="left">
<h1>Registration Successful</h1><br>
Welcome<b style="color:red;">${artist.c_name}</b></div>
</div>
</body>
</html>
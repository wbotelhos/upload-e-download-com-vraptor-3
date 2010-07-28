<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>http://wbotelhos.com</title>
	</head>
	<body>
		<c:if test="${not empty error}">${error}<br/><br/></c:if>

		<form action="<c:url value="/usuario/foto"/>" enctype="multipart/form-data" method="post">
			Imagem: <input type="file" name="imagem"/> <input type="submit"/>
		</form><br/>

		<a href="<c:url value='/usuario/foto'/>">
			<img src="<c:url value='/usuario/foto'/>" width="90" height="90" border="0"/>
		</a>

		<c:if test="${userSession.user.foto ne 'default.jpg' and not empty userSession.user.foto}">
			<br/><br/><a href="<c:url value="/usuario/foto/remover"/>">Remover</a>
		</c:if>
	</body>
</html>
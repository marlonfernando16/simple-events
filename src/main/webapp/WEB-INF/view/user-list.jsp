<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrado com sucesso</title>
</head>
<body>

	<h1>Usuarios Cadastrados</h1>

	<a href="${pageContext.request.contextPath}/usuario/">Novo Usuario</a>

	<div>${message}${user.nome}</div>


	<ul>
		<c:forEach var="userItem" items="${users}">
			<li>${userItem.id}${userItem.nome}<a href="read/${userItem.id}">Edit</a>
				<a href="delete/${userItem.id}">Delete</a></li>
		</c:forEach>
	</ul>

</body>
</html>
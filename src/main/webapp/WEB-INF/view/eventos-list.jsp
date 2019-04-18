<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listagem de Eventos</title>
</head>
<body>

	<h1>Eventos Cadastrados</h1>
	<c:if test="${not empty evento}">
		<p>Evento cadastrado!</p>
	</c:if>
	
	<c:if test="${empty eventos}">
		<p>Ainda não há eventos cadastrados.</p>
	</c:if>
	
	<ul>
		<c:forEach var="e" items="${eventos}">
			<li>
				<ul>
					<li>Evento: ${e.id}</li>
					<li>Descrição: ${e.descricao}</li>
					<li>Data: ${e.data}</li>
					<li>Local: ${e.local}</li>
				</ul>
			</li>
		</c:forEach>
	</ul>

</body>
</html>
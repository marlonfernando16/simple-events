<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Novo Evento</title>
</head>
<body>

	<form:form action="salvar" method="post" modelAttribute="evento">
		<span>Descrição: </span> <form:input path="descricao"/><form:errors path="descricao" />
		<br>
		<span>Data: </span> <form:input path="data"/><form:errors path="data" />
		<br>
		<span>Local: </span> <form:input path="local"/><form:errors path="local" />
		<br>
		<input type="submit" value="Salvar" />
	</form:form>

</body>
</html>
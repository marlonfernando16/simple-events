<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Usuarios</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath}/usuario/create"
		method="POST" modelAttribute="user" acceptCharset="UTF-8">

		Nome: <form:input path="nome" />
		<form:errors path="nome">
			<c:if test="${not empty user}">
				${user.nome}
			</c:if>
		</form:errors>
		<br />
		Telefone: <form:input path="telefone" />
		<form:errors path="telefone">
			<c:if test="${not empty user}">
				${user.telefone}
			</c:if>
		</form:errors>
		<br />
		Data de Nascimento <form:input path="datanascimento" />
		<form:errors path="datanascimento">
			<c:if test="${not empty user}">
				${user.datanascimento}
			</c:if>
		</form:errors>
		<br />
		E-Mail: <form:input path="email" />
		<form:errors path="email">
			<c:if test="${not empty user}">
				${user.email}
			</c:if>
		</form:errors>
		<br />
		Senha: <form:input path="senha" />
		<form:errors path="senha">
			<c:if test="${not empty user}">
				${user.senha}
			</c:if>
		</form:errors>
		<br />
		<input type="submit" value="Salvar" />
	</form:form>

</body>
</html>
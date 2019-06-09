<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Página principal da aplicação</title>
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</head>

<style>
body {
	background-color: #F8F8F8;
	margin-top: 3%;
}
</style>
<body>
	<div class="valign-wrapper row login-box">
		<div class="col card hoverable s10 pull-s1 m6 pull-m3 l4 pull-l4">
			<div class="card"></div>

			<div
				class="card-action s10 pull-s1 m6 pull-m3 l4 pull-l4 teal lighten-2 white-text blue">
				<h3 class="center">Sign up</h3>
			</div>

			<div class="card-content">
				<c:if test="${not empty message_error }">
					<p class="center" style="color: red">${message_error}</p>
				</c:if>
				<form:form
					action="${pageContext.request.contextPath}/usuario/update/${user.id}"
					method="POST" modelAttribute="user" acceptCharset="UTF-8">

					<div class="input-field col s12">
						<i class="material-icons prefix">account_circle</i>
						<form:input path="nome" class="validate" />
						<form:errors class="center red-text" path="nome" />
						<label for="nome">Name</label>
					</div>
					<div class="input-field col s12">
						<i class="material-icons prefix">phone</i>
						<form:input path="telefone" class="validate" />
						<form:errors class="center red-text" path="telefone" />
						<label for="telefone">Phone</label>
					</div>
					<div class="input-field col s12">
						<i class="material-icons prefix">calendar_today</i>
						<form:input path="datanascimento" type="date" class="validate" />
						<form:errors class="center red-text" path="datanascimento" />
						<label for="datanascimento">Date of birth</label>
					</div>
					<div class="input-field col s12">
						<i class="material-icons prefix">email</i>
						<form:input path="email" class="validate" type="email" />
						<form:errors class="center red-text" path="email" />
						<label for="email">Email</label>
					</div>
					
					<div class="form-field center-align">
						<button class="btn-large ">Salvar</button>
					</div>

				</form:form>
			</div>
		</div>
	</div>
</body>
</html>

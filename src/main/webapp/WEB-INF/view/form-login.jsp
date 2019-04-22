<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	
<style>
	
	body {
		background-color: #F8F8F8 ;
		margin-top: 3%;
		}
</style>
</head>
<body>
	<div class="valign-wrapper row login-box">
		<div class="col card hoverable s10 pull-s1 m6 pull-m3 l4 pull-l4">
			<div class="card"></div>

			<div
				class="card-action s10 pull-s1 m6 pull-m3 l4 pull-l4 teal lighten-2 white-text blue">
				<h3 class="center">Log in</h3>
			</div>

				<div class="card-content">
					<c:if test="${not empty erro }">
						<p class="center" style="color: red">${erro}</p>
					</c:if>
					<c:if test="${not empty message }">
						<p class="center" style="color: green">${message}</p>
					</c:if>
					<form:form action="${pageContext.request.contextPath }/login/valide" method="post">
					<div class="input-field col s12">
							<i class="material-icons prefix">account_circle</i>
							<input type="text" name="login"/><br>
							<label for="login">Login</label>
					</div>
					<div class="input-field col s12">
							<i class="material-icons prefix">lock</i>
						 	<input type="password" name="senha"/><br>
						 	<label for="login">Password</label>
					</div>
							<div class="form-field center-align">
								<button class="btn-large ">Log in</button>
							</div>
							<br>
							<div class ="center">
								Don't have an account yet?<a href="${pageContext.request.contextPath }/usuario/"> Sign up for free.</a>
							</div> 
					</form:form>
			 </div>
        </div>
    </div>
</body>
</html>
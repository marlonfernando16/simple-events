<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Especialidades</title>
<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
<style>
body {
	background-color: #F8F8F8;
}

.i-circle.md-login {
	margin-left: 50px;
	margin-top: 50%;
	font-weight: 900;
	padding: 10px;
	padding-right: 15px;
	padding-left: 15px;
	border-radius: 50%;
	color: black;
}
</style>
</head>
<body>

	<!--menu-->
	<!-- Dropdown Structure -->
	<ul id="dropdown1" class="dropdown-content">
		<li></li>
		<li><a href="${pageContext.request.contextPath }/login/logout">Sair</a></li>
	</ul>
	<nav class="teal lighten-2">
		<div class="nav-wrapper">
			<a href="#!" class="brand-logo">Simple events</a>
			<ul class="right hide-on-med-and-down">
				<li><a href="${pageContext.request.contextPath }/eventos"><i
						class="material-icons left">home</i>Home</a></li>
				<li><a
					href="${pageContext.request.contextPath }/especialidades/form"><i
						class="material-icons left">event</i>Cadastrar Especialidades</a></li>
				<!-- Dropdown Trigger -->
				<c:if test="${user != null}">
					<li><a class="dropdown-trigger" href="#!"
						data-target="dropdown1"><span
							class="i-circle md-login center white ">${fn:toUpperCase(fn:substring(user.email, -1, 1))}</span><i
							class="material-icons right">arrow_drop_down</i></a></li>
				</c:if>
			</ul>
		</div>
	</nav>

	<span style="color: #08c117;">${message_success}</span>
	<span style="color: #e82727;">${message_error}</span>

	<c:if test="${empty especialidades}">
		<p>Ainda n�o h� especialidades cadastradas.</p>
	</c:if>

	<c:forEach var="e" items="${especialidades}">
		<div class="row">
			<div class="col s12 m6">
				<div class="card-panel white hoverable lighten-5 z-depth-1">
					<div class="card-content white-text">
						<h4 class="black-text text-darken-3" style="font-weight: 700">${e.nome}</h4>
						<h6 class="black-text">${e.descricao}</h6>
					</div>
					<%-- <div class="card-action">
          <a href="${pageContext.request.contextPath}/especialidades/read/${e.id}">Atualizar</a>
        </div> --%>
				</div>
			</div>
		</div>
	</c:forEach>

</body>
</html>
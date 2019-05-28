<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${evento.descricao}</title>
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
</head>

<style>
body {
	background-color: #F8F8F8;
}

.eventos {
	margin-left: 10%;
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

.gambiarra {
	width: 25% !important;
}
</style>
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
				<c:if test="${user.admin }">
					<li>
					<a href="${pageContext.request.contextPath }/especialidade"><i
							class="material-icons left">work</i>Especialidades</a></li>
				</c:if>
				<c:if test="${user!=null}">
					<li><a href="${pageContext.request.contextPath }/eventos/form"><i
							class="material-icons left">event</i>Cadastrar eventos</a></li>
					<!-- Dropdown Trigger -->
					<li><a class="dropdown-trigger" href="#!"
						data-target="dropdown1"><span
							class="i-circle md-login center white ">${fn:toUpperCase(fn:substring(user.email, -1, 1))}</span><i
							class="material-icons right">arrow_drop_down</i></a></li>
				</c:if>
				<c:if test="${user == null}">
					<li><a href="${pageContext.request.contextPath }/login/form"><i
							class="material-icons left">account_circle</i>Log in </a></li>
				</c:if>


			</ul>
		</div>
	</nav>
	
	<span style="color: #ea4343;">${message}</span>

	<!-- Formulario de Cadastro -->
	<div class="row container">
		<p>&nbsp;</p>
		<form action="${pageContext.request.contextPath}/eventos/candidatar"
		 method="post" class="col s12 white ">
			<fieldset class="formulario">
				<legend>
					<i class="material-icons prefix " style="font-size: 70px">event_available</i>
				</legend>
				<h3 class=" center">${evento.descricao}</h3>


				<!--Campo Data -->
				<div class="input-field col s12">
					<i class="material-icons prefix">calendar_today</i>
					<h6 style="margin-left: 40px; margin-top: 12px" class="light">
						<fmt:formatDate pattern="dd-MM-yyyy" value="${evento.data}" />
					</h6>
				</div>

				<!--Campo Local -->
				<div class="input-field col s12">
					<i class="material-icons prefix">location_on</i>
					<h6 style="margin-left: 40px; margin-top: 12px" class="light">${evento.local }</h6>
				</div>

				<!--Campo Owner-->
				<div class="input-field col s12">
					<i class="material-icons prefix">account_circle</i>
					<h6 style="margin-left: 40px; margin-top: 12px" class="light">${evento.owner.email }</h6>
				</div>
				<h3 class=" center blue-text">Vagas</h3>
				
				<!--Candidato ou visitante -->
				
				<c:if test="${evento.owner.email != user.email }">
					<div class="input-field col s12">
						<c:forEach var="vaga" items="${evento.vagas}">
							<div class="row">
								<div class="input-field col s3 ">
									<p>
										<label> 
										<input type="checkbox" class="vagas"
											name="vagas" value="${vaga.id }" class="filled-in" />
											<span>${vaga.especialidade.nome } (${vaga.qtd_vagas})</span>
										</label>
									</p>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:if>
				
				<!--Promotor -->
				
				<c:if test="${evento.owner.email == user.email }">
					<div class="input-field col s12">
						<c:forEach var="vaga" items="${evento.vagas}">
							<h6 style="font-weight:700" class="text-darken-3">
							${vaga.especialidade.nome } (${vaga.qtd_vagas})</h6>
								<c:if test="${fn:length(vaga.candidato_vaga) == 0}">
									<li class="grey-text ">Não tem candidatos</li>
								</c:if>
								<c:if test="${fn:length(vaga.candidato_vaga) > 0}">
									<c:forEach var="candidatovaga" items="${vaga.candidato_vaga}">
										<li class="black-text ">${candidatovaga.candidato.email}</li>
									</c:forEach>
								</c:if>
						</c:forEach>
					</div>
				</c:if>
					<!--Botões-->
					<div class="input-field col s4">
						<a href="${pageContext.request.contextPath }/eventos"
							class="btn red">cancelar</a>
						<c:if test="${evento.owner.email != user.email }">
							<input type="submit" value="candidatar-se" class="btn blue">
						</c:if>
						<c:if test="${evento.owner.email == user.email }">
							<input type="submit" disabled value="candidatar-se"
								class="btn blue">
						</c:if>
					</div>
			</fieldset>
		</form>
	</div>


	<script type="text/javascript">
		$(document).ready(function() {
			$('.modal').modal();
			$('.dropdown-trigger').dropdown();
		});
	</script>

</body>
</html>
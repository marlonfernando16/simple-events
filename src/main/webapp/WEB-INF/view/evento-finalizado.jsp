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

.modal {
	width: 25% !important;
	margin-top: 5%
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
					<li><a
						href="${pageContext.request.contextPath }/especialidades"><i
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
	<!-- open modal success -->
	<c:if test="${not empty message_success}">
		<script>
			//open modal sucess
			$(document).ready(function() {
				$('#modalsuccess').modal();
				$('#modalsuccess').modal('open');
			});
		</script>
	</c:if>
	<!-- open modal error -->
	<c:if test="${not empty message_error}">
		<script>
			$(document).ready(function() {
				$('#modalerror').modal();
				$('#modalerror').modal('open');
			});
		</script>
	</c:if>

	<!-- Formulario de Cadastro -->
	<div class="row container">
		<p>&nbsp;</p>
		<fieldset class="formulario">
			<legend>
				<i class="material-icons prefix red-text " style="font-size: 70px">event_available</i>
			</legend>

			<h3 class=" center">${evento.descricao}</h3>
			<h6 class="red-text center ">(inscrições encerradas)</h6>

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
	</div>
	</fieldset>
	</div>

	<!--Vagas -->
	<h3 class=" center green-text">Resultado</h3>
	<div class="container">
		<ul class="collapsible">
			<c:forEach var="vaga" items="${evento.vagas}">
				<li>
					<div class="collapsible-header" style="font-weight: 700">
						${vaga.especialidade.nome}</div>
					<div class="collapsible-body">
						<c:forEach var="candidatovaga" items="${vaga.candidato_vaga}">
							<c:if
								test="${not empty vaga.candidato_vaga && candidatovaga.state != 'NAO_AVALIADO'}">
								<span>
									<table>
										<thead>
											<th>candidato</th>
											<th>situação</th>
											<th>nota desempenho</th>
											<c:if test="${evento.owner.id == user.id }">
												<th>avaliar desempenho</th>
											</c:if>
										</thead>

										<tbody>
											<tr>
												<td>${candidatovaga.candidato.email}</td>
												<c:if test="${candidatovaga.state == 'APROVADO' }">
													<td class="green-text">DEFERIDO</td>
												</c:if>
												<c:if test="${candidatovaga.state == 'NAO_APROVADO' }">
													<td class="red-text">INDEFERIDO</td>
												</c:if>

												<td>${candidatovaga.nota_desempenho}</td>
												<c:if test="${evento.owner.id == user.id }">
													<td><a href="#"> <i
															class="material-icons prefix blue-text">note_add</i>
													</a></td>
												</c:if>
											</tr>
										</tbody>
									</table>
								</span>
							</c:if>
							<c:if
								test="${empty vaga.candidato_vaga || candidatovaga.state == 'NAO_AVALIADO'}">
								<p class="red-text">não teve inscritos para esta vaga</p>
							</c:if>
						</c:forEach>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>

	<!-- Modal sucess -->
	<div class="modal" id="modalsuccess">
		<div class="modal-header green">
			<div class="center white-text">
				<i class="material-icons prefix center "
					style="font-size: 50px; margin-bottom: 10px; margin-top: 10px; margin-left: 3px">
					check_circle</i>
				<h5 style="margin-top: 12px; margin-left: 5px"></h5>
			</div>
		</div>
		<div class="modal-content">
			<div class="center green-text">${message_success}</div>
			<div class="modal-footer center">
				<a class="btn green modal-close modal-action">Ok</a>
			</div>
		</div>
	</div>


	<!-- Modal error -->
	<div class="modal" id="modalerror">
		<div class="modal-header red">
			<div class="center white-text">
				<i class="material-icons prefix center "
					style="font-size: 50px; margin-bottom: 10px; margin-top: 10px; margin-left: 3px">
					error_outline</i>
				<h5 style="margin-top: 12px; margin-left: 5px"></h5>
			</div>
		</div>
		<div class="modal-content">
			<div class="center red-text">${message_error}</div>
			<div class="modal-footer center">
				<a class="btn red modal-close modal-action">Close</a>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		$('.modal').modal();
		$('.dropdown-trigger').dropdown();

		document.addEventListener('DOMContentLoaded', function() {
			var elems = document.querySelectorAll('.collapsible');
			var instances = M.Collapsible.init(elems);
		});
	</script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Novo Evento</title>
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
				<li><a href="${pageContext.request.contextPath }/eventos/form"><i
						class="material-icons left">event</i>Cadastrar eventos</a></li>
				<!-- Dropdown Trigger -->
				<li><a class="dropdown-trigger" href="#!"
					data-target="dropdown1"><span
						class="i-circle md-login center white ">${fn:toUpperCase(fn:substring(user.email, -1, 1))}</span><i
						class="material-icons right">arrow_drop_down</i></a></li>
			</ul>
		</div>
	</nav>

	<span style="color: #08c117;">${message_success}</span>
	<span style="color: #e82727;">${message_error}</span>

	<!-- Formulario de Cadastro -->
	<div class="row container">
		<p>&nbsp;</p>
		<fieldset class="formulario">
			<form:form
				action="${pageContext.request.contextPath }/eventos/update/${evento.id}"
				method="post" class="col s12 white " id="form2"
				modelAttribute="evento">
				<legend>
					<i class="material-icons prefix " style="font-size: 70px">event_available</i>
				</legend>
				<h5 class="light center">Atualizar Evento</h5>

				<!--Campo Descricao -->
				<div class="input-field col s12">
					<i class="material-icons prefix">event_note</i>
					<form:input path="descricao" />
					<form:errors path="descricao" />
					<form:errors path="descricao">
						<c:if test="${not empty evento}">
					${evento.descricao}
				</c:if>
					</form:errors>
					<label for="descricao">Descricao</label>
				</div>

				<!--Campo Data -->
				<div class="input-field col s12">
					<i class="material-icons prefix">calendar_today</i>
					<form:input path="data" type="date" />
					<form:errors path="data" />
					<form:errors path="data">
						<c:if test="${not empty evento}">
					${evento.data}
				</c:if>
					</form:errors>
					<label for="data">Data</label>
				</div>

				<!--Campo Local -->
				<div class="input-field col s12">
					<i class="material-icons prefix">location_on</i>
					<form:input path="local" />
					<form:errors path="local" />
					<form:errors path="local">
						<c:if test="${not empty evento}">
					${evento.local}
				</c:if>
					</form:errors>
					<label for="local">Local</label>
				</div>
				<div class="center">
					<input type="submit" value="Atualizar" class="btn blue">
				</div>
			</form:form>
			<form:form
				action="${pageContext.request.contextPath }/eventos/finalizar/${evento.id}"
				method="post" class="col s12 white " id="form">
				<!-- Título Vagas -->
				<div class="row" style="margin-left: 43%;">
					<div class="col s2">
						<h5 class="light">Vagas</h5>
					</div>

					<div class="col s2">
						<a href="#" onclick="openModal()" class="modal-trigger"> <i
							class="material-icons prefix green-text"
							style="margin-top: 18px;">add</i>
						</a>
					</div>
				</div>
				<!--Promotor -->
				<div class="container">
					<table class="striped" style="margin-top: 30px; border: 1px solid;">
						<thead>
							<th>vaga</th>
							<th>quantidade</th>
							<th></th>
							<th></th>
						</thead>
						<tbody>
							<c:forEach var="vaga" items="${evento.vagas}">
								<tr>
									<td style="font-weight: 700">${vaga.especialidade.nome}</td>
									<td>${vaga.qtd_vagas}</td>
									<td><a href="#"
										onclick="openModalUpdate('${vaga.especialidade.nome}',${vaga.id},${vaga.qtd_vagas })"
										class="modal-trigger"> <i
											class="material-icons prefix blue-text">edit</i>
									</a></td>
									<td><a
										href="${pageContext.request.contextPath}/vagas/delete/${vaga.id }">
											<i class="material-icons prefix red-text">delete_forever</i>
									</a></td>
								</tr>
								<c:forEach var="candidatovaga" items="${vaga.candidato_vaga}">
									<tr>
										<td>${candidatovaga.candidato.email}</td>
										<td><label> <input name="${candidatovaga.id}"
												type="radio" class="defere" value="DEFERIDO" /> <span>Deferido</span>
										</label></td>
										<td><label> <input name="${candidatovaga.id}"
												type="radio" class="defere" value="NAO_DEFERIDO" /> <span>Indeferido</span>

										</label></td>
										<td></td>
									</tr>
									<input type="hidden" name="deferimentos_vagas"
										value="${candidatovaga.id}">
								</c:forEach>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<%--
					<div class="input-field col s12">
						<c:forEach var="vaga" items="${evento.vagas}">
							<h6 style="font-weight: 700" class="text-darken-3">
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
				--%>

				<!--Botões-->
				<div class="input-field col s12">
					<input type="submit" value="Finalizar" class="btn blue"> <a
						href="${pageContext.request.contextPath }/eventos" class="btn red">cancelar</a>
				</div>
			</form:form>
		</fieldset>
	</div>


	<!-- Modal Add Vaga -->
	<div class="modal" id="modal"
		style="width: 30%; height: 50%; margin-top: 5%;">
		<div class="modal-header blue">
			<div class="classemuda"
				style="color: white; display: flex; flex-direction: row;">
				<i class="material-icons prefix "
					style="font-size: 30px; margin-bottom: 10px; margin-top: 10px; margin-left: 3px">
					add_circle_outline</i>
				<h5 style="margin-top: 12px; margin-left: 5px">Adicionar Vaga</h5>
			</div>
		</div>
		<div class="modal-content">
			<form action="${pageContext.request.contextPath}/vagas/add"
				method="post" class="col s12">
				<div class="row">
					<c:if test="${fn:length(especialidades)>0}">
						<!-- Campo especialidade -->
						<div class="input-field col s5 m6">
							<select name="especialidade">
								<c:forEach var="especialidade" items="${especialidades}">
									<option value="${especialidade.id}">${especialidade.nome }</option>
								</c:forEach>

							</select> <label>Vaga</label>
						</div>

						<!-- Id do evento -->
						<input type="hidden" name="idevento" value="${evento.id }">

						<!--Campo Quantidade -->
						<div class="input-field  col s2 m2">
							<input type="number" name="quantidadevagas" class="validate"
								id="title" value="1" min="1" required> <label
								for="theme">Quantidade</label>
						</div>

					</c:if>
					<c:if test="${fn:length(especialidades) == 0}">
						<p class="center red-text">Não tem vagas para cadastrar</p>
					</c:if>
				</div>
		</div>
		<div class="modal-footer" style="margin-top: 15%;">
			<c:if test="${fn:length(especialidades) > 0}">
				<button type="submit" id="saveData" class="btn blue">Add</button>
			</c:if>
			<a class="btn red modal-close modal-action">Cancel</a>
		</div>
		</form>
	</div>

	<!-- Modal update vaga -->
	<div class="modal" id="modalupdate" style="width: 30%; margin-top: 5%;">
		<div class="modal-header blue">
			<div class="classemuda"
				style="color: white; display: flex; flex-direction: row;">
				<i class="material-icons prefix "
					style="font-size: 30px; margin-bottom: 10px; margin-top: 10px; margin-left: 3px">
					update</i>
				<h5 style="margin-top: 12px; margin-left: 5px">Atualizar Vaga</h5>
			</div>
		</div>
		<div class="modal-content">
			<form action="${pageContext.request.contextPath}/vagas/update"
				method="post" class="col s12">
				<div class="row">
					<!--Nome da vaga -->
					<div class="col s5 ">
						<h6 class="descricaovaga"
							style="font-weight: 700; margin-top: 15px;">nome da vaga :</h6>
					</div>

					<!-- Id vaga -->
					<input type="hidden" name="idvaga" class="inputIdVaga" value="">

					<!--Campo Quantidade -->
					<div class=" col s2">
						<input type="number" name="quantidadevaga" class="quantidadevaga"
							id="title" value="1" min="1" required>
					</div>
				</div>
		</div>
		<div class="modal-footer">
			<button type="submit" id="saveData" class="btn blue">Atualizar</button>
			<a class="btn red modal-close modal-action">Cancel</a>
		</div>
		</form>
	</div>

	<script type="text/javascript">
		$('#modal').modal();
		function openModal() {
			$('#modal').modal('open');
		}
		$('#modalupdate').modal();
		function openModalUpdate(nomevaga, idvaga, qtdvagas) {
			$('#modalupdate').modal('open');
			let inputQtdVagas = document.querySelector('.quantidadevaga');
			let descricaoVaga = document.querySelector('.descricaovaga');
			let inputIdVaga = document.querySelector('.inputIdVaga');
			descricaoVaga.innerHTML = nomevaga;
			inputQtdVagas.value = qtdvagas;
			inputIdVaga.value = idvaga;
		}
		$(document).ready(function() {
			$('select').formSelect();
		});
		
		
		
		//gerar os inputs para cada candidato para resgatar no controller
	    $("#form").submit( function(eventObj) {	
	    	$('.defere:checked').each(function() {
			     //deferimentos_values.push($(this).val());
	    		$('<input />').attr('type', 'hidden')
	            .attr('name', "deferejs")
	            .attr('value', $(this).val())
	            .appendTo('#form');
			});	  	
	    	
	    	
			
	    
	    });
	</script>

</body>
</html>
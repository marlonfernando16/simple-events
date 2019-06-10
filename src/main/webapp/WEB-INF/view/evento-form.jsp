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
				<c:if test="${user!=null}">
					<li><a href="${pageContext.request.contextPath }/eventos/form"><i
							class="material-icons left">event</i>Cadastrar eventos</a></li>
					<!-- Dropdown Trigger -->
					<li><a class="dropdown-trigger" href="#!"
						data-target="dropdown1"><span
							class="i-circle md-login center white ">${fn:toUpperCase(fn:substring(user.email, -1, 1))}</span><i
							class="material-icons right">arrow_drop_down</i></a></li>
				</c:if>
			</ul>
		</div>
	</nav>

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
		<form:form action="${pageContext.request.contextPath }/eventos"
			method="post" class="col s12 white " modelAttribute="evento">
			<fieldset class="formulario">
				<legend>
					<i class="material-icons prefix " style="font-size: 70px">event_available</i>
				</legend>
				<h5 class="light center">Cadastro de Eventos</h5>

				<!--Campo Descricao -->
				<div class="input-field col s12">
					<i class="material-icons prefix">event_note</i>
					<form:input autofocus="autofocus" type="text" required="required" path="descricao" />
					<form:errors class="center red-text" path="descricao" />
					<label for="descricao">Descricao</label>
				</div>

				<!--Campo Data -->
				<div class="input-field col s12">
					<i class="material-icons prefix">calendar_today</i>
					<form:input path="data" required="required" type="date" />
					<form:errors class="center red-text" path="data" />
					<label for="data">Data</label>
				</div>

				<!--Campo Local -->
				<div class="input-field col s12">
					<i class="material-icons prefix">location_on</i>
					<form:input path="local" type="text" required="required" />
					<form:errors class="center red-text" path="local" />
					<label for="local">Local</label>
				</div>

				<!--Vagas -->
				<div class="input-field col s12">
					<c:forEach var="esp" items="${especialidades }">
						<div class="row">
							<div class="input-field col s2 	">
								<p>
									<label> <input type="checkbox" class="especialidades"
										name="especialidades" value="${esp.id}" class="filled-in" />
										<span>${esp.nome }</span>
									</label>
								</p>
							</div>
							<div class="input-field col s1">
								<input name="quantidadevagas" class="quantidadevagas" disabled
									type="number" value="1" /> <label for="especialidade">${vaga.especialidade.nome}</label>
							</div>
						</div>
					</c:forEach>
				</div>
				
				<div class="hide" id="msg_erro_vagas">
					<span class="red-text">Escolha pelo menos uma vaga!</span>
				</div>

				<!--Botões-->
				<div class="input-field col s4">
					<a href="${pageContext.request.contextPath }/eventos"
						class="btn red">cancelar</a> <input type="submit"
						value="cadastrar" class="btn blue" id="botaoCadastrar">
				</div>
			</fieldset>
		</form:form>
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
	</script>
	
	<script type="text/javascript">
		$(document).ready(function () {
    		$('#botaoCadastrar').click(function() {
      			checked = $("input[type=checkbox]:checked").length;
		      if(!checked) {
		      	$('#msg_erro_vagas').removeClass('hide');
      		  }
    		});
		});
	</script>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$(document).ready(function(){
		    $('select').formSelect();
		  });
		});
	//ativando o imput de quantidade de vagas quando o checkbox for marcado
	
	let especialidades = document.querySelectorAll('.especialidades');
	let quantidadevagas = document.querySelectorAll('.quantidadevagas');
	especialidades.forEach((value, indice)=> {
			value.addEventListener('change',function(){
				if(this.checked) {
					console.log(indice)
			        quantidadevagas[indice].disabled = false
			    }else {
			    	quantidadevagas[indice].disabled = true
			    }
			})
	})
	</script>

</body>
</html>
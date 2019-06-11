<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listagem de Eventos</title>
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

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
	
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
</head>

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

.modal {
	width: 25% !important;
	margin-top: 5%
}

@import url("https://fonts.googleapis.com/css?family=Pacifico");

.card-info, .card__meta a {
	color: #3299BB !important;
	display: inline-flex;
	vertical-align: middle;
	line-height: 2;
	font-size: 1rem;
}

.card__meta i.small {
	font-size: 1.5rem;
}

.card .card-content .card-title {
	line-height: 34px;
}

.card__date {
	background: #08c;
	position: absolute;
	top: 6px;
	right: 6px;
	width: 40px;
	height: 40px;
	border-radius: 50%;
	color: #fff;
	text-align: center;
	line-height: 13px;
	font-weight: bold;
	padding-top: 7px;
}

.card__date__day {
	display: block;
	font-size: 14px;
}

.card__date__month {
	display: block;
	font-size: 10px;
	text-transform: uppercase;
}

.card .card-content .card-title {
	color: #0D8080 !important;
	line-height: 18px;
}

.card .card-title {
	font-size: 18px;
	font-weight: 400;
}

.card .card-content p {
	color: #1E1E1E;
	font-size: 13px;
}

.collapsible {
	background: #fff;
}

.card-row {
	margin-bottom: 4px;
	margin-top: 4px;
}

.card-action i {
	vertical-align: text-bottom;
	font-size: 21px;
}

.collapsible-header {
	background-color: #3299BB;
	color: #fff;
}

.score {
  display: block;
  position: relative;
  overflow: hidden;
}

.score-wrap {
  display: inline-block;
  position: relative;
  height: 19px;
}

.score .stars-active {
  color: #EEBD01;
  position: relative;
  z-index: 10;
  display: inline-block;
  overflow: hidden;
  white-space: nowrap;
}

.score .stars-inactive {
  color: grey;
  position: absolute;
  top: 0;
  left: 0;
  -webkit-text-stroke: initial;
  /* overflow: hidden; */
}

.pesquisa {
  background-color: #3299BB;
  color: #fff;
  box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.07);
  border-radius: 5px;
}

</style>
<body>
	<!--menu-->
	<!-- Dropdown Structure -->
	<ul id="dropdown1" class="dropdown-content">
		<li><a href="${pageContext.request.contextPath }/usuario/read/${user.id}">Atualizar</a></li>
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
							class="i-circle md-login center white ">${fn:toUpperCase(fn:substring(user.nome, -1, 1))}</span><i
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

	<c:if test="${empty eventos}">
		<p>Ainda não há eventos cadastrados.</p>
	</c:if>



	<%-- <nav class="search-title-filter">
		<div class="nav-wrapper">
			<div class="input-field col s1">
				<input id="search" type="search" name="search">
				<label for="search" class=""><i class="material-icons">search</i></label>
				<i class="material-icons">close</i>
			</div>
		</div>
	</nav> --%>
	
	<div class="row" style="margin-top:20px;">
      <div class="row col s3 pesquisa">
        <div class="input-field">
          <i class="material-icons prefix">search</i>
          <input id="search" type="search" name="search">
        </div>
      </div>
  </div>



	<div id="card-container" class="row">
		<c:forEach var="evento" items="${eventos}">
			<div class="col s12 m6 l4 evento">
				<!-- Card 1 -->
				<div class="card">
					<div class="card-content white-text">
						<div class="card__date" style="padding-top:12px;">
							<%-- <span class="card__date__day">${evento.data.day}</span> --%> <span
								style="font-size:13px;" class="card__date__month">${evento.data.month}</span>
						</div>
						<div class="card__meta">
							<a href="https://www.google.com.br/maps/place/${evento.local}"
								target="_blank"><i class="small material-icons">room</i>${evento.local}</a>
							<%-- <time>17th March</time> --%>
						</div>
						<span class="card-title grey-text text-darken-4 descricao">${evento.descricao}</span>
						<span class="card-title grey-text text-darken-4">
							<span class="score">
							    <div class="score-wrap">
							        <span class="stars-active" style="width:${evento.getMediaAvaliacao()*100 / 5}%">
							            <i class="fa fa-star" aria-hidden="true"></i>
							            <i class="fa fa-star" aria-hidden="true"></i>
							            <i class="fa fa-star" aria-hidden="true"></i>
							            <i class="fa fa-star" aria-hidden="true"></i>
							            <i class="fa fa-star" aria-hidden="true"></i>
							        </span>
									<span class="stars-inactive">
							            <i class="fa fa-star-o" aria-hidden="true"></i>
							            <i class="fa fa-star-o" aria-hidden="true"></i>
							            <i class="fa fa-star-o" aria-hidden="true"></i>
							            <i class="fa fa-star-o" aria-hidden="true"></i>
							            <i class="fa fa-star-o" aria-hidden="true"></i>
									</span>
								</div>
							</span>
							<span class="grey-text" style="margin-left:105px; margin-top:-27px; float:left; font-size:15px;">
								${ evento.getMediaAvaliacao()} 
								(${fn:length(evento.avaliacao_eventos)} avaliações)
							</span>
						</span>
						
						<span class="card-subtitle grey-text text-darken-2">Criado por:
							${evento.owner.email} - </span>
						<span class="card-subtitle text-darken-2 finalizado">${evento.finalizado}</span>
						<span class="text-darken-2 card-info"><i
							class="small material-icons">label</i> <c:forEach var="vaga"
								items="${evento.vagas}">
							${vaga.especialidade.nome},&nbsp;
						</c:forEach> </span>
					</div>
					<div class="card-action" style="display: flex; justify-content: space-between;">
						<a href="${pageContext.request.contextPath}/eventos/${evento.id}"><i
							class="material-icons">&nbsp;language</i>VISITAR EVENTO</a>
						<c:if test="${evento.owner.id == user.id}">
						<a class="red-text" href="${pageContext.request.contextPath}/eventos/delete/${evento.id}"><i
							class="material-icons delete">delete</i>APAGAR</a>
						</c:if>
					</div>
				</div>
				<!-- End of card -->
			</div>
		</c:forEach>
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
		//dropdown
		//$('.modal').modal();
		$('.dropdown-trigger').dropdown();
		//modal sucess
		$('#modalsuccess').modal();
		function openModalSuccess() {
			$('#modalsuccess').modal('open');
		}
		//modal error
		$('#modalerror').modal();
		function openModalError() {
			$('#modalerror').modal('open');
		}
	</script>

	<script type="text/javascript">
	$(document).ready(function(){
		let meses = document.querySelectorAll('.card__date__month')
		meses.forEach(elemento => {
			let array_meses = [
				'JAN', 'FEV', 'MAR', 'ABR', 'MAI', 'JUN',
				'JUL', 'AGO', 'SET', 'OUT', 'NOV', 'DEZ'
			]
			elemento.textContent = array_meses[elemento.textContent]
		});
		
		let estados = document.querySelectorAll('.finalizado')
		estados.forEach(elemento => {
			if (elemento.textContent == 'true') {
				elemento.textContent = "Inscrições encerradas."
				elemento.classList.add("red-text")
			} else {
				elemento.textContent = "Inscrições abertas."
				elemento.classList.add("green-text")
			}
		});
		
		$('#search').keyup(
		function searchEvento(){
  			let pesquisa = this.value.toLowerCase()
  			let nome
  			$('.evento').each(function(index, evento) {
    			nome = $(evento).find('.descricao').text().toLowerCase()
    			if(nome.indexOf(pesquisa) == -1)
    				evento.style.display = 'none'
    			else
    				evento.style.display = 'block'
  			})
		})
	});
	</script>

</body>
</html>
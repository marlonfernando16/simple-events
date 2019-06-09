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

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
</head>

<style>

rating
{
  align-items: center;
  background-color: #fdfdfd;
  display: flex;
  flex-flow: column nowrap;
  height: 100vh;
  justify-content: center;
  width: 100%;
}
p {
  color: #333;
  font-family: 'segoe ui', sans-serif;
  font-size: 24pt;
  transition: transform .5s ease;
}
p:hover {
  transform: scale(1.5, 1.5);
}
.rating {
  display: block;
  direction: rtl;
  unicode-bidi: bidi-override;
  text-align: center;
}
.rating .star {
  display: none;
}
.rating label {
  color: lightgray;
  display: inline-block;
  font-size: 22pt;
  margin: 0 -2px;
  transition: transform .15s ease;
}
.rating label:hover {
  transform: scale(1.35, 1.35);
}
.rating label:hover,
.rating label:hover ~ label {
  color: orange;
}
.rating .star:checked ~ label {
  color: orange;
}

ratingde
{
  align-items: center;
  background-color: #fdfdfd;
  display: flex;
  flex-flow: column nowrap;
  height: 100vh;
  justify-content: center;
  width: 100%;
}
p {
  color: #333;
  font-family: 'segoe ui', sans-serif;
  font-size: 24pt;
  transition: transform .5s ease;
}
p:hover {
  transform: scale(1.5, 1.5);
}
.ratingde {
  display: block;
  direction: rtl;
  unicode-bidi: bidi-override;
  text-align: center;
}
.ratingde .starde {
  display: none;
}
.ratingde label {
  color: lightgray;
  display: inline-block;
  font-size: 22pt;
  margin: 0 -2px;
  transition: transform .15s ease;
}
.ratingde label:hover {
  transform: scale(1.35, 1.35);
}
.ratingde label:hover,
.ratingde label:hover ~ label {
  color: orange;
}
.ratingde .starde:checked ~ label {
  color: orange;
}

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

			<h3 class="">${evento.descricao}</h3>
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
						<div class="grey-text" style="margin-left:90px; margin-top:-28px;">
							${ evento.getMediaAvaliacao()} 
							(${fn:length(evento.avaliacao_eventos)} avaliações)
						</div>
						
			
			<c:forEach var="avaliacao_evento" items="${evento.avaliacao_eventos }">
				<!-- se o usuario ja voltou -->
				<c:if test="${avaliacao_evento.participante.id == user.id }">
					 <c:forEach var = "i" begin = "1" end = "5">
							<c:if test="${i <= avaliacao_evento.nota_avaliacao_evento  }">
								<i class="fa fa-star orange-text" aria-hidden="true"></i>
							</c:if>
							<c:if test="${i > avaliacao_evento.nota_avaliacao_evento  }">
								<i class="fa fa-star grey-text" aria-hidden="true"></i>
							</c:if>
					</c:forEach>
					<span class="grey-text">  sua avaliação </span>
				</c:if>
			</c:forEach>
			
			<!-- mostrar o avaliar evento apenas se o usuario tiver sido deferido em uma das vagas-->
			<c:forEach var="vaga" items="${evento.vagas }">
				<c:forEach var="candidato_vaga" items="${vaga.candidato_vaga }">
					<c:if test="${user.id == candidato_vaga.candidato.id && candidato_vaga.state == 'APROVADO'}">
						<div class="row">
							<a href="#" onclick="openModalAvaliarEvento()" id="idModalAvaliarEvento" class="modal-trigger green-text center ">Avaliar evento</a>
						</div>
						<div class="divider"></div>
						<c:forEach var="avaliacao_evento" items="${evento.avaliacao_eventos }">
							<c:if test="${avaliacao_evento.participante.id == user.id }">
								<!-- desabilita a opcao de avaliar caso o usuario ja tenha avaliado -->
								<script>
									$(document).ready(function() {
										desabilitaAvaliarEvento()
									});
								</script>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
			</c:forEach>
			
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
					<div class="collapsible-header" style="font-weight:700">
						${vaga.especialidade.nome}
					</div>
					<div class="collapsible-body">
						<c:if test="${not empty vaga.candidato_vaga}">
							<span> 
								<table>
									<thead>
										<th>candidato</th>
										<th>situação</th>
										<th>nota desempenho</th>
									</thead>
										<c:forEach var="candidatovaga" items="${vaga.candidato_vaga}">
												<tbody>
												<tr>	
													<td>${candidatovaga.candidato.email}</td>
													<c:if test="${candidatovaga.state == 'APROVADO' }">
														<td class="green-text">DEFERIDO</td>
													</c:if>
													<c:if test="${candidatovaga.state == 'NAO_APROVADO' }">
														<td class="red-text">INDEFERIDO</td>
													</c:if>
													<c:if test="${candidatovaga.state == 'APROVADO' && candidatovaga.nota_desempenho > 0 }">
														<td>
															<div >
															    <c:forEach var = "i" begin = "1" end = "5">
															    	<c:if test="${i <= candidatovaga.nota_desempenho }">
																   		<i class="fa fa-star orange-text" aria-hidden="true"></i>
																   	</c:if>
																   	<c:if test="${i > candidatovaga.nota_desempenho }">
																   		<i class="fa fa-star grey-text" aria-hidden="true"></i>
																   	</c:if>
																</c:forEach>
															 </div>
														</td>
													</c:if>
													<c:if test="${evento.owner.id == user.id && candidatovaga.state == 'APROVADO' && candidatovaga.nota_desempenho == 0 }">
														<td>
															<a style="margin-left:40px;" href="#" onclick="openModalDesempenho('${candidatovaga.candidato.nome}', ${candidatovaga.id })" class="modal-trigger">
															 	<i class="material-icons prefix blue-text">note_add</i>
															</a>
														</td>
													</c:if>
													<c:if test="${evento.owner.id != user.id && candidatovaga.state == 'APROVADO' && candidatovaga.nota_desempenho == 0 }">
														<td>
															<div style="fonte-weight:700; margin-left:50px;">-</div>
														</td>
													</c:if>
													<c:if test="${candidatovaga.state == 'NAO_APROVADO' }">
														<td>
															<div style="fonte-weight:700; margin-left:50px;">-</div>
														</td>
													</c:if>	
												</tr>
											</c:forEach>
										</tbody>
									</table>
							</span>
						</c:if>
						<c:if test="${empty vaga.candidato_vaga}">
							<div class="red-text">não teve inscritos para esta vaga</div>
						</c:if>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	
	<!-- Modal avaliar evento -->
	<div class="modal" id="modalavaliarevento">
		<div class="modal-header blue">
			<div class="classemuda"
				style="color: white; display: flex; flex-direction: row;">
				<i class="material-icons prefix "
					style="font-size: 30px; margin-bottom: 10px; margin-top: 10px; margin-left: 3px">
					event_note</i>
				<h5 style="margin-top: 12px; margin-left: 5px">Avalie o evento</h5>
			</div>
		</div>
		<div class="modal-content">
			<h6 style="font-weight: 700; margin-left: 15px;">Como foi a sua experiência ? </h6> 
			<div class="grey-text">Escolha de 1 a 5 estrelas para classificar.</div>
				<form action="${pageContext.request.contextPath }/eventos/avaliar/evento" method="post">
					<div class="rating">
					  <!-- Id do evento -->
					  <input type="hidden" name="idevento" class="idEvento" value="${evento.id }">
					  <input id="radio1" type="radio" name="star" value="5" class="star" />
					  <label for="radio1">&#9733;</label>
					  <input id="radio2" type="radio" name="star" value="4" class="star" />
					  <label for="radio2">&#9733;</label>
					  <input id="radio3" type="radio" name="star" value="3" class="star" />
					  <label for="radio3">&#9733;</label>
					  <input id="radio4" type="radio" name="star" value="2" class="star" />
					  <label for="radio4">&#9733;</label>
					  <input id="radio5" type="radio" name="star" checked value="1" class="star" />
					  <label for="radio5">&#9733;</label>
					</div>
					
					<div class="modal-footer ">
						<button type="submit" id="saveData" class="btn blue">Avaliar</button>
						<a class="btn red modal-close modal-action">Cancelar</a>
					</div>
				</form>
			</div>
		</div>
		
	<!-- Modal desempenho -->
	<div class="modal" id="modaldesempenho">
		<div class="modal-header blue">
			<div class="classemuda"
				style="color: white; display: flex; flex-direction: row;">
				<i class="material-icons prefix "
					style="font-size: 30px; margin-bottom: 10px; margin-top: 10px; margin-left: 3px">
					event_note</i>
				<h5 style="margin-top: 12px; margin-left: 5px">Avalie o desempenho</h5>
			</div>
		</div>
		<div class="modal-content">
			<h7 style="font-weight: 700; margin-left: 10px;">Como foi o desempenho de </h7> 
			<h7 style="font-weight: 700;" class="candidatonome blue-text">nome</h7>
			
			<!-- Id do evento -->
			
			
			<span class="grey-text">Escolha de 1 a 5 estrelas para classificar</span>
				<form action="${pageContext.request.contextPath }/eventos/avaliar/desempenho" method="post">
					<div class="ratingde">
					  <input type="hidden" name="idcandidatovaga" class="idCandidatoVaga" value="">
					  <input id="radio11" type="radio" name="starde" value="5" class="starde" />
					  <label for="radio11">&#9733;</label>
					  <input id="radio22" type="radio" name="starde" value="4" class="starde" />
					  <label for="radio22">&#9733;</label>
					  <input id="radio33" type="radio" name="starde" value="3" class="starde" />
					  <label for="radio33">&#9733;</label>
					  <input id="radio44" type="radio" name="starde" value="2" class="starde" />
					  <label for="radio44">&#9733;</label>
					  <input id="radio55" type="radio" name="starde" checked value="1" class="starde" />
					  <label for="radio55">&#9733;</label>
					</div>
					
					<div class="modal-footer ">
						<button type="submit" id="saveData" class="btn blue">Avaliar</button>
						<a class="btn red modal-close modal-action">Cancelar</a>
					</div>
				</form>
			</div>
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
		//modal avaliar desempenho
		$('#modaldesempenho').modal();
		function openModalDesempenho(candidato, candidatovagaid) {
			$('#modaldesempenho').modal('open');
			let candidatonome = document.querySelector('.candidatonome');
			let idCandidatoVaga = document.querySelector('.idCandidatoVaga');
			candidatonome.innerHTML = candidato+" ?";
			idCandidatoVaga.value = candidatovagaid;
			console.log(candidato);
			console.log(idCandidatoVaga)
		}
		//modal avaliar evento
		$('#modalavaliarevento').modal();
		function openModalAvaliarEvento() {
			$('#modalavaliarevento').modal('open');
			console.log("oi");
		}
		$('.star').on('change', function() {
			  let stars = $(this).val();
			  /* Make an AJAX call to register the rating */
			  console.log(stars);
			  console.log()
			});
		$('.starde').on('change', function() {
			  let stars = $(this).val();
			  /* Make an AJAX call to register the rating */
			  //console.log(stars);
			  console.log("miz")
			});

		document.addEventListener('DOMContentLoaded', function() {
			var elems = document.querySelectorAll('.collapsible');
			var instances = M.Collapsible.init(elems);
		});
		
		function desabilitaAvaliarEvento(){
			let idModalAvaliarEvento = document.querySelector('#idModalAvaliarEvento');
			idModalAvaliarEvento.style.display= 'none'
		}

		
	</script>

</body>
</html>
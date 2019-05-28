<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
</head>

<style>
	body {
		background-color: #F8F8F8 ;
		}
	
	.eventos{
		margin-left: 10%;
	}
    .i-circle.md-login{
      margin-left: 50px;
      margin-top: 50%;
      font-weight:900;
      padding: 10px;
      padding-right: 15px;
      padding-left: 15px;
      border-radius: 50%;
      color:black;
    }
    .gambiarra {
    	width: 25% !important;
    }

</style>
<body>
	<!--menu-->
	<!-- Dropdown Structure -->
	<ul id="dropdown1" class="dropdown-content">
		<li> </li>
		<li><a href="${pageContext.request.contextPath }/login/logout">Sair</a></li>
	</ul>
	<nav class="teal lighten-2" >
		<div class="nav-wrapper">
			<a href="#!" class="brand-logo">Simple events</a>
			<ul class="right hide-on-med-and-down">
				<li><a href="${pageContext.request.contextPath }/eventos"><i class="material-icons left">home</i>Home</a></li>
				<c:if test="${user.admin }">
					<li><a href="${pageContext.request.contextPath }/especialidade"><i class="material-icons left">work</i>Especialidades</a></li>
				</c:if>
				<c:if test="${user!=null}">
					<li><a href="${pageContext.request.contextPath }/eventos/form"><i class="material-icons left">event</i>Cadastrar eventos</a></li>
				<!-- Dropdown Trigger -->
					<li><a class="dropdown-trigger" href="#!"
					data-target="dropdown1"><span class="i-circle md-login center white ">${fn:toUpperCase(fn:substring(user.email, -1, 1))}</span><i class="material-icons right">arrow_drop_down</i></a></li>
				</c:if>
				<c:if test="${user == null}">
						<li> <a href="${pageContext.request.contextPath }/login/form"><i class="material-icons left">account_circle</i>Log in </a></li>
				</c:if>
				
			
			</ul>
		</div>
	</nav>
	
	<span style="color: #08c117;">${message}</span>
	
	<c:if test="${empty eventos}">
		<p>Ainda não há eventos cadastrados.</p>
	</c:if>

	<ul class="eventos">
		<c:forEach var="e" items="${eventos}">

	<div class="col s12 ">
      <div class="card-panel white hoverable lighten-5 z-depth-1" style="width:90%;height:9em;padding-top:5px">
        <div class="row">
            <div class="col s12 m4 l3">
            <span class="black-text">
                <h4><a href="${pageContext.request.contextPath}/evento/${e.id}" class="black-text text-darken-3" style="font-weight:700">  ${e.descricao} </a></h4>
                <small class="grey-text">created by ${e.owner.email }</small>
              </span>
            </div>
            <div class="col s12 m4 l3">
              <h4 class="locais blue-text" >${e.local}</h4>
              <small class="grey-text center">will happen in <fmt:formatDate pattern="dd-MM-yyyy" value="${e.data}" /></small>
            </div>
            <div class="col s12 m4 l3">
            	<h4 class="locais blue-text" >Vagas</h4>
            	<small class="grey-text center">
            		<c:forEach var="vaga"  items="${e.vagas}">
            			${vaga.especialidade.nome } -
            		</c:forEach>
            	</small>
            </div>
            <c:if test="${usuario.email == e.owner.email}">
            <div class="col s12 m4 l1" style = "margin-top: 20px">
				<p><a href="${pageContext.request.contextPath}/eventos/delete/${e.id}"><i class="material-icons">delete_forever</i></a></p> 
            </div>
            <%-- <div class="col s12 m4 l1" style = "margin-top: 20px">
            	<p><a href="${pageContext.request.contextPath}/eventos/read/${e.id}"><i class="material-icons">create</i></a></p> 
            </div> --%>
            </c:if>
            
        </div>
      </div>
  </div>
		</c:forEach>
	</ul>
	
	<script type="text/javascript">
	$(document).ready(function(){
		   $('.modal').modal();
		   $('.dropdown-trigger').dropdown();
		});
	</script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Meu Spotify</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="recursos/css/estilo.css">

</head>
<body>

	<div class="container">

		<!-- INÍCIO DO MENU SUPERIOR -->
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#links-menu" style="background-color: #75BA2F;">
						<i class="material-icons">menu</i>
					</button>
				</div>


				<nav id="links-menu" class="collapse navbar-collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="playlists">Minhas Playlists</a></li>
						<li><a href="novaplaylist">Nova Playlist</a></li>
						<li><a href="novamusica">Upload de Música</a></li>
						<li><a href="sair">Sair</a></li>
					</ul>
				</nav>
			</div>
		</nav>
		<!-- FIM DO MENU SUPERIOR -->

		<div class="row">
			<div class="col-md-12" id="cabecalho">
				<div id="logo" class="text-center">
					<img src="recursos/images/logo.png">
					<p>Meu Spotify</p>
					<h1>Detalhes da Playlist</h1>
					<h5>
						Ouça suas músicas favoritas <strong>${Usuario.nome}</strong>
					</h5>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4 text-center">
				<c:set var = "msg" scope = "request" value = "${erroSTR}"/>
				<c:if test="${msg != null}">
					<h5 class="alert alert-danger" role="alert">
						${erroSTR}
					</h5>
				</c:if>
			</div>
			<div class="col-md-4"></div>
		</div>

		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<h4 class="tituloh4">
					${Playlist.titulo}
					<a href="player">
						<img id="imgPlay" alt="Tocar Playlist" Title="Tocar Playlist" src="recursos/images/play.png">
					</a>
				</h4>
				<h6><a href="recuperamusicas?idplaylist=${Playlist.id}">+ adicionar músicas</a></h6>
			</div>
			<div class="col-md-2"></div>
		</div>
		
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<c:set var = "m" scope = "session" value = "${Playlist.musicas}"/>
				<c:if test="${empty m}">
					<h5 class="alert alert-warning text-center" role="alert">
						Playlist vazia!
					</h5>
				</c:if>
				<c:forEach var="musica" items="${Playlist.musicas}">
					<div id="playlists">
						<span class="tituloMusica"><c:out value="${musica.titulo}" /></span>
						<span class="detalhesMusica">(Artista: <c:out value="${musica.artista}" /> - </span>
						<span class="detalhesMusica">Álbum: <c:out value="${musica.album}" />)</span>
					</div>
				</c:forEach>
			</div>
			<div class="col-md-2"></div>
		</div>

	</div>


	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
</body>
</html>
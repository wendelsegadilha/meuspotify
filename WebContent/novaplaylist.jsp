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
					<h1>Nova Playlist</h1>
					<h5>Crie sua playlist <strong>${Usuario.nome}</strong></h5>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4 text-center">
				<c:set var = "msgErro" scope = "request" value = "${erroSTR}"/>
				<c:set var = "msgOK" scope = "request" value = "${okSTR}"/>
				<c:if test="${msgErro != null}">
					<h5 class="alert alert-danger" role="alert">
						${erroSTR}
					</h5>
				</c:if>
				<c:if test="${msgOK != null}">
					<h5 class="alert alert-success" role="alert">
						${msgOK}
					</h5>
				</c:if>
			</div>
			<div class="col-md-4"></div>
		</div>

		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<div id="form-login">
					<form action="efetivaplaylist" method="post">
						<div class="form-group">
							<label for="txtNomePlaylist">Nome:</label> <input type="text"
								class="form-control" name="txtNomePlaylist" id="txtNomePlaylist" required="required">
						</div>
						<div class="form-group text-center">
							<button class="btn btn-success">Criar Playlist</button>
						</div>
					</form>
				</div>
			</div>
			<div class="col-md-4"></div>
		</div>


	</div>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
</body>
</html>
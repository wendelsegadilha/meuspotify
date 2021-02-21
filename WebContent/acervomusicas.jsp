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
					<h1>Acervo de Músicas</h1>
					<h5>
						Confira todo nosso acervo musical <strong>${Usuario.nome}</strong>
					</h5>
				</div>
			</div>
		</div>
		

		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<c:forEach var="musica" items="${ListaMusicas}">
					<div id="musicas">
						<button class="btn btn-success imgAddMusica" onclick="adicionar(${idPlaylist}, ${musica.id});">+</button>
						<span class="tituloMusica"><c:out value="${musica.titulo}" /></span>
						<span class="detalhesMusica">(<c:out value="${musica.artista}" />)</span>
						<div id="dadosMusica">
						<span class="detalhesMusica">( Álbum: <c:out value="${musica.album}" /> - </span>
						<span class="detalhesMusica">
							Estilo:
							<c:if test="${musica.estilo == 1}">Rock</c:if>
							<c:if test="${musica.estilo == 2}">Sertanejo</c:if>
							<c:if test="${musica.estilo == 3}">Samba/Pagode</c:if>
							<c:if test="${musica.estilo == 4}">Forró</c:if>
							<c:if test="${musica.estilo == 5}">Eletrônica</c:if>
							<c:if test="${musica.estilo == 6}">Reggae</c:if>
							<c:if test="${musica.estilo == 7}">Outros</c:if>
							)
						</span>
						</div>
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
		
<script type="text/javascript">
	function adicionar(idPlaylist, idMusica) {
		var xhttp = new XMLHttpRequest();
		  xhttp.open("GET","addmusicaplaylist?idplaylist="+idPlaylist+"&idmusica="+idMusica);
		  xhttp.onreadystatechange = function() {
		    if (this.readyState === 4 && this.status === 200) {
		     alert(this.responseText);
		    }
		  };
		  xhttp.send();
	}
</script>		
</body>
</html>
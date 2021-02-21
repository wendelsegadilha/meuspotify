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

<script type="text/javascript">
	var musicas = new Array(); //lista de musicas
	var repetir = false; //se a playlist vai ficar em modo repeat
	var musicaAtual = 0;
	var totalMusicas = 0;
	var player;
	
	function setupPlayer(){
		var divMusicas = document.getElementById("playerContent");
		var filhos = divMusicas.childNodes;
		for(i = 0; i < filhos.length; i++){
			if(filhos[i].nodeName === "DIV"){
				musicas.push(filhos[i].id);
				totalMusicas++;
			}
		}
		console.log(musicas);
		player = document.getElementById("musicplayer");
		player.src = musicas[0];
		document.getElementById("tocandoAgora").innerHTML = "Tocando Agora: " + document.getElementById(musicas[musicaAtual]).innerHTML;
		player.onended = function(){
			if(musicaAtual < musicas.length-1){
				musicaAtual = musicaAtual + 1;
				player.src = musicas[musicaAtual];
				player.play();
			}else{
				if(repetir){
					musicaAtual = 0;
					player.src = musicas[musicaAtual];
					player.play();
				}else{
					alert("Fim das músicas!");
				}
			}
			document.getElementById("tocandoAgora").innerHTML = "Tocando Agora: " + document.getElementById(musicas[musicaAtual]).innerHTML;
		}
		
	}
	
	function alterarRepeat() {
		repetir = !repetir;
		if(repetir){
			document.getElementById("imgRepeat").src="recursos/images/repeat_green.jpg";			
		}else{
			document.getElementById("imgRepeat").src="recursos/images/repeat_gray.jpg";
		}
	}
	
	function play(objetoMusica){
		console.log(objetoMusica.id);
		for(i = 0; i < musicas.length; i++){
			if(musicas[i] === objetoMusica.id){
				musicaAtual	= i;
				player.src = musicas[musicaAtual];
				player.play();
				document.getElementById("tocandoAgora").innerHTML = "Tocando Agora: " + document.getElementById(musicas[musicaAtual]).innerHTML;
			}
		}
	}
	
</script>

</head>
<body onload="setupPlayer()">

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
					<img id="imgRepeat" alt="Repetir" title="ativar repetir" src="recursos/images/repeat_gray.jpg" width="30" onclick="alterarRepeat()">
				</h4>
				<div id="tocandoAgora" class="text-center"></div>
				<div class="text-center">
					<audio id="musicplayer" controls controlsList="nodownload" src=""></audio>
				</div>
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
			</div>
			<div class="col-md-2"></div>
		</div>
		
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div id="playerContent">
					<c:forEach var="musica" items="${Playlist.musicas}">
						<div class="musicas-player" id="${musica.linkArquivo}" onclick="play(this)">
							<div id="playlists">
							<span class="tituloMusica"><c:out value="${musica.titulo}" /></span>
							<span class="detalhesMusica">(Artista: <c:out value="${musica.artista}" /> - </span>
							<span class="detalhesMusica">Álbum: <c:out value="${musica.album}" />)</span>
							</div>
						</div>
					</c:forEach>
				</div>
				
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
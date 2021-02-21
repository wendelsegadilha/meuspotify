<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Meu Spotify</title>
<link rel="stylesheet" href="recursos/css/bootstrap.min.css">
<link rel="stylesheet" href="recursos/css/estilo.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div id="logo" class="text-center">
					<img src="recursos/images/logo.png">
					<p>Meu Spotify</p>
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
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<div id="form-login">
					<form action="login" method="post">
						<div class="form-group">
							<label for="email">E-mail:</label>
							<input type="email" class="form-control" name="email" id="email" required="required">
						</div>
						<div class="form-group">
							<label for="senha">Senha:</label>
							<input type="password" class="form-control" name="senha" id="senha" required="required">
						</div>
						<div class="form-group">
							<button class="btn btn-success">Conectar</button> 
							<small>Não tem conta? clique <a href="novousuario">aqui</a></small>
						</div>
					</form>
				</div>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>


	<script src="recursos/js/jquery-3.3.1.slim.min.js"></script>
	<script src="recursos/js/popper.min.js"></script>
	<script src="recursos/js/bootstrap.min.js"></script>
</body>
</html>
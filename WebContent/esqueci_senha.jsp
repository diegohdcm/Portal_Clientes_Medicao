<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>

<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/bootstrap.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript"
	src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<link rel="stylesheet" href="css/style.css">
<link rel="shortcut icon" href="LogoOri.ico" >
<title>Esqueci Minha Senha</title>
</head>

<body>

	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<div align="center">
					<img src="img/logo-ntransp3.png" class="img-logo">
				</div>
				<h3 class="modal-title text-center" id="myModalLabel">Esqueci
					Minha Senha</h3>
			</div>

			<div class="modal-body">
				<form role="form" action="Controle?cmd=esquecisenha" method="post">

					<c:if test="${msg != null}">

						<div class="alert alert-danger alert-dismissable">

							<a class="close" data-dismiss="alert" aria-label="close">�</a> <strong>${msg}</strong>

							<a href="cadastro_cliente_email.jsp"
								style="text-align: left; margin-left: 10px;" title="Cadastra-se">Cadastra-se</a>

						</div>

					</c:if>


					<c:if test="${msg1 != null}">

						<div class="alert alert-success alert-dismissable">

							<a class="close" data-dismiss="alert" aria-label="close">�</a> <strong>${msg1}</strong>

						</div>

					</c:if>
					<div class="form-group">
						<div class="input-group">
							<input type="text" class="form-control" id="email" name="email"
								placeholder="Digite o seu email" title="Digite o seu emai">
							<span class="input-group-addon"><i class="fa fa-building"></i></span>
						</div>

					</div>

					<input value="Enviar" type="submit"
						class="form-control btn btn-danger" style="color: white">


					<br> <br>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
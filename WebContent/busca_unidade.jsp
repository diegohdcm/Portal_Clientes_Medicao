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
<title>Busca Clientes</title>
</head>

<body>
<!-- Modal Tempo expirado (Deslogar) -->
	<div id="modalSessaoExpirada" class="modal fade" role="dialog" draggable="false" data-backdrop="static">
		<div class="modal-dialog modal-sm">

			<!-- painel-->

			<div class="panel panel-info">

				<div class="panel panel-heading">
					<div class="panel-title text-center">Sess�o expirada!</div>
				</div>
				<div class="text-center">
					Tempo excedido de conec��o, favor realizar login novamente.<br />
				</div>
				

				<div class="modal-footer"
					style="margin-top: 0px; margin-bottom: 5px">
					
					<div class="text-center">
						
					</div>
					
					<a type="submit" class="btn btn-danger btn-sm"
				href="Controle?cmd=sair">OK</a>
				</div>
			</div>
		</div>
	</div>
	<!-- FIM MODAL -->
	
	
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<div align="center">
					<img src="img/logo-ntransp3.png" class="img-logo">
				</div>
				<h3 class="modal-title text-center" id="myModalLabel">Cadastro
					de Unidades</h3>
			</div>

			<div class="modal-body">
				<form role="form" action="Controle?cmd=buscar" method="post">

					<c:if test="${msg != null}">

						<div class="alert alert-danger alert-dismissable">

							<a class="close" data-dismiss="alert" aria-label="close">�</a> <strong>${msg}</strong>

						</div>

					</c:if>
					<div class="form-group">
						<div class="input-group">
							<input type="text" class="form-control" id="Login" name="codigo"
								placeholder="Digite o C�digo Hidroluz ou CNPJ do Condo"
								title="Digite o Cod. Hidroluz ou CNPJ do Condom�nio"> <span class="input-group-addon"><i
								class="fa fa-building"></i></span>
						</div>

					</div>

					<input value="Enviar" type="submit"
						class="form-control btn btn-danger" style="color: white">


					<br>
					<br>
				</form>
			</div>
		</div>
	</div>

</body>
<!-- Chama o modal Sess�oExpirada -->
<script type="text/javascript">
	$(window).on('load', function() {
		setTimeout(function() {
			$('#modalSessaoExpirada').modal('show');
		}, 10000);
	});
</script>

</html>


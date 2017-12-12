<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>

<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.0/jquery-ui.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/stylesistema.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.js"></script>
<script src="https://code.highcharts.com/highcharts.src.js"></script>
<script
	src='https://maps.googleapis.com/maps/api/js?key=AIzaSyBOGnYAV-oxy57rY3b2kggn5oHC-O64Jzg&callback=initMap'></script>

<link rel="shortcut icon" href="LogoOri.ico">
<title>Alteração Cadastral</title>
</head>

<body>
	<!-- BARRA DE NAVEGAÇÃO -->
	<nav class="navbar navbar-fixed-top navbar-custom" id="mainNav">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#barra-collapse">
					<span class="sr-only">Barra Navegação</span> Menu&nbsp; <i
						class="fa fa-bars"></i>
				</button>
				<a href="#" class="navbar-brand"><img src="img/logo-ntransp.png"
					class="imagemnav"></a>
			</div>
			<div class="collapse navbar-collapse" id="barra-collapse">
				<ul class="nav navbar-nav navbar-right" style="margin-top: 15px;">


				</ul>
			</div>
		</div>
	</nav>
	<!-- FECHA BARRA DE NAVEGAÇÃO -->


	<!-- INICIA INFORMAÇÕES DE CLIENTES ------------------------------------------------------->
	<div class="cliente col-md-12">
		<div class="col-md-8 col-md-offset-2">
			<div class="panel panel-info"></div>
		</div>
	</div>
	<!--FECHA CLIENTE -->



	<!--  DADOS CADASTRAIS ---------------------------------------------------------------->
	<div class="informacoes col-md-12">

		<div class="col-md-8 col-md-offset-2">
			<div class="panel panel-info">
				<div class="panel panel-heading">
					<div class="panel-title">ALTERAR SENHA</div>
				</div>

				<div class="panel-body">


					<c:if test="${msg != null}">


						<div class="alert alert-success alert-dismissable">
							<a class="close" data-dismiss="alert" aria-label="close">×</a> <strong>${msg}</strong>
						</div>




					</c:if>

					<c:if test="${msg1 != null}">




						<div class="alert alert-danger alert-dismissable">
							<a class="close" data-dismiss="alert" aria-label="close">×</a> <strong>${msg1}</strong>
						</div>


					</c:if>
					<form method="post" action="Controle?cmd=alterar_senha"
						class="form-inline" id="form1">

						<div class="col-md-7">
							<div class="form-group" style="margin-bottom: 10px;">
								<label for="nome">NOME</label><br /> <input value="${nome}"
									type="text" name="nome" id="nome" title="Digite seu Nome"
									required class="form-control" readonly="readonly"
									placeholder="Digite seu Nome" style="min-width: 300px">
							</div>


							<div class="form-group" style="margin-bottom: 10px;">
								<label for="email">EMAIL</label><br /> <input value="${email}"
									type="email" name="email" id="email" required
									placeholder="Digite seu Email" title="Digite seu Email"
									class="form-control" readonly="readonly"
									style="min-width: 300px">
							</div>


							<div class="form-group" style="margin-bottom: 10px;">
								<label for="email">TELEFONE</label><br /> <input
									value="${telefone}" type="text" name="telefone" id="telefone"
									required placeholder="Digite seu Telefone"
									title="Digite seu Telefone" class="form-control"
									style="min-width: 300px">
							</div>




						</div>

						<div class="col-md-5">

							<div class="form-group" style="margin-bottom: 10px;">
								<label for="senhaatual">SENHA ATUAL</label><br /> <input
									type="password" name="senhaatual" id="senhaatual" required
									placeholder="Digite a Senha Atual" title="Digite a Senha Atual"
									class="form-control" style="min-width: 250px">
							</div>


							<div class="form-group" style="margin-bottom: 10px;">
								<label for="novasenha">NOVA SENHA</label><br /> <input
									type="password" name="novasenha" id="novasenha"
									title="Digite  a Nova Senha" required
									placeholder="Digite a Nova Senha" class="form-control"
									style="min-width: 250px">
							</div>

							<div class="form-group" style="margin-bottom: 10px;">
								<label for="confsenha">CONFIRME SUA SENHA</label><br /> <input
									type="password" name="confsenha" id="confsenha"
									title="Confirme a Nova Senha" required
									placeholder="Confirme a Nova Senha" class="form-control"
									oninput="validaSenha(this)" style="min-width: 250px">

							</div>

							<div class="form-group" style="margin-bottom: 10px;">
								<br />
								
								<!-- <button onclick="goBack()" class="btn btn-primary">Voltar</button> -->
								
								
								<a type="submit" class="btn btn-primary" href="Controle?cmd=voltar"
								   style="font-size: 16px; text-decoration: none; color: primary">Voltar</a>
								   

								<button type="submit" class="btn btn-info">Salvar</button>
							</div>
						</div>


					</form>


				</div>
			</div>
		</div>
	</div>


</body>

<!-- ONLOAD ----------------------------------------------------------------------->

<script>
	function goBack() {
		window.history.back()
	}
</script>

<!-- VALIDAÇÃO DE SENHA ----------------------------------------------------------------------->
<script>
	function validaSenha(input) {
		if (input.value != document.getElementById('novasenha').value) {
			input.setCustomValidity('Repita a senha corretamente');
		} else {
			input.setCustomValidity('');
		}
	}
</script>

<!-- MASCARA ----------------------------------------------------------------------->
<script type="text/javascript">
	$(function() {
		$("#tel").mask("(99)9999-99?99");
	});
</script>

<!-- MAPA GMAPS ----------------------------------------------------------------------->
<script type='text/javascript'>
	function init_map() {
		var myOptions = {
			zoom : 15,
			center : new google.maps.LatLng(-22.9113861, -43.19053710000003),
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById('gmap_canvas'),
				myOptions);
		marker = new google.maps.Marker({
			map : map,
			position : new google.maps.LatLng(-22.9113861, -43.19053710000003)
		});
		infowindow = new google.maps.InfoWindow({
			content : '<strong>Estamos aqui</strong><br>Av Mem de Sá, 272<br>'
		});
		google.maps.event.addListener(marker, 'click', function() {
			infowindow.open(map, marker);
		});
		infowindow.open(map, marker);
	}
	google.maps.event.addDomListener(window, 'load', init_map);
</script>

</html>
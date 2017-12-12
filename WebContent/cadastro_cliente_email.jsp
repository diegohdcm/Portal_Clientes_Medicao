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

<link rel="shortcut icon" href="LogoOri.ico" >
<title>Cadastro de Clientes</title>
</head>

<body>
<!-- Modal Alerta de cadsatro -->
	<div id="modalAtencaoCadastro" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">

			<!-- painel-->

			<div class="panel panel-info">

				<div class="panel panel-heading">
					<button type="button" class="close " data-dismiss="modal"
						style="color: red">&times;</button>
					<div class="panel-title text-center">Atenção!</div>
				</div>
				<div class="text-center">
					Para realização do cadastro tenha em mãos o CNPJ ou código do condomínio na Hidroluz.<br />
				</div>

				<div class="modal-footer"
					style="margin-top: 0px; margin-bottom: 5px">
					<div class="text-center">
<!-- 						<br /> Caso não tenha  <a -->
<!-- 							href="cadastro_cliente_email.jsp" -->
<!-- 							title="Clique para cadastrar-se">clique aqui.</a> -->
					</div>
					<br />
					<button type="button" class="btn btn-danger btn-sm"
						data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>
	<!-- FIM MODAL -->


	<!-- BARRA DE NAVEGAÇÃO -->
	<nav class="navbar navbar-fixed-top navbar-custom" id="mainNav">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#barra-collapse">
					<span class="sr-only">Barra Navegação</span> Menu&nbsp; <i
						class="fa fa-bars"></i>
				</button>
				<a href="inicio.jsp" class="navbar-brand"><img src="img/logo-ntransp.png"
					class="imagemnav"></a>
			</div>

		</div>
	</nav>
	<!-- FECHA BARRA DE NAVEGAÇÃO -->

	<!-- INICIA INFORMAÇÕES DE CLIENTES ------------------------------------------------------->
	<div class="cliente col-md-12">
		<div class="col-md-8 col-md-offset-2"></div>
	</div>
	<!--FECHA CLIENTE -->


	<!--  DADOS CADASTRAIS ---------------------------------------------------------------->
	<div class="informacoes col-md-12">

		<div class="col-md-8 col-md-offset-2">
			<div class="panel panel-info">
				<div class="panel panel-heading">
					<div class="panel-title">DADOS CADASTRAIS</div>
				</div>

				<div class="panel-body">




					<form method="post" action="Controle?cmd=cadastraemail"
						class="form-inline" id="form1">

						<c:if test="${msg != null}">
							<div class="alert alert-danger alert-dismissable">
								<a class="close" data-dismiss="alert" aria-label="close">×</a> <strong>${msg}</strong>
								
								<a href="esqueci_senha.jsp" style="text-align: left; margin-left: 10px;"
									title="Recupere sua senha">Esqueci minha senha</a>
							</div>
						</c:if>
						<div class="col-md-7">
							<div class="form-group" style="margin-bottom: 10px;">
								<label for="nome">NOME</label><br /> <input value="${nome}"
									type="text" name="nome" id="nome" title="Digite seu nome"
									required class="form-control" placeholder="Digite seu nome"  style="min-width:400px">
							</div>
                            <div class="form-group" style="margin-bottom: 10px;">
								<label for="email">EMAIL</label><br /> <input value="${email}"
									type="email" name="email" id="email" required
									placeholder="Digite seu e-mail" title="Digite seu e-mail"
									class="form-control"  style="min-width:400px">
							</div>
                            <div class="form-group" style="margin-bottom: 10px;">
								<label for="tel">TELEFONE</label><br /> <input type="text"
									name="tel" id="tel" title="Digite números"
									placeholder="Digite seu celular" class="form-control" style="min-width:250px">
							</div>
                            </div>
						<div class="col-md-5">
							<div class="form-group" style="margin-bottom: 10px;">
								<label for="novasenha">SENHA</label><br /> <input
									type="password" name="senha" id="senha" value="${senha}"
									title="Digite  a nova senha" required
									placeholder="Digite a nova senha" class="form-control" style="min-width:280px">
							</div>
							<div class="form-group" style="margin-bottom: 10px;">
								<label for="confsenha">CONFIRME SUA SENHA</label><br /> <input
									type="password" name="confsenha" id="confsenha"
									title="Confirme a nova senha" required
									placeholder="Confirme a nova senha" class="form-control"
									oninput="validaSenha(this)" style="min-width:280px"><br />
							</div>
                            <div class="form-group" style="margin-bottom: 10px;">
								<br />
								<button type="submit" class="btn btn-info ">Salvar</button>
								<a href="inicio.jsp" class="btn btn-primary">Voltar</a>
							</div>
                            </div>
					</form>
				</div>
			</div>
		</div>
	</div>


</body>

<!-- ONLOAD ----------------------------------------------------------------------->



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

<!-- Chama o modal atenção cadastro -->
<script type="text/javascript">
	$(window).on('load', function() {
		$('#modalAtencaoCadastro').modal('show');
	});
</script>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>

<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="//select2.github.io/select2/select2-3.5.2/select2.css">
	<link rel="stylesheet" href="css/select2-bootstrap.css">
	<link rel="stylesheet" href="css/gh-pages.css">
	
	
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/cerulean/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.js"></script>

<link rel="stylesheet" href="css/stylesistema.css">
<link rel="shortcut icon" href="LogoOri.ico" >
<title>Cadastro de Unidades</title>
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
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><i class="fa fa-user fa-2x"></i>&nbsp;Usuário</a>
					</li>
					<li><a href="#"> <i class="fa fa-phone fa-2x"></i>&nbsp;
							(21)2199-9999
					</a></li>
					<li><a href="#"><i class="fa fa-cog fa-2x"></i></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- FECHA BARRA DE NAVEGAÇÃO -->

	<div class="col-md-8 col-md-offset-2 cliente">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">
					<h3 style="margin-top: 0px;">Dados do Condomínio</h3>
				</div>
				<div class="row">
					<div class="col-md-2" style="text-align: left">
						<p>
							<b>Nome:</b>
						</p>
						<p>
							<b>Endereço:</b>
						</p>
					</div>
					<div class="col-md-4" style="text-align: left">
						<p>${nome}</p>
						<p>${endereco}</p>
					</div>
					<div class="col-md-2" style="text-align: left">
						<p>
							<b>Bairro:</b>
						</p>
						<p>
							<b>Cidade:</b>
						</p>
					</div>
					<div class="col-md-4" style="text-align: left">
						<p>${bairro}</p>

						<p>${cidade}</p>

					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="col-md-8 col-md-offset-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<form role="form" action="Controle?cmd=cadastrar" method="post">
					<div class="row">
						<h3 style="margin-top: 0px;">Cadastro da Unidade</h3>
						<div class="col-md-6">
							<label for="bloco">Selecione o Bloco:</label> <select
								class="form-control" name="bloco" id="bloco">
								<c:forEach var="item" items="${blocos}">
									<option>${item}</option>
								</c:forEach>
							</select> 
													
							<label for="unidade">Informe sua Unidade:</label> 
							
							<div class="select2-wrapper">
							<select
								class="form-control select2"  name="unidade"
								id="unidade">
								<option></option>
								<c:forEach var="item" items="${unidades}">
									<option>${item}</option>
								</c:forEach>
							</select> 
							
							</div>				
							
							
							<label for="nome">Informe o Nome:</label> <input type="text"
								name="nome" id="nome" placeholder="Digite o Nome"
								title="Digite o Nome"  class="form-control">
						</div>
						<div class="col-md-6">

							<label for="telefone">Informe o Telefone:</label> <input
								type="text" name="telefone" id="telefone"
								placeholder="Digite o Telefone" title="Digite o Telefone"
								required class="form-control"> 
								
							<label for="email">Informe
								o Email:</label> <input value="${email}" type="email" name="email" id="email"
								placeholder="Digite o Email" title="Digite o Email" 
								class="form-control"> <br>
								
								
							<button type="submit" class="btn btn-info">Enviar</button>

						</div>
					</div>
					<br>

					<p>${msg}</p>

				</form>
			</div>
		</div>
	</div>
</body>

<!-- MASCARA ----------------------------------------------------------------------->
<script type="text/javascript">
	$(function() {
		$("#telefone").mask("(99)99999-9999");
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$('input.typeahead').typeahead({
			name : 'Unidade',
			local : [ 'Audi', 'BMW', 'Bugatti', 'Ferrari' ]
		});
	});
</script>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//select2.github.io/select2/select2-3.4.2/select2.js"></script>
<script>
	$( ".select2" ).select2( { placeholder: "Selecione sua Unidade", maximumSelectionSize: 6 } );

	$( ":checkbox" ).on( "click", function() {
		$( this ).parent().nextAll( "select" ).select2( "enable", this.checked );
	});

	$( "#demonstrations" ).select2( { placeholder: "Select2 version", minimumResultsForSearch: -1 } ).on( "change", function() {
		document.location = $( this ).find( ":selected" ).val();
	} );

	$( "button[data-select2-open]" ).click( function() {
		$( "#" + $( this ).data( "select2-open" ) ).select2( "open" );
	});
</script>

</html>
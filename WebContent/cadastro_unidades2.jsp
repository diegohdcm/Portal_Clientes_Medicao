<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>

<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet"
	href="//select2.github.io/select2/select2-3.5.2/select2.css">
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

		</div>
	</nav>
	<!-- FECHA BARRA DE NAVEGAÇÃO -->

	<div class="col-md-8 col-md-offset-2 cliente"></div>

	<div class="col-md-8 col-md-offset-2">
		<div class="panel panel-info">
			<div class="panel panel-heading">
				<div class="panel-title">Cadastro da Unidade</div>
			</div>

			<div class="panel-body">

				<div>
					<form role="form" action="Controle?cmd=cadastrarsocial"
						method="post">
						<c:if test="${msg != null}">
							<div class="alert alert-danger alert-dismissable">
								<a class="close" data-dismiss="alert" aria-label="close">×</a> <strong>${msg}</strong>
							</div>
						</c:if>
							<div class="col-md-12" style="text-align: left; font-size: 16px">
								
                                <div class="panel panel-default">
                                <div class="panel-body">
                                   <b>Condomínio: </b>${nome}<br/>
                                    <b>Endereço: </b>${endereco}</div>
                                </div>
							</div>
                
						<div class="col-md-5">
							<div class="form-group">
								<label for="bloco">Selecione o Bloco:</label> <select
									class="form-control" name="bloco" id="bloco">
									<c:forEach var="item" items="${blocos}">
										<option>${item}</option>
									</c:forEach>
								</select>
							</div>
                            </div>
                            <div class="col-md-5">
							<div class="form-group">
								<label for="unidade">Informe sua Unidade:</label>
								<div class="select2-wrapper">
									<select class="form-control select2" name="unidade"
										id="unidade">
										<option></option>
										<c:forEach var="item" items="${unidades}">
											<option>${item}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
                        <div class="col-md-2">
						<button type="submit" class="btn btn-info" style="margin-top: 25px">Enviar</button>
                        </div>
						<br>
					</form>
				</div>
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

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//select2.github.io/select2/select2-3.4.2/select2.js"></script>
<script>
	$(".select2").select2({
		placeholder : "Selecione sua Unidade",
		maximumSelectionSize : 6
	});

	$(":checkbox").on("click", function() {
		$(this).parent().nextAll("select").select2("enable", this.checked);
	});

	$("#demonstrations").select2({
		placeholder : "Select2 version",
		minimumResultsForSearch : -1
	}).on("change", function() {
		document.location = $(this).find(":selected").val();
	});

	$("button[data-select2-open]").click(function() {
		$("#" + $(this).data("select2-open")).select2("open");
	});
</script>

</html>
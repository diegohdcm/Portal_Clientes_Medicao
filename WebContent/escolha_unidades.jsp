<%@ page import="entity.*, persistence.*, java.util.*"%>
<%
try{
if(request.getSession().getAttribute("EMAIL_USU")!=null && request.getSession().getAttribute("ENDERECO")==null){
	String ende =" ";
	request.getSession().setAttribute("ENDERECO", ende);
}else if(request.getSession().getAttribute("EMAIL_USU")==null && request.getSession().getAttribute("ENDERECO")!=null){
	String email =" ";
	request.getSession().setAttribute("EMAIL_USU", email);
}

if (request.getSession().getAttribute("EMAIL_USU")==null || request.getSession().getAttribute("ENDERECO")==null){
	
	request.setAttribute("msg",	"Sua sessão foi encerrada devido tempo de conexão inativo, favor realizar login novamente.");
 request.getRequestDispatcher("inicio.jsp").forward(request, response);
}

}catch(Exception ex){
	request.setAttribute(ex.getMessage(),"Acesso fora da sessão ...");
	request.getRequestDispatcher("inicio.jsp").forward(request, response);
}
%>
<!doctype html>
<html>

<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/stylesistema.css">
<link rel="stylesheet" href="jquery-filestyle.css" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
<script
	src='https://maps.googleapis.com/maps/api/js?key=AIzaSyBOGnYAV-oxy57rY3b2kggn5oHC-O64Jzg&callback=initMap'></script>

<link rel="shortcut icon" href="LogoOri.ico">
<title>Escolha Unidade</title>
</head>

<body>
	
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

		

		<div
			style="float: right; color: #fff; margin-right: 200px; margin-top: -100px">

			<a href="#" class="topo"><img height="36" width="36"
				src="${picture}" /><font size="3"><b>${nome}</b></font></a>
		</div>


		<div
			style="float: right; color: #fff; margin-right: 50px; margin-top: -65px">



			<a type="submit" class="btn btn-info" href="busca_unidade.jsp"
					target='blank'
					style="font-size: 15px; text-decoration: none; color: primary; margin-top: 10px;">Adicionar 
					unidade</a> <a type="submit" class="btn btn-info"
					href="inicio.jsp"
					style="font-size: 15px; text-decoration: none; color: primary; margin-top: 10px;">Sair</a>

		</div>

	</nav>

	
	<!-- FECHA BARRA DE NAVEGAÇÃO -->

	<div class="col-md-12 cliente">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="container">Escolha Unidade</div>
			</div>






			<c:if test="${msg != null}">

				<div class="alert alert-success alert-dismissable">
					<a class="close" data-dismiss="alert" aria-label="close"
						style="text-align: center;">×</a> <strong>${msg}</strong>

				</div>

			</c:if>

			<div class="panel-body">
				<table class="cell-border compact table-striped table-hover"
					id="tabela">
					<thead>
						<tr>

							<th>Nome</th>
							<th>Bloco</th>
							<th>Unidade</th>
							<th>Consumo</th>

						</tr>
					</thead>

					<tbody>
						<c:forEach items="${unidades}" var="linha">
							<tr>

								<td>${linha.cliente.razsoc_nome}</td>
								<td>${linha.bloco}</td>
								<td>${linha.unidade}</td>
								<td><a
									href="Controle?cmd=consultaunidade&cod_cli=${linha.cliente.codigo}&bloco=${linha.bloco}&unidade=${linha.unidade}&condo=${linha.cliente.nomfant_apel}"
									style="font-size: 18px; text-decoration: none; padding-right: 20px;"
									target='blank' title="Clique para exibir relatório"> <i
										class="fa fa-file-pdf-o"></i></a></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>

<!-- RODAPE ----------------------------------------------------------------->
<div class="col-md-12" style="margin-top: 30px">
	<div class="row rodape">
		<!-- PRIMEIRA COLUNA -->
		<div class="col-md-4">
			<div class="row" style="margin: 50px 0 80px 0;">
				<img src="img/logo-ntransp2.png" class="imagemrod">
			</div>

			<div class="row col-md-8" style="margin-left: 20px;">
				<a href="Controle?cmd=voltarsite" class="btn btn-danger btnv"
					title="Clique para ir ao site">IR PARA O SITE </a>
			</div>
		</div>

		<!-- SEGUNDA  COLUNA -->
		<div class="col-md-4" style="margin-top: 40px; margin-bottom: 40px">
			<div class="row">
				<h4
					style="text-align: left; margin-left: 55px; font-size: 20px; color: #E86536">
					<b>UNIDADE CENTRO</b>
				</h4>
				<hr>
			</div>

			<div class="row">
				<div style='overflow: block; height: 180px; width: 300px;'>
					<div id='gmap_canvas'
						style='height: 180px; width: 300px; margin-left: 60px'></div>
				</div>
			</div>

			<div class="row">
				<h5 style="text-align: left; margin-left: 60px; color: #fff">
					Av. Mem de Sá, 272 - Rio de Janeiro - RJ <br> CEP: 20330-153
				</h5>
			</div>
		</div>


		<!-- TERCEIRA  COLUNA -->
		<div class="col-md-4" style="margin-top: 40px; margin-bottom: 40px">
			<div class="row">
				<h4
					style="text-align: left; margin-left: 55px; font-size: 20px; color: #E86536">
					<b>SIGA-NOS NO FACEBOOK</b>
				</h4>
				<hr>
				<div style="position: absolute; margin-left: 58px;">
					<iframe
						src="https://www.facebook.com/plugins/page.php?href=https%3A%2F%2Fwww.facebook.com%2Fempresahidroluz%2F&amp;tabs=timeline&amp;width=300&amp;height=190&amp;small_header=false&amp;adapt_container_width=false&amp;hide_cover=false&amp;show_facepile=true&amp;appId=255842508155897"
						width="300" height="190" style="border: none; overflow: hidden"
						scrolling="no" frameborder="0" allowtransparency="true"></iframe>
				</div>
			</div>
		</div>
		<!-- FECHA TERCEIRA COLUNA-->
	</div>
</div>

<hr style="border-top: solid 15px; width: 100%; margin-top: none;">


<!-- MASCARA ----------------------------------------------------------------------->
<script type="text/javascript">
	$(function() {
		$("#cnpj").mask("99.999.999/9999-99");
	});
</script>


<!-- PAGINAÇÃO DATATABLE ----------------------------------------------------------------------->
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#tabela').DataTable({
			"pagingType" : "simple_numbers",
			"language" : {
				"paginate" : {
					"next" : "Próxima",
					"previous" : "Anterior"
				},
				"lengthMenu" : "Mostrar _MENU_ registros por página",
				"zeroRecords" : "Nada encontrado... - Desculpe",
				"info" : "Mostrando página _PAGE_ de _PAGES_",
				"infoEmpty" : "Sem registros",
				"infoFiltered" : "(Filtrado de _MAX_ registros)"
			}
		});
	});
</script>

<!-- SCRIPT GMAPS -->

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

<!-- <!-- Chama o modal SessãoExpirada -->
<!-- <script type="text/javascript"> -->
<!-- // 	$(window).on('load', function() { -->
<!-- // 		setTimeout(function() { -->
<!-- // 			$('#modalSessaoExpirada').modal('show'); -->
<!-- // 		}, 10000); -->
<!-- // 	}); -->
<!-- </script> -->

</html>
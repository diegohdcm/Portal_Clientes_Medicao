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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.0/jquery-ui.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/stylesistema.css">
<link rel="stylesheet" href="css/bootstrap.css">

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
<title>Portal Hidroluz Medição</title>
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

		<div class="col-md-12"
			style="margin-left: 350px; margin-top: -100px; width: 600px;">
			<div class="row">

				<div class="col-md-6" style="text-align: left;">
					
					<input type="hidden"
						value="${unidade}${bloco}" id="unidade" readonly="readonly">
					<a id="unidade" value="${unidade}"></a>
					<b>Condomínio: </b>${cond}<br> 
				    <b>Unidade: </b>${unidade}<br>
					<b>Bloco: </b>${bloco}


				</div>
			</div>
		</div>

		<div
			style="float: right; color: #fff; margin-right: 230px; margin-top: -100px">

			<a href="#" class="topo"><img height="36" width="36"
				src="${picture}" /><font size="3"><b>${nome}</b></font></a>
		</div>


		<div
			style="float: right; color: #fff; margin-right: 50px; margin-top: -65px">



			<a type="submit" class="btn btn-info"
				href="Controle?cmd=escolhaunidade"
				style="font-size: 11px; text-decoration: none; color: primary; margin-top: 10px;">Listar 
				Unidades</a> <a type="submit" class="btn btn-info"
				href="Controle?cmd=alterar"
				style="font-size: 11px; text-decoration: none; color: primary; margin-top: 10px;">Editar
				Cadastro</a> <a type="submit" class="btn btn-info"
				href="Controle?cmd=sair"
				style="font-size: 11px; text-decoration: none; color: primary; margin-top: 10px;">Sair</a>

		</div>

	</nav>


	<!-- FECHA BARRA DE NAVEGAÇÃO -->



	<!--  INFORMAÇÕES DA UNIDADE ---------------------------------------------------------------->
	<div class="panel-group cliente" id="accordion">

		<div class="panel panel-danger">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" href="#collapse1">Últimas leituras</a>
				</h4>
			</div>
			<div id="collapse1" class="panel-collapse collapse in">
				<div class="panel-body">
					<table class="table table-bordered table-hover table-condensed">
						<thead>
							<tr align="center">
								<th style="text-align: center;">Data Leitura</th>
								<th style="text-align: center;">Dias</th>
								<th style="text-align: center;">Consumo</th>
								<th style="text-align: center;">Consumo Diário</th>
								<th style="text-align: center;">Valor Consumo</th>
								<th style="text-align: center;">Valor Rateio</th>
								<th style="text-align: center;">Valor Total</th>
								<th style="text-align: center;">Detalhes do Consumo</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${lista}" var="linha" varStatus="myIndex">
								<tr>
									<td><fmt:formatDate pattern="dd/MM/yy"
											value="${linha.data_da_leitura}" /></td>
									<td>${linha.qtd_dias_lidos}</td>
									<td align="center"><fmt:formatNumber pattern="#####"
											value="${linha.consumo}" /> m³</td>
									<td><fmt:formatNumber type="number" maxFractionDigits="2"
											value="${linha.consumo /linha.qtd_dias_lidos }" /> m³</td>
									<td align="center"><fmt:formatNumber type="currency"
											value="${linha.valor_consumo_unidade}" /></td>

									<td align="center"><fmt:formatNumber type="currency"
											value="${linha.valor_rateio_area_comum}" /></td>
									<td align="center"><fmt:formatNumber type="currency"
											value="${linha.valor_consumo_total}" /></td>
									<td><a class="glyphicon glyphicon-plus"
										data-toggle="modal" data-target="#myModal${myIndex.count}"
										title="Clique para exibir os detalhes do consumo"></a></td>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="panel panel-danger" style="margin-top: 0px;">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" href="#collapse3">Ver Mais</a>
					</h4>
				</div>
				<div id="collapse3" class="panel-collapse collapse">
					<div class="panel-body">
						<table class="table table-bordered table-hover table-condensed">
							<thead align="center">
								<tr align="center">
									<th style="text-align: center;">Data Leitura</th>
									<th style="text-align: center;">Dias</th>
									<th style="text-align: center;">Consumo</th>
									<th style="text-align: center;">Consumo Diário</th>
									<th style="text-align: center;">Valor Consumo</th>
									<th style="text-align: center;">Valor Rateio</th>
									<th style="text-align: center;">Valor Total</th>
									<th style="text-align: center;">Detalhes do Consumo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${lista2}" var="linha" varStatus="myIndex">
									<tr>

										<td><fmt:formatDate pattern="dd/MM/yy"
												value="${linha.data_da_leitura}" /></td>
										<td>${linha.qtd_dias_lidos}</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.consumo}" /> m³</td>
										<td><fmt:formatNumber type="number" maxFractionDigits="2"
												value="${linha.consumo /linha.qtd_dias_lidos }" /> m³</td>
										<td align="center"><fmt:formatNumber type="currency"
												value="${linha.valor_consumo_unidade}" /></td>
										<td align="center"><fmt:formatNumber type="currency"
												value="${linha.valor_rateio_area_comum}" /></td>
										<td align="center"><fmt:formatNumber type="currency"
												value="${linha.valor_consumo_total}" /></td>
										<td><a class="glyphicon glyphicon-plus"
											data-toggle="modal"
											data-target="#myModal${myIndex.count + 3}"
											title="Clique para exibir os detalhes do consumo"></a></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>

					</div>
				</div>
			</div>


			<div class="container">
				<!-- Modal -->
				<div class="modal fade" id="myModal1" role="dialog">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="panel panel-info">
							<div class="panel panel-heading">
								<div class="panel-title">DETALHES DO CONSUMO</div>
							</div>

							<div class="panel-body">
								<table class="table table-bordered table-hover table-condensed">
									<thead>
										<tr>
											<th style="text-align: center; vertical-align: middle">Período</th>
											<th style="text-align: center; vertical-align: middle"> Localização </th>
											<th style="text-align: center; vertical-align: middle">Nº
												hidrômetro</th>
											<th style="text-align: center; vertical-align: middle">Indice
												Anterior</th>
											<th style="text-align: center; vertical-align: middle"> Indice
												Atual </th>
											<th style="text-align: center; vertical-align: middle">Consumo</th>
											<th style="text-align: center; vertical-align: middle">Fluxo
												Contínuo</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${listaDetalhes0}" var="linha">
											<tr>
												<td><fmt:formatDate pattern="dd/MM/yy"
														value="${linha.data_da_leitura}" /> <a> a </a> <fmt:formatDate
														pattern="dd/MM/yy"
														value="${linha.data_da_leitura_anterior}" /></td>
												<td>${linha.localizacao}</td>
												<td>${linha.numero_hidrometro}</td>
												<td align="center"><fmt:formatNumber pattern="#####"
														value="${linha.indice_antigo}" /> m³</td>
												<td align="center"><fmt:formatNumber pattern="#####"
														value="${linha.indice}" />m³</td>
												<td align="center"><fmt:formatNumber pattern="#####"
														value="${linha.consumo}" />m³</td>
												<c:if test="${linha.fluxo_continuo == true}">
													<td align="center">SIM</td>
												</c:if>
												<c:if test="${linha.fluxo_continuo == false}">
													<td align="center">NÃO</td>
												</c:if>
										</c:forEach>
									</tbody>
								</table>

								<div class="col-md-10">
<!-- 									  <a -->
<!-- 										href="http://hidroluz.com.br/novo/hidroluz-da-entrevista-a-revista-lowndes-report-sobre-medicao-individualizada/" -->
<!-- 										style="text-align: left; margin-left: 0px;" title="Fluxo" -->
<!-- 										target='_blank'>* Entenda o Fluxo Contínuo</a> -->
								</div>
								<div class="col-md-2">
									<button type="button" class="btn btn-danger text-right btn-sm"
										data-dismiss="modal"
										style="text-align: rigth; margin-left: 17px;">Fechar</button>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="container">
			<!-- Modal -->
			<div class="modal fade" id="myModal2" role="dialog">
				<div class="modal-dialog">
					<!-- Modal content-->
					<div class="panel panel-info">
						<div class="panel panel-heading">
							<div class="panel-title">DETALHES DO CONSUMO</div>
						</div>

						<div class="panel-body">
							<table class="table table-bordered table-hover table-condensed">
								<thead>
									<tr>
										<th style="text-align: center; vertical-align: middle">Período</th>
										<th style="text-align: center; vertical-align: middle">Localização</th>
										<th style="text-align: center; vertical-align: middle">Nº
											hidrômetro</th>
										<th style="text-align: center; vertical-align: middle">Indice
											Anterior</th>
										<th style="text-align: center; vertical-align: middle">Indice
											Atual</th>
										<th style="text-align: center; vertical-align: middle">Consumo</th>
										<th style="text-align: center; vertical-align: middle">Fluxo
											Contínuo</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listaDetalhes1}" var="linha">
										<tr>
											<td><fmt:formatDate pattern="dd/MM/yy"
													value="${linha.data_da_leitura}" /> <a> a </a> <fmt:formatDate
													pattern="dd/MM/yy"
													value="${linha.data_da_leitura_anterior}" /></td>
											<td>${linha.localizacao}</td>
											<td>${linha.numero_hidrometro}</td>
											<td align="center"><fmt:formatNumber pattern="#####"
													value="${linha.indice_antigo}" /> m³</td>
											<td align="center"><fmt:formatNumber pattern="#####"
													value="${linha.indice}" />m³</td>
											<td align="center"><fmt:formatNumber pattern="#####"
													value="${linha.consumo}" />m³</td>
											<c:if test="${linha.fluxo_continuo == true}">
												<td align="center">SIM</td>
											</c:if>
											<c:if test="${linha.fluxo_continuo == false}">
												<td align="center">NÃO</td>
											</c:if>
									</c:forEach>
								</tbody>
							</table>
							<div class="col-md-10">
<!-- 								 <a -->
<!-- 									href="http://hidroluz.com.br/novo/hidroluz-da-entrevista-a-revista-lowndes-report-sobre-medicao-individualizada/" -->
<!-- 									style="text-align: left; margin-left: 10px;" title="Fluxo" -->
<!-- 									target='_blank'>* Entenda o Fluxo Contínuo</a>  -->
							</div>
							<div class="col-md-2">
								<button type="button" class="btn btn-danger text-right btn-sm"
									data-dismiss="modal"
									style="text-align: rigth; margin-left: 17px;">Fechar</button>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModal3" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				
				<div class="panel panel-info">
					<div class="panel panel-heading">
						<div class="panel-title">DETALHES DO CONSUMO</div>
					</div>

					<div class="panel-body">
						<table class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th style="text-align: center; vertical-align: middle">Período</th>
									<th style="text-align: center; vertical-align: middle">Localização</th>
									<th style="text-align: center; vertical-align: middle">Nº
										hidrômetro</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Anterior</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Atual</th>
									<th style="text-align: center; vertical-align: middle">Consumo</th>
									<th style="text-align: center; vertical-align: middle">Fluxo
										Contínuo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaDetalhes2}" var="linha">
									<tr>
										<td><fmt:formatDate pattern="dd/MM/yy"
												value="${linha.data_da_leitura}" /> <a> a </a> <fmt:formatDate
												pattern="dd/MM/yy" value="${linha.data_da_leitura_anterior}" /></td>
										<td>${linha.localizacao}</td>
										<td>${linha.numero_hidrometro}</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice_antigo}" /> m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice}" />m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.consumo}" />m³</td>
										<c:if test="${linha.fluxo_continuo == true}">
											<td align="center">SIM</td>
										</c:if>
										<c:if test="${linha.fluxo_continuo == false}">
											<td align="center">NÃO</td>
										</c:if>
								</c:forEach>
							</tbody>
						</table>
						<div class="col-md-10">
<!-- 							 <a -->
<!-- 								href="http://hidroluz.com.br/novo/hidroluz-da-entrevista-a-revista-lowndes-report-sobre-medicao-individualizada/" -->
<!-- 								style="text-align: left; margin-left: 10px;" title="Fluxo" -->
<!-- 								target='_blank'>* Entenda o Fluxo Contínuo</a> -->
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-danger text-right btn-sm"
								data-dismiss="modal"
								style="text-align: rigth; margin-left: 17px;">Fechar</button>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModal4" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="panel panel-info">
					<div class="panel panel-heading">
						<div class="panel-title">DETALHES DO CONSUMO</div>
					</div>

					<div class="panel-body">
						<table class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th style="text-align: center; vertical-align: middle">Período</th>
									<th style="text-align: center; vertical-align: middle">Localização</th>
									<th style="text-align: center; vertical-align: middle">Nº
										hidrômetro</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Anterior</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Atual</th>
									<th style="text-align: center; vertical-align: middle">Consumo</th>
									<th style="text-align: center; vertical-align: middle">Fluxo
										Contínuo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaDetalhes3}" var="linha">
									<tr>
										<td><fmt:formatDate pattern="dd/MM/yy"
												value="${linha.data_da_leitura}" /> <a> a </a> <fmt:formatDate
												pattern="dd/MM/yy" value="${linha.data_da_leitura_anterior}" /></td>
										<td>${linha.localizacao}</td>
										<td>${linha.numero_hidrometro}</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice_antigo}" /> m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice}" />m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.consumo}" />m³</td>
										<c:if test="${linha.fluxo_continuo == true}">
											<td align="center">SIM</td>
										</c:if>
										<c:if test="${linha.fluxo_continuo == false}">
											<td align="center">NÃO</td>
										</c:if>
								</c:forEach>
							</tbody>
						</table>
						<div class="col-md-10">
<!-- 							 <a -->
<!-- 								href="http://hidroluz.com.br/novo/hidroluz-da-entrevista-a-revista-lowndes-report-sobre-medicao-individualizada/" -->
<!-- 								style="text-align: left; margin-left: 10px;" title="Fluxo" -->
<!-- 								target='_blank'>* Entenda o Fluxo Contínuo</a>  -->
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-danger text-right btn-sm"
								data-dismiss="modal"
								style="text-align: rigth; margin-left: 17px;">Fechar</button>

						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModal5" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="panel panel-info">
					<div class="panel panel-heading">
						<div class="panel-title">DETALHES DO CONSUMO</div>
					</div>

					<div class="panel-body">
						<table class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th style="text-align: center; vertical-align: middle">Período</th>
									<th style="text-align: center; vertical-align: middle">Localização</th>
									<th style="text-align: center; vertical-align: middle">Nº
										hidrômetro</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Anterior</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Atual</th>
									<th style="text-align: center; vertical-align: middle">Consumo</th>
									<th style="text-align: center; vertical-align: middle">Fluxo
										Contínuo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaDetalhes4}" var="linha">
									<tr>
										<td><fmt:formatDate pattern="dd/MM/yy"
												value="${linha.data_da_leitura}" /> <a> a </a> <fmt:formatDate
												pattern="dd/MM/yy" value="${linha.data_da_leitura_anterior}" /></td>
										<td>${linha.localizacao}</td>
										<td>${linha.numero_hidrometro}</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice_antigo}" /> m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice}" />m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.consumo}" />m³</td>
										<c:if test="${linha.fluxo_continuo == true}">
											<td align="center">SIM</td>
										</c:if>
										<c:if test="${linha.fluxo_continuo == false}">
											<td align="center">NÃO</td>
										</c:if>
								</c:forEach>
							</tbody>
						</table>
						<div class="col-md-10">
<!-- 						<a -->
<!-- 								href="http://hidroluz.com.br/novo/hidroluz-da-entrevista-a-revista-lowndes-report-sobre-medicao-individualizada/" -->
<!-- 								style="text-align: left; margin-left: 10px;" title="Fluxo" -->
<!-- 								target='_blank'>* Entenda o Fluxo Contínuo</a> -->
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-danger text-right btn-sm"
								data-dismiss="modal"
								style="text-align: rigth; margin-left: 17px;">Fechar</button>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModal6" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="panel panel-info">
					<div class="panel panel-heading">
						<div class="panel-title">DETALHES DO CONSUMO</div>
					</div>

					<div class="panel-body">
						<table class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th style="text-align: center; vertical-align: middle">Período</th>
									<th style="text-align: center; vertical-align: middle">Localização</th>
									<th style="text-align: center; vertical-align: middle">Nº
										hidrômetro</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Anterior</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Atual</th>
									<th style="text-align: center; vertical-align: middle">Consumo</th>
									<th style="text-align: center; vertical-align: middle">Fluxo
										Contínuo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaDetalhes5}" var="linha">
									<tr>
										<td><fmt:formatDate pattern="dd/MM/yy"
												value="${linha.data_da_leitura}" /> <a> a </a> <fmt:formatDate
												pattern="dd/MM/yy" value="${linha.data_da_leitura_anterior}" /></td>
										<td>${linha.localizacao}</td>
										<td>${linha.numero_hidrometro}</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice_antigo}" /> m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice}" />m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.consumo}" />m³</td>
										<c:if test="${linha.fluxo_continuo == true}">
											<td align="center">SIM</td>
										</c:if>
										<c:if test="${linha.fluxo_continuo == false}">
											<td align="center">NÃO</td>
										</c:if>
								</c:forEach>
							</tbody>
						</table>
						<div class="col-md-10">
<!-- 							<a -->
<!-- 								href="http://hidroluz.com.br/novo/hidroluz-da-entrevista-a-revista-lowndes-report-sobre-medicao-individualizada/" -->
<!-- 								style="text-align: left; margin-left: 10px;" title="Fluxo" -->
<!-- 								target='_blank'>* Entenda o Fluxo Contínuo</a> -->
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-danger text-right btn-sm"
								data-dismiss="modal"
								style="text-align: rigth; margin-left: 17px;">Fechar</button>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModal7" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="panel panel-info">
					<div class="panel panel-heading">
						<div class="panel-title">DETALHES DO CONSUMO</div>
					</div>

					<div class="panel-body">
						<table class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th style="text-align: center; vertical-align: middle">Período</th>
									<th style="text-align: center; vertical-align: middle">Localização</th>
									<th style="text-align: center; vertical-align: middle">Nº
										hidrômetro</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Anterior</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Atual</th>
									<th style="text-align: center; vertical-align: middle">Consumo</th>
									<th style="text-align: center; vertical-align: middle">Fluxo
										Contínuo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaDetalhes6}" var="linha">
									<tr>
										<td><fmt:formatDate pattern="dd/MM/yy"
												value="${linha.data_da_leitura}" /> <a> a </a> <fmt:formatDate
												pattern="dd/MM/yy" value="${linha.data_da_leitura_anterior}" /></td>
										<td>${linha.localizacao}</td>
										<td>${linha.numero_hidrometro}</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice_antigo}" /> m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice}" />m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.consumo}" />m³</td>
										<c:if test="${linha.fluxo_continuo == true}">
											<td align="center">SIM</td>
										</c:if>
										<c:if test="${linha.fluxo_continuo == false}">
											<td align="center">NÃO</td>
										</c:if>
								</c:forEach>
							</tbody>
						</table>
						<div class="col-md-10">
<!-- 						 	<a -->
<!-- 								href="http://hidroluz.com.br/novo/hidroluz-da-entrevista-a-revista-lowndes-report-sobre-medicao-individualizada/" -->
<!-- 								style="text-align: left; margin-left: 10px;" title="Fluxo" -->
<!-- 								target='_blank'>* Entenda o Fluxo Contínuo</a> -->
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-danger text-right btn-sm"
								data-dismiss="modal"
								style="text-align: rigth; margin-left: 17px;">Fechar</button>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModal8" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="panel panel-info">
					<div class="panel panel-heading">
						<div class="panel-title">DETALHES DO CONSUMO</div>
					</div>

					<div class="panel-body">
						<table class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th style="text-align: center; vertical-align: middle">Período</th>
									<th style="text-align: center; vertical-align: middle">Localização</th>
									<th style="text-align: center; vertical-align: middle">Nº
										hidrômetro</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Anterior</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Atual</th>
									<th style="text-align: center; vertical-align: middle">Consumo</th>
									<th style="text-align: center; vertical-align: middle">Fluxo
										Contínuo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaDetalhes7}" var="linha">
									<tr>
										<td><fmt:formatDate pattern="dd/MM/yy"
												value="${linha.data_da_leitura}" /> <a> a </a> <fmt:formatDate
												pattern="dd/MM/yy" value="${linha.data_da_leitura_anterior}" /></td>
										<td>${linha.localizacao}</td>
										<td>${linha.numero_hidrometro}</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice_antigo}" /> m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice}" />m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.consumo}" />m³</td>
										<c:if test="${linha.fluxo_continuo == true}">
											<td align="center">SIM</td>
										</c:if>
										<c:if test="${linha.fluxo_continuo == false}">
											<td align="center">NÃO</td>
										</c:if>
								</c:forEach>
							</tbody>
						</table>
						<div class="col-md-10">
<!-- 							<a -->
<!-- 								href="http://hidroluz.com.br/novo/hidroluz-da-entrevista-a-revista-lowndes-report-sobre-medicao-individualizada/" -->
<!-- 								style="text-align: left; margin-left: 10px;" title="Fluxo" -->
<!-- 								target='_blank'>* Entenda o Fluxo Contínuo</a>  -->
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-danger text-right btn-sm"
								data-dismiss="modal"
								style="text-align: rigth; margin-left: 17px;">Fechar</button>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModal9" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="panel panel-info">
					<div class="panel panel-heading">
						<div class="panel-title">DETALHES DO CONSUMO</div>
					</div>

					<div class="panel-body">
						<table class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th style="text-align: center; vertical-align: middle">Período</th>
									<th style="text-align: center; vertical-align: middle">Localização</th>
									<th style="text-align: center; vertical-align: middle">Nº
										hidrômetro</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Anterior</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Atual</th>
									<th style="text-align: center; vertical-align: middle">Consumo</th>
									<th style="text-align: center; vertical-align: middle">Fluxo
										Contínuo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaDetalhes8}" var="linha">
									<tr>
										<td><fmt:formatDate pattern="dd/MM/yy"
												value="${linha.data_da_leitura}" /> <a> a </a> <fmt:formatDate
												pattern="dd/MM/yy" value="${linha.data_da_leitura_anterior}" /></td>
										<td>${linha.localizacao}</td>
										<td>${linha.numero_hidrometro}</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice_antigo}" /> m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice}" />m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.consumo}" />m³</td>
										<c:if test="${linha.fluxo_continuo == true}">
											<td align="center">SIM</td>
										</c:if>
										<c:if test="${linha.fluxo_continuo == false}">
											<td align="center">NÃO</td>
										</c:if>
								</c:forEach>
							</tbody>
						</table>
						<div class="col-md-10">
<!-- 							<a -->
<!-- 								href="http://hidroluz.com.br/novo/hidroluz-da-entrevista-a-revista-lowndes-report-sobre-medicao-individualizada/" -->
<!-- 								style="text-align: left; margin-left: 10px;" title="Fluxo" -->
<!-- 								target='_blank'>* Entenda o Fluxo Contínuo</a> -->
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-danger text-right btn-sm"
								data-dismiss="modal"
								style="text-align: rigth; margin-left: 17px;">Fechar</button>

						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModal10" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="panel panel-info">
					<div class="panel panel-heading">
						<div class="panel-title">DETALHES DO CONSUMO</div>
					</div>

					<div class="panel-body">
						<table class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th style="text-align: center; vertical-align: middle">Período</th>
									<th style="text-align: center; vertical-align: middle">Localização</th>
									<th style="text-align: center; vertical-align: middle">Nº
										hidrômetro</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Anterior</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Atual</th>
									<th style="text-align: center; vertical-align: middle">Consumo</th>
									<th style="text-align: center; vertical-align: middle">Fluxo
										Contínuo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaDetalhes9}" var="linha">
									<tr>
										<td><fmt:formatDate pattern="dd/MM/yy"
												value="${linha.data_da_leitura}" /> <a> a </a> <fmt:formatDate
												pattern="dd/MM/yy" value="${linha.data_da_leitura_anterior}" /></td>
										<td>${linha.localizacao}</td>
										<td>${linha.numero_hidrometro}</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice_antigo}" /> m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice}" />m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.consumo}" />m³</td>
										<c:if test="${linha.fluxo_continuo == true}">
											<td align="center">SIM</td>
										</c:if>
										<c:if test="${linha.fluxo_continuo == false}">
											<td align="center">NÃO</td>
										</c:if>
								</c:forEach>
							</tbody>
						</table>
						<div class="col-md-10">
<!-- 							<a -->
<!-- 								href="http://hidroluz.com.br/novo/hidroluz-da-entrevista-a-revista-lowndes-report-sobre-medicao-individualizada/" -->
<!-- 								style="text-align: left; margin-left: 10px;" title="Fluxo" -->
<!-- 								target='_blank'>* Entenda o Fluxo Contínuo</a> -->
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-danger text-right btn-sm"
								data-dismiss="modal"
								style="text-align: rigth; margin-left: 17px;">Fechar</button>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModal11" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="panel panel-info">
					<div class="panel panel-heading">
						<div class="panel-title">DETALHES DO CONSUMO</div>
					</div>

					<div class="panel-body">
						<table class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th style="text-align: center; vertical-align: middle">Período</th>
									<th style="text-align: center; vertical-align: middle">Localização</th>
									<th style="text-align: center; vertical-align: middle">Nº
										hidrômetro</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Anterior</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Atual</th>
									<th style="text-align: center; vertical-align: middle">Consumo</th>
									<th style="text-align: center; vertical-align: middle">Fluxo
										Contínuo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaDetalhes10}" var="linha">
									<tr>
										<td><fmt:formatDate pattern="dd/MM/yy"
												value="${linha.data_da_leitura}" /> <a> a </a> <fmt:formatDate
												pattern="dd/MM/yy" value="${linha.data_da_leitura_anterior}" /></td>
										<td>${linha.localizacao}</td>
										<td>${linha.numero_hidrometro}</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice_antigo}" /> m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice}" />m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.consumo}" />m³</td>
										<c:if test="${linha.fluxo_continuo == true}">
											<td align="center">SIM</td>
										</c:if>
										<c:if test="${linha.fluxo_continuo == false}">
											<td align="center">NÃO</td>
										</c:if>
								</c:forEach>
							</tbody>
						</table>
						<div class="col-md-10">
<!-- 						<a -->
<!-- 							href="http://hidroluz.com.br/novo/hidroluz-da-entrevista-a-revista-lowndes-report-sobre-medicao-individualizada/" -->
<!-- 							style="text-align: left; margin-left: 10px;" title="Fluxo" -->
<!-- 							target='_blank'>* Entenda o Fluxo Contínuo</a> -->
						</div>	
						<div class="col-md-2">
								<button type="button" class="btn btn-danger text-right btn-sm"
									data-dismiss="modal"
									style="text-align: rigth; margin-left: 17px;">Fechar</button>

							</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModal12" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="panel panel-info">
					<div class="panel panel-heading">
						<div class="panel-title">DETALHES DO CONSUMO</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<th style="text-align: center; vertical-align: middle">Período</th>
									<th style="text-align: center; vertical-align: middle">Localização</th>
									<th style="text-align: center; vertical-align: middle">Nº
										hidrômetro</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Anterior</th>
									<th style="text-align: center; vertical-align: middle">Indice
										Atual</th>
									<th style="text-align: center; vertical-align: middle">Consumo</th>
									<th style="text-align: center; vertical-align: middle">Fluxo
										Contínuo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaDetalhes11}" var="linha">
									<tr>
										<td><fmt:formatDate pattern="dd/MM/yy"
												value="${linha.data_da_leitura}" /> <a> a </a> <fmt:formatDate
												pattern="dd/MM/yy" value="${linha.data_da_leitura_anterior}" /></td>
										<td>${linha.localizacao}</td>
										<td>${linha.numero_hidrometro}</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice_antigo}" /> m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.indice}" />m³</td>
										<td align="center"><fmt:formatNumber pattern="#####"
												value="${linha.consumo}" />m³</td>
										<c:if test="${linha.fluxo_continuo == true}">
											<td align="center">SIM</td>
										</c:if>
										<c:if test="${linha.fluxo_continuo == false}">
											<td align="center">NÃO</td>
										</c:if>
								</c:forEach>
							</tbody>
						</table>
						<div class="col-md-10">
<!-- 							<a -->
<!-- 								href="http://hidroluz.com.br/novo/hidroluz-da-entrevista-a-revista-lowndes-report-sobre-medicao-individualizada/" -->
<!-- 								style="text-align: left; margin-left: 10px;" title="Fluxo" -->
<!-- 								target='_blank'>* Entenda o Fluxo Contínuo</a> -->
						</div>
						<div class="col-md-2">
							<button type="button" class="btn btn-danger text-right btn-sm"
								data-dismiss="modal"
								style="text-align: rigth; margin-left: 17px;">Fechar</button>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- GRAFICO -------------------------------------------------------------------------------->
	<div class="grafico col-md-12" style="margin: 30px 0 30px 0">
		<div class="panel panel-info">
			<div class="panel panel-heading">
				<div class="panel-title">Valores dos últimos 12 meses</div>
			</div>
			<div class="panel-body">
				<div id="container"
					style="width: 850px; height: 400px; margin: 10px auto"></div>
			</div>
		</div>
	</div>
	<!-- FECHA GRAFICO -->


	<!-- GRAFICO -------------------------------------------------------------------------------->
	<div class="grafico col-md-12" style="margin: 30px 0 30px 0">
		<div class="panel panel-info">
			<div class="panel panel-heading">
				<div class="panel-title">Consumo</div>
			</div>
			<div class="panel-body">
				<div id="consumo"
					style="width: 850px; height: 400px; margin: 10px auto"></div>
			</div>
		</div>
	</div>
	<!-- FECHA GRAFICO -->


</body>

<br>
<!-- RODAPE ----------------------------------------------------------------->
<div class="col-md-12">
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

<!-- Chama o modal  de boas vindas -->
<script type="text/javascript">
	$(window).on('load', function() {
		setTimeout(function() {
			$('#modalSessaoExpirada').modal('show');
		}, 10000);
	});
</script>

<!-- SCRIPT GRAFICO -->
<!-- SCRIPT GRAFICO -->
<script type="text/javascript">
	Highcharts
			.chart(
					'container',
					{
						chart : {
							type : 'column'
						},
						title : {
							text : ''
						},
						subtitle : {
							text : ''
						},
						xAxis : {
							categories : retornaAtributos('data_da_leitura'),
							crosshair : true
						},
						yAxis : {
							min : 0,
							title : {
								text : 'Valor em Reais'
							}
						},
						tooltip : {
							headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
							pointFormat : '<tr><td style="color:{series.color};padding:0">R$: </td>'
									+ '<td style="padding:0"><b>{point.y:.2f}</b></td></tr>',
							footerFormat : '</table>',
							shared : true,
							useHTML : true
						},
						plotOptions : {
							column : {
								pointPadding : 0.2,
								borderWidth : 0
							}
						},
						series : [ {
							name : 'Valores',
							data : retornaAtributos('valor_consumo_total')
						} ]

						,
						responsive : {
							rules : [ {
								condition : {
									maxWidth : 500
								},
								chartOptions : {
									legend : {
										align : 'center',
										verticalAlign : 'bottom',
										layout : 'horizontal'
									},
									yAxis : {
										labels : {
											align : 'left',
											x : 0,
											y : -5
										},
										title : {
											text : null
										}
									},
									subtitle : {
										text : null
									},
									credits : {
										enabled : false
									}
								}
							} ]
						}
					});

	function retornaAtributos(atributo) {
		var vet = [];
		var unidade = document.getElementById('unidade').value;
		var out = "Output" + unidade + ".json";
		$.ajax({
			url : out,
			type : "GET",
			dataType : 'JSON',
			async : false,
			success : function(data) {
				$.each(data, function(key, value) {
					vet.push(value[atributo]);
				});
			}
		});
		return vet;
	}
</script>


<!-- SCRIPT GRAFICO -->
<script type="text/javascript">
	//alert("oi");

	Highcharts
			.chart(
					'consumo',
					{
						chart : {
							type : 'line'
						},
						title : {
							text : ''
						},
						subtitle : {
							text : ''
						},
						xAxis : {
							categories : retornaAtributos('data_da_leitura'),
							crosshair : true
						},
						yAxis : {
							min : 0,
							title : {
								text : 'Consumo em M³'
							}
						},
						tooltip : {
							headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
							pointFormat : '<tr><td style="color:{series.color};padding:0"></td>'
									+ '<td style="color:{series.color};padding:0"><b>{point.y:.0f}M³</b></td></tr>',
							footerFormat : '</table>',
							shared : true,
							useHTML : true
						},
						plotOptions : {
							column : {
								pointPadding : 0.2,
								borderWidth : 0
							}
						},
						series : [ {
							name : 'Consumo Unidade',
							data : retornaAtributos('consumo')
						}, {
							name : 'Consumo Médio Condomínio ',
							data : retornaAtributos('media_condo')
						} ],

						responsive : {
							rules : [ {
								condition : {
									maxWidth : 500
								},
								chartOptions : {
									legend : {
										align : 'center',
										verticalAlign : 'bottom',
										layout : 'horizontal'
									},
									yAxis : {
										labels : {
											align : 'left',
											x : 0,
											y : -5
										},
										title : {
											text : null
										}
									},
									subtitle : {
										text : null
									},
									credits : {
										enabled : false
									}
								}
							} ]
						}
					});

	function retornaAtributos(atributo) {
		var vet = [];
		var unidade = document.getElementById('unidade').value;
		var out = "Output" + unidade + ".json";
		$.ajax({
			url : out,
			type : "GET",
			dataType : 'JSON',
			async : false,
			success : function(data) {
				$.each(data, function(key, value) {
					vet.push(value[atributo]);
				});
			}
		});
		return vet;
	}
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

</html>
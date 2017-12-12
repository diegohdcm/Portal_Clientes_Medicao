<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<!doctype html>

<html>



<head>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>

<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="entity.*, persistence.*, config.*"%>


<link rel="stylesheet" href="css/botoes.css">
<link rel="stylesheet" href="css/bootstrap.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script src="js/bootstrap.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript"
	src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>

<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id"
	content="814156775949-ltn7105ab0u3fhapiusagvku8rg02i9h.apps.googleusercontent.com">

<link rel="shortcut icon" href="LogoOri.ico">
<title>Bem Vindo ao Portal Hidroluz Medição</title>

<link rel="stylesheet" href="css/style.css">

</head>

<body>

	<!-- Modal Boas Vindas -->
	<div id="modalBoasVindas" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">

			<!-- painel-->

			<div class="panel panel-info">

				<div class="panel panel-heading">
					<button type="button" class="close " data-dismiss="modal"
						style="color: red">&times;</button>
					<div class="panel-title text-center">Bem-vindo ao novo Portal
						Hidroluz!</div>
				</div>
				<div class="text-center">
					Agora para acessar informe seu email e senha cadastrados.
				</div>
				<div class="text-center">
						 Caso não tenha e-mail cadastrado, <a
							href="cadastro_cliente_email.jsp"
							title="Clique para cadastrar-se">clique aqui.</a>
				</div>
<br/>
				<div class="modal-footer"
					style="margin-top: 0px; margin-bottom: 5px">
					
					<div class="text-center">
						 Conheça também nosso aplicativo, já disponível para Android no Google Play.
					
					</div>
					
					<button type="button" class="btn btn-danger btn-sm"
						data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>
	<!-- FIM MODAL -->

	<div class="modal-dialog" style="margin-top: 10px;">

		<div class="modal-content">

			<div class="modal-header">

				<div align="center">

					<img src="img/logo-ntransp3.png" class="img-logo">

				</div>

				<h3 class="modal-title text-center" id="myModalLabel">Acesso ao
					Portal</h3>

			</div>



			<div class="modal-body">

				<form method="post" action="Controle?cmd=logar">

					<c:if test="${msg != null}">

						<div class="alert alert-danger alert-dismissable">

							<a class="close" data-dismiss="alert" aria-label="close">×</a> <strong>${msg}</strong>

						</div>

					</c:if>

					<c:if test="${msg_cad01 != null}">

						<div class="alert alert-success alert-dismissable">

							<a class="close" data-dismiss="alert" aria-label="close">×</a> <strong>${msg_cad01}</strong>

							<strong>${msg_cad01}</strong>

						</div>

					</c:if>

					<c:if test="${msg_cad02 != null}">

						<div class="alert alert-success alert-dismissable">

							<a class="close" data-dismiss="alert" aria-label="close">×</a> <strong>${msg_cad02}</strong>

							<a href="Controle?cmd=ReenviarEmail"
								style="text-align: left; margin-left: 10px;" title="Reenviar">Reenviar</a>

						</div>

					</c:if>



					<div class="form-group">

						<div class="input-group">

							<input type="text" class="form-control" id="Login"
								placeholder="Usuário" name="login"> <label for="Login"
								class="input-group-addon glyphicon glyphicon-user"></label>

						</div>

					</div>


					<div class="form-group">

						<div class="input-group">

							<input type="password" class="form-control" id="Password"
								placeholder="Senha" name="senha"> <label for="Password"
								class="input-group-addon glyphicon glyphicon-lock"></label>

						</div>

					</div>



					<div style="margin-bottom: 20px;">

						<div class="row">

							<div class="col-md-6">

								<a href="cadastro_cliente_email.jsp"
									style="text-align: right; margin-left: 5px;"
									title="Clique para cadastrar-se">Não tenho cadastro</a>

							</div>

							<div class="col-md-6">

								<a href="esqueci_senha.jsp"
									style="text-align: left; margin-left: 25px;"
									title="Recupere sua senha">Esqueci minha senha</a>

							</div>

						</div>

					</div>

					<div class="modal-footer">

						<input value="Acessar" type="submit"
							class="form-control btn btn-danger" style="color: white">



					</div>

					<div class="modal-footer" style="margin-top: -20px;">





						<div class="fb-login-button" data-max-rows="1" data-size="large"
							data-button-type="continue_with" data-show-faces="false"
							data-auto-logout-link="false" data-use-continue-as="false"
							onlogin="checkLoginState();"></div>


					</div>




				</form>



				<div id="fb-root"></div>
				<script>
					// This is called with the results from from FB.getLoginStatus().
					function statusChangeCallback(response) {
						console.log('statusChangeCallback');
						console.log(response);
						// The response object is returned with a status field that lets the
						// app know the current login status of the person.
						// Full docs on the response object can be found in the documentation
						// for FB.getLoginStatus().
						if (response.status === 'connected') {
							// Logged into your app and Facebook.
							//testAPI();
						} else if (response.status === 'not_authorized') {
							// The person is logged into Facebook, but not your app.
							document.getElementById('status').innerHTML = 'Login with Facebook ';
						} else {
							// The person is not logged into Facebook, so we're not sure if
							// they are logged into this app or not.
							document.getElementById('status').innerHTML = 'Login with Facebook ';
						}
					}
					// This function is called when someone finishes with the Login
					// Button. See the onlogin handler attached to it in the sample
					// code below.
					function checkLoginState() {
						FB.getLoginStatus(function(response) {
							statusChangeCallback(response);
							testAPI();
						});
					}
					window.fbAsyncInit = function() {
						FB.init({
							appId : '143997559518724',
							cookie : true, // enable cookies to allow the server to access 
							// the session
							xfbml : true, // parse social plugins on this page
							version : 'v2.2' // use version 2.2
						});
						// Now that we've initialized the JavaScript SDK, we call 
						// FB.getLoginStatus(). This function gets the state of the
						// person visiting this page and can return one of three states to
						// the callback you provide. They can be:
						//
						// 1. Logged into your app ('connected')
						// 2. Logged into Facebook, but not your app ('not_authorized')
						// 3. Not logged into Facebook and can't tell if they are logged into
						// your app or not.
						//
						// These three cases are handled in the callback function.

						FB.getLoginStatus(function(response) {
							statusChangeCallback(response);
						});
					};
					// Load the SDK asynchronously
					(function(d, s, id) {
						var js, fjs = d.getElementsByTagName(s)[0];
						if (d.getElementById(id))
							return;
						js = d.createElement(s);
						js.id = id;
						js.src = "//connect.facebook.net/pt_BR/sdk.js";
						fjs.parentNode.insertBefore(js, fjs);
					}(document, 'script', 'facebook-jssdk'));

					// Here we run a very simple test of the Graph API after login is
					// successful. See statusChangeCallback() for when this call is made.
					function testAPI() {
						console.log('Welcome! Fetching your information.... ');
						FB
								.api(
										'/me?fields=id,first_name,email,picture',
										function(response) {
											console
													.log('Successful login for: '
															+ response.name);

											window.location.href = "http://www.hidroluzportal.com.br/altera_cadastro.jsp?user_name="
													+ response.first_name
															.replace(" ", "_")
													+ "&user_email="
													+ response.email
													+ "&user_id="
													+ response.id
													+ "&user_origem=FACEBOOK&user_picture="
													+ response.picture.data.url;
										});
					}
				</script>


				<script type="text/javascript">
					function onSignIn(googleUser) {

						console.log("oi");
						// window.location.href='success.jsp';
						var profile = googleUser.getBasicProfile();
						var id = profile.getId();
						var imagurl = profile.getImageUrl();
						var name = profile.getName();
						var email = profile.getEmail();

						window.location.href = "http://www.hidroluzportal.com.br/altera_cadastro.jsp?user_email="
								+ email
								+ "&user_picture="
								+ imagurl
								+ "&user_origem=GOOGLE&user_id="
								+ id
								+ "&user_name=" + name;
					}

					var btn = document.getElementById("btn");

					btn.addEventListener("click", onSignIn);
				</script>

				<!-- Chama o modal  de boas vindas -->
				<script type="text/javascript">
					$(window).on('load', function() {
						$('#modalBoasVindas').modal('show');
					});
				</script>
			</div>
		</div>
	</div>
</body>
</html>
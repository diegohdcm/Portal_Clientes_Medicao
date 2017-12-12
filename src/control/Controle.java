package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jcommon.encryption.SimpleMD5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import config.EmailUtil;
import config.HibernateUtil;
import config.Mensagem;
import entity.Administracao_site;
import entity.Clientes;
import entity.Cont_cli;
import entity.Log_acesso_site;
import entity.Registros_leitura_medicao;
import entity.Unidades;
import entity.Usuarios;
import net.sf.jasperreports.engine.JasperRunManager;
import persistence.Administracao_siteDao;
import persistence.ClienteDao;
import persistence.Log_acesso_siteDao;
import persistence.Registros_leitura_medicaoDao;
import persistence.UnidadesDao;
import persistence.UsuariosDao;

@WebServlet("/Controle")
public class Controle extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String path;
	private String name;
	private File file;
	static HttpServletRequest req;
	static HttpServletResponse re;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cmd = request.getParameter("cmd");

		if (cmd.equalsIgnoreCase("limpar")) {
			limpar(request, response);
		} else if (cmd.equalsIgnoreCase("consultaunidade")) {
			info_unidade(request, response);
		} else if (cmd.equalsIgnoreCase("extrato")) {
			extrato(request, response);
		} else if (cmd.equalsIgnoreCase("alterar")) {
			alterar(request, response);
		} else if (cmd.equalsIgnoreCase("primeiroacesso")) {
			primeiroAcesso(request, response);
		} else if (cmd.equalsIgnoreCase("consultaunidadeadm")) {
			info_unidade_adm(request, response);
		} else if (cmd.equalsIgnoreCase("escolhaunidade")) {
			escolha_unidade(request, response);
		} else if (cmd.equalsIgnoreCase("sair")) {
			sair(request, response);
		} else if (cmd.equalsIgnoreCase("ReenviarEmail")) {
			ReenviarEmail(request, response);
		} else if (cmd.equalsIgnoreCase("voltar")) {
			Voltarcadastro(request, response);
		}  else if (cmd.equalsIgnoreCase("voltarsite")) {
			voltarsite(request, response);
		}

	}
	
	private void Voltarcadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String bloco = (String) request.getSession().getAttribute("BLOCO");
		String unidade = (String) request.getSession().getAttribute("UNIDADE");
		String nome = (String) request.getSession().getAttribute("NOME");
		Integer cod_cli = (Integer) request.getSession().getAttribute("COD_CLI");
		String cond = (String) request.getSession().getAttribute("CONDO");
	

		try {

			List<Registros_leitura_medicao> reg = new Registros_leitura_medicaoDao().findAll(cod_cli, bloco, unidade);

			List<Registros_leitura_medicao> reg2 = new Registros_leitura_medicaoDao().findDetalhes(cod_cli, bloco,
					unidade);

			Integer fim = reg.size();

			if (fim > 3) {

				List<Registros_leitura_medicao> part1 = reg.subList(0, 3);

				List<Registros_leitura_medicao> part2 = reg.subList(3, fim);

				request.setAttribute("lista", part1);
				request.setAttribute("lista2", part2);

			} else {

				List<Registros_leitura_medicao> part1 = reg;
				request.setAttribute("lista", part1);
				request.setAttribute("lista2", null);

			}

			Date dt = reg2.get(0).getData_da_leitura();
			int cont = 0;

			List<Registros_leitura_medicao> reg4 = new ArrayList<Registros_leitura_medicao>();

			for (int i = 0; i < reg2.size(); i++) {

				if (reg2.get(i).getData_da_leitura().equals(dt)) {
					reg4.add(reg2.get(i));
				}

				if (!dt.equals(reg2.get(i).getData_da_leitura())) {

					dt = reg2.get(i).getData_da_leitura();

					String lista = "listaDetalhes" + cont;
					request.setAttribute(lista, reg4);
					reg4 = new ArrayList<Registros_leitura_medicao>();
					cont++;
					i--;
				}

			}

			ServletContext context = request.getServletContext();
			path = context.getRealPath("/");

			// Cria arquivo
			file = new File(path + "Output" + unidade + bloco + ".json");

			// Se o arquivo nao existir, ele gera
			if (!file.exists()) {
				file.createNewFile();
			}

			Writer writer = new FileWriter(file);

			Gson gson = new GsonBuilder().create();

			List<Registros_leitura_medicao> reg3 = new Registros_leitura_medicaoDao().findAll(cod_cli, bloco, unidade);

			Collections.reverse(reg3);

			gson.toJson(reg3, writer);

			writer.close();

			
			request.setAttribute("nome", nome);
			request.setAttribute("picture", "img/user.png");
			request.setAttribute("cond", cond.trim());
			// request.setAttribute("ende", ende_global);
			request.setAttribute("unidade", unidade);
			request.setAttribute("bloco", bloco);
			
			request.getSession().setAttribute("CONDO", cond);
			request.getSession().setAttribute("BLOCO", bloco);
			request.getSession().setAttribute("UNIDADE", unidade);
			request.getSession().setAttribute("COD_CLI", cod_cli);
			
			request.getRequestDispatcher("principal.jsp").forward(request, response);

			// file.delete();

		} catch (Exception e) {
			
			request.setAttribute("msg",
					"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

			request.getRequestDispatcher("inicio.jsp").forward(request, response);

		}

	}

	private void sair(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		file.delete();
		
		request.getSession().invalidate();

		request.getRequestDispatcher("inicio.jsp").forward(request, response);

	}
	
	private void voltarsite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().invalidate();

		response.sendRedirect("http://www.hidroluz.com.br");

	}

	private void escolha_unidade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer ID = (Integer) request.getSession().getAttribute("ID_USUARIO");
		List<Unidades> u;
		try {
			u = new UnidadesDao().findAll_Unidades_Cli(ID);

                String nome = (String) request.getSession().getAttribute("NOME");
                String foto = (String) request.getSession().getAttribute("PICTURE");
              
				request.setAttribute("nome", nome);
				request.setAttribute("picture", foto);
				request.setAttribute("unidades", u);

				request.getRequestDispatcher("escolha_unidades.jsp").forward(request, response);

			

		} catch (Exception e) {
			
			request.setAttribute("msg",
					"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

			request.getRequestDispatcher("inicio.jsp").forward(request, response);

			e.printStackTrace();

		}

	}
	
	private void primeiroAcesso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer cod_cli = new Integer(request.getParameter("cod_cli"));
		String bloco = new String(request.getParameter("bloco"));
		String unidade = new String(request.getParameter("unidade"));
		String email = new String(request.getParameter("email"));
		String path = (String) request.getSession().getAttribute("PATH");
		UnidadesDao ud = new UnidadesDao();
		
		

		try {

			List<Unidades> unidades = ud.findByCodeUni(cod_cli, unidade, bloco);

			List<Usuarios> usua = new UsuariosDao().findEmail(email.toUpperCase());

			if (!unidades.isEmpty() && unidades != null) {

				if (usua.get(0).getId_usuario().equals(unidades.get(0).getUsuario().getId_usuario())) {

					ud.acesso(cod_cli, bloco, unidade);

					List<Registros_leitura_medicao> reg = new Registros_leitura_medicaoDao().findAll(cod_cli, bloco,
							unidade);

					List<Registros_leitura_medicao> reg2 = new Registros_leitura_medicaoDao().findDetalhes(cod_cli,
							bloco, unidade);

					Integer fim = reg.size();

					if (fim > 3) {

						List<Registros_leitura_medicao> part1 = reg.subList(0, 3);

						List<Registros_leitura_medicao> part2 = reg.subList(3, fim);

						request.setAttribute("lista", part1);
						request.setAttribute("lista2", part2);

					} else {

						List<Registros_leitura_medicao> part1 = reg;
						request.setAttribute("lista", part1);
						request.setAttribute("lista2", null);

					}

					Date dt = reg2.get(0).getData_da_leitura();
					int cont = 0;

					List<Registros_leitura_medicao> reg4 = new ArrayList<Registros_leitura_medicao>();

					for (int i = 0; i < reg2.size(); i++) {

						if (reg2.get(i).getData_da_leitura().equals(dt)) {
							reg4.add(reg2.get(i));
						}

						if (!dt.equals(reg2.get(i).getData_da_leitura())) {

							dt = reg2.get(i).getData_da_leitura();

							String lista = "listaDetalhes" + cont;
							request.setAttribute(lista, reg4);
							reg4 = new ArrayList<Registros_leitura_medicao>();
							cont++;
							i--;
						}

					}

					ServletContext context = request.getServletContext();
					path = context.getRealPath("/");

					// Cria arquivo
					
					
					file = new File(path + "Output" + unidade + bloco + ".json");

					// Se o arquivo nao existir, ele gera
					if (!file.exists()) {
						file.createNewFile();
					}

					Writer writer = new FileWriter(file);

					Gson gson = new GsonBuilder().create();

					List<Registros_leitura_medicao> reg3 = new Registros_leitura_medicaoDao().findAll(cod_cli, bloco,
							unidade);

					Collections.reverse(reg3);

					gson.toJson(reg3, writer);

					writer.close();

					request.setAttribute("nome", usua.get(0).getNome());
					request.setAttribute("picture", "img/user.png");
					request.setAttribute("cond",
					unidades.get(0).getCliente().getRazsoc_nome());
					// request.setAttribute("ende",
					// reg.get(0).getCliente().getEndereco());
					request.setAttribute("unidade", unidade);
					request.setAttribute("bloco", bloco);
					
					request.getSession().setAttribute("ID_USUARIO", usua.get(0).getId_usuario());
					request.getSession().setAttribute("NOME", usua.get(0).getNome());
					request.getSession().setAttribute("PICTURE", "img/user.png");
					
					
						request.getRequestDispatcher("principal.jsp").forward(request, response);
					
					
				} else {

					request.setAttribute("msg", "Essa unidade não é a que você realizou o cadastro.");

					request.getRequestDispatcher("inicio.jsp").forward(request, response);

				}

			} else {

				request.setAttribute("msg", "Essa unidade não é a que você realizou o cadastro.");

				request.getRequestDispatcher("inicio.jsp").forward(request, response);

			}
		} catch (Exception e) {
			
			request.setAttribute("msg",
					"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

			request.getRequestDispatcher("inicio.jsp").forward(request, response);

			e.printStackTrace();

		}

	}

	private void info_unidade_adm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = (String) request.getSession().getAttribute("PATH");
		Integer cod_cli = new Integer(request.getParameter("cod_cli"));
		String bloco = new String(request.getParameter("bloco"));
		String unidade = new String(request.getParameter("unidade"));

		try {

			List<Registros_leitura_medicao> reg = new Registros_leitura_medicaoDao().findAll(cod_cli, bloco, unidade);

			List<Registros_leitura_medicao> reg2 = new Registros_leitura_medicaoDao().findDetalhes(cod_cli, bloco,
					unidade);

			Integer fim = reg.size();

			if (fim > 3) {

				List<Registros_leitura_medicao> part1 = reg.subList(0, 3);

				List<Registros_leitura_medicao> part2 = reg.subList(3, fim);

				request.setAttribute("lista", part1);
				request.setAttribute("lista2", part2);

			} else {

				List<Registros_leitura_medicao> part1 = reg;
				request.setAttribute("lista", part1);
				request.setAttribute("lista2", null);

			}

			Date dt = reg2.get(0).getData_da_leitura();
			int cont = 0;

			List<Registros_leitura_medicao> reg4 = new ArrayList<Registros_leitura_medicao>();

			for (int i = 0; i < reg2.size(); i++) {

				if (reg2.get(i).getData_da_leitura().equals(dt)) {
					reg4.add(reg2.get(i));
				}

				if (!dt.equals(reg2.get(i).getData_da_leitura())) {

					dt = reg2.get(i).getData_da_leitura();

					String lista = "listaDetalhes" + cont;
					request.setAttribute(lista, reg4);
					reg4 = new ArrayList<Registros_leitura_medicao>();
					cont++;
					i--;
				}

			}

			ServletContext context = request.getServletContext();
			path = context.getRealPath("/");

			// Cria arquivo
			file = new File(path + "Output" + unidade + bloco + ".json");

			// Se o arquivo nao existir, ele gera
			if (!file.exists()) {
				file.createNewFile();
			}

			Writer writer = new FileWriter(file);

			Gson gson = new GsonBuilder().create();

			List<Registros_leitura_medicao> reg3 = new Registros_leitura_medicaoDao().findAll(cod_cli, bloco, unidade);

			Collections.reverse(reg3);

			gson.toJson(reg3, writer);

			writer.close();

			request.setAttribute("nome", "Usuário");
			request.setAttribute("picture", "img/user.png");
			
			String nome = (String) request.getSession().getAttribute("NOME");
			String ende = (String) request.getSession().getAttribute("ENDERECO");
			
			request.setAttribute("cond", nome);
			request.setAttribute("ende", ende);
			request.setAttribute("unidade", unidade);
			request.setAttribute("bloco", bloco);
			
			
			request.getSession().setAttribute("BLOCO", bloco);
			request.getSession().setAttribute("UNIDADE", unidade);
			request.getSession().setAttribute("COD_CLI", cod_cli);
			
			request.getRequestDispatcher("principal_adm.jsp").forward(request, response);

		} catch (Exception e) {
			
			request.setAttribute("msg",
					"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

			request.getRequestDispatcher("inicio.jsp").forward(request, response);

		}

	}

	private void info_unidade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			Integer cod_cli = new Integer(request.getParameter("cod_cli"));
			String bloco = new String(request.getParameter("bloco"));
			String unidade = new String(request.getParameter("unidade"));
			String cond = new String(request.getParameter("condo"));
			String nome = (String) request.getSession().getAttribute("NOME");

			try {

				List<Registros_leitura_medicao> reg = new Registros_leitura_medicaoDao().findAll(cod_cli, bloco, unidade);

				List<Registros_leitura_medicao> reg2 = new Registros_leitura_medicaoDao().findDetalhes(cod_cli, bloco,
						unidade);

				Integer fim = reg.size();

				if (fim > 3) {

					List<Registros_leitura_medicao> part1 = reg.subList(0, 3);

					List<Registros_leitura_medicao> part2 = reg.subList(3, fim);

					request.setAttribute("lista", part1);
					request.setAttribute("lista2", part2);

				} else {

					List<Registros_leitura_medicao> part1 = reg;
					request.setAttribute("lista", part1);
					request.setAttribute("lista2", null);

				}

				Date dt = reg2.get(0).getData_da_leitura();
				int cont = 0;

				List<Registros_leitura_medicao> reg4 = new ArrayList<Registros_leitura_medicao>();

				for (int i = 0; i < reg2.size(); i++) {

					if (reg2.get(i).getData_da_leitura().equals(dt)) {
						reg4.add(reg2.get(i));
					}

					if (!dt.equals(reg2.get(i).getData_da_leitura())) {

						dt = reg2.get(i).getData_da_leitura();

						String lista = "listaDetalhes" + cont;
						request.setAttribute(lista, reg4);
						reg4 = new ArrayList<Registros_leitura_medicao>();
						cont++;
						i--;
					}

				}

				ServletContext context = request.getServletContext();
				path = context.getRealPath("/");

				// Cria arquivo
				file = new File(path + "Output" + unidade + bloco + ".json");

				// Se o arquivo nao existir, ele gera
				if (!file.exists()) {
					file.createNewFile();
				}

				Writer writer = new FileWriter(file);

				Gson gson = new GsonBuilder().create();

				List<Registros_leitura_medicao> reg3 = new Registros_leitura_medicaoDao().findAll(cod_cli, bloco, unidade);

				Collections.reverse(reg3);

				gson.toJson(reg3, writer);

				writer.close();

				
				request.setAttribute("nome", nome);
				request.setAttribute("picture", "img/user.png");
				request.setAttribute("cond", cond.trim());
				// request.setAttribute("ende", ende_global);
				request.setAttribute("unidade", unidade);
				request.setAttribute("bloco", bloco);
				request.getRequestDispatcher("principal.jsp").forward(request, response);
				
				request.getSession().setAttribute("CONDO", cond);
				request.getSession().setAttribute("BLOCO", bloco);
				request.getSession().setAttribute("UNIDADE", unidade);
				request.getSession().setAttribute("COD_CLI", cod_cli);

				// file.delete();

			} catch (Exception e) {
				
				request.setAttribute("msg",
						"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

				request.getRequestDispatcher("inicio.jsp").forward(request, response);

			}

		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cmd = request.getParameter("cmd");
		System.out.println(cmd);
		if (cmd.equalsIgnoreCase("logar")) {
			logar(request, response);
		} else if (cmd.equalsIgnoreCase("buscar")) {
			buscar_cliente(request, response);
		} else if (cmd.equalsIgnoreCase("cadastrarsocial")) {
			cadastrarSocial(request, response);
		} else if (cmd.equalsIgnoreCase("editar")) {
			editar(request, response);
		} else if (cmd.equalsIgnoreCase("upload")) {
			upload(request, response);
		} else if (cmd.equalsIgnoreCase("service")) {
			service(request, response);
		} else if (cmd.equalsIgnoreCase("cadastraemail")) {
			cadastra_email(request, response);
		} else if (cmd.equalsIgnoreCase("esquecisenha")) {
			esqueci_senha(request, response);
		} else if (cmd.equalsIgnoreCase("info_unidade")) {
			info_unidade(request, response);
		} else if (cmd.equalsIgnoreCase("alterar_senha")) {
			alterar_senha(request, response);
		}
	}
	
	private void alterar_senha(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email = new String(request.getParameter("email"));
		String senha_antiga = new String(request.getParameter("senhaatual"));
		String senha = new String(request.getParameter("novasenha"));
		String telefone = new String(request.getParameter("telefone"));
		String nome = (String) request.getSession().getAttribute("NOME");
	//	String login = (String) request.getSession().getAttribute("LOGIN");

		SimpleMD5 md5 = new SimpleMD5(senha_antiga, "");
		senha_antiga = md5.toHexString().toUpperCase();

		try {

			List<Usuarios> u = new UsuariosDao().findEmail(email.toUpperCase());

			if (senha_antiga.equals(u.get(0).getSenha().trim())) {

				new UsuariosDao().update(telefone, senha, email.toUpperCase());

				

				 
				request.setAttribute("nome", nome);
				//request.setAttribute("login", login);
				request.setAttribute("email", email);
				request.setAttribute("telefone", telefone);

				request.setAttribute("msg", "Senha atualizada com Sucesso.");
				request.getRequestDispatcher("altera_cadastro2.jsp").forward(request, response);

			} else {

				
				request.setAttribute("nome", nome);
				request.setAttribute("telefone", telefone);
			//	request.setAttribute("login", login);
				request.setAttribute("email", email);

				request.setAttribute("msg1", "Senha atual incorreta.");
				request.getRequestDispatcher("altera_cadastro2.jsp").forward(request, response);
			}

		
		} catch (Exception e) {
			
			request.setAttribute("msg",
					"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

			request.getRequestDispatcher("inicio.jsp").forward(request, response);
			
			e.printStackTrace();

		}

	}

	private void esqueci_senha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = new String(request.getParameter("email"));

		try {

			List<Usuarios> u = new UsuariosDao().findEmail(email.toUpperCase());

			if (u == null || u.isEmpty()) {

				request.setAttribute("msg", "Email não cadastrado!! Deseja se Cadastrar ??? ");
				request.getRequestDispatcher("esqueci_senha.jsp").forward(request, response);

			} else {

				Integer i = 0;
				String senha_nova;

				Random random = new Random();
				i = random.nextInt((9999 - 1000) + 1) + 1000;
				senha_nova = i.toString();

				EnviarEmailNovaSenha(u.get(0).getNome(), u.get(0).getEmail(), senha_nova);

				new UsuariosDao().esqueci_senha(senha_nova, email.toUpperCase());

				request.setAttribute("msg_cad01", "Foi enviada para o seu e-mail uma nova senha.");
				request.getRequestDispatcher("inicio.jsp").forward(request, response);

			}

		} catch (Exception e) {
			
			request.setAttribute("msg",
					"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

			request.getRequestDispatcher("inicio.jsp").forward(request, response);

			e.printStackTrace();

		}

	}

	private void EnviarEmailNovaSenha(String nome, String email, String senha_nova) {
		Mensagem m = new Mensagem();
		m.setPara(email);
		m.setAssunto("Portal Hidroluz - Solicitação de Nova Senha");
		StringBuffer texto = new StringBuffer();

		String center = "center";
		String zero = "0";
		String seis = "600";
		String logo = "https://uploaddeimagens.com.br/images/001/089/101/full/LogoOriginal.png";

		texto.append("<table border=" + 0 + " width=" + 600 + " cellspacing=" + 0 + " cellpadding=" + 0 + " align="
				+ "center" + ">");
		texto.append("<tbody>");
		texto.append("<tr bgcolor=" + "#E9EBEC" + " height=" + 80 + ">");
		texto.append("<td align=" + "center" + "><img src=" + logo + " alt=" + "Logo_Original" + " border=" + 0
				+ " width=" + 132 + " height=" + 48 + ">&nbsp;</td>");
		texto.append("</tr>");
		texto.append("</tbody>");
		texto.append("<tr>");

		texto.append("<table border=" + 0 + " width=" + 600 + " cellspacing=" + 0 + " + align=" + "center" + ">");
		texto.append("<tbody>");
		texto.append("<tr>");

		texto.append("<td align=" + "left" + " height=" + 60 + "width=" + 600 + ">");

		texto.append("<h4 style=" + center + ">Olá, " + nome.trim() + "!;</h4>");
		texto.append("<h4 style=" + center
				+ ">Não consegue lembrar sua senha? Não tem problema, acontece com todos nós.</h4>");
		texto.append("<h4 style=" + center + ">Abaixo sua nova Senha:</h4>");
		texto.append("<h4 style=" + center + ">Usuário: " + email.trim().toLowerCase() + "</h4>");
		texto.append("<h4 style=" + center + ">Senha: " + senha_nova + "</h4>");
		texto.append("</td></tr><tr><td>");

		texto.append("<table border=" + 0 + " width=" + 600 + " cellspacing=" + 0 + " cellpadding=" + 0 + " align="
				+ "center" + ">");
		texto.append("<tbody><tr bgcolor=" + "#E9EBEC" + " height=" + 80 + ">");
		texto.append("<td style=" + "width: 435px;" + "height=" + 80 + ">");
		texto.append("<h4>&nbsp;Conheça a Hidroluz nas Redes Sociais:</h4>");
		texto.append("</td>");
		texto.append("<td style=" + "width: 77.5px;" + " align=" + "center" + " valign=" + "middle " + " height=" + 50
				+ "><a title=" + "Facebook Hidroluz" + " href=" + "https://www.facebook.com/grupohidroluz/" + "rel="
				+ "noopener" + " data-saferedirecturl="
				+ "https://www.google.com/url?hl=pt-BR&amp;q=https://www.facebook.com/bancointeroficial&amp;source=gmail&amp;ust=1505310262242000&amp;usg=AFQjCNHwA7jbIcJdJJzccSCvsARnMtrF9g"
				+ "><img class=" + "CToWUd" + " src="
				+ "https://ci3.googleusercontent.com/proxy/dhmsFGOPjq7FXnyJaV73yY0xVxFpEP0iXfGDehXkMVzJvz0BA7c_mx4FBEqgae_SjZMdgjC72FujU9PhytaXIiEqdApZxf7MKJFs5I09KNm3O4Mu5Dn0boSui5Qs405am1EfJ6h8lXIZNeuKI2xNs3PwRHffqWs=s0-d-e1-ft#http://marketing.bancointer.com.br/comunicacao/e-mail-mkt-institucional/imagens/icon-facebook.png"
				+ " alt=" + "Facebook Hidroluz" + " width=" + 24 + " height=" + 24 + " border=" + 0 + " /></a></td>");
		texto.append(" <td style=" + "width: 122.5px;" + " height=" + 50 + ">");
		texto.append("<td style=" + "width: 51px;" + ">&nbsp;</td>");
		texto.append("</tr></tbody></table>");
		texto.append("</td></tr>");
		texto.append("</td></tr>");
		texto.append("</tbody></table></table>");

		m.setMensagem(texto.toString());

		try {

			String msg = new EmailUtil().enviarEmail(m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void cadastra_email(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nome = new String(request.getParameter("nome"));
		String email = new String(request.getParameter("email"));
		String senha = new String(request.getParameter("senha"));
		String telefone = new String(request.getParameter("tel"));

		try {

			List<Usuarios> u = new UsuariosDao().findEmail(email.toUpperCase());

			if (u == null || u.isEmpty()) {

				Usuarios usu = new Usuarios();
				usu.setId_usuario(null);
				usu.setNome(nome);
				usu.setFoto("");
				usu.setId_social("");
				usu.setOrigem("EMAIL");
				usu.setTelefone(telefone);
				usu.setEmail(email.toUpperCase());

				SimpleMD5 md5 = new SimpleMD5(senha, "");
				senha = md5.toHexString().toUpperCase();

				usu.setSenha(senha);

				new UsuariosDao().gravar(usu);

				request.getSession().setAttribute("EMAIL_USU", email.toUpperCase());
				request.getSession().setAttribute("ORIGEM", "EMAIL");

				response.sendRedirect("busca_unidade.jsp");

			} else {

				request.setAttribute("msg", "Email Já cadastrado!! Esqueceu sua Senha ??? ");
				request.getRequestDispatcher("cadastro_cliente_email.jsp").forward(request, response);

			}

		} catch (Exception e) {

			request.setAttribute("msg",
					"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

			request.getRequestDispatcher("inicio.jsp").forward(request, response);

			e.printStackTrace();

		}

	}

	protected void upload(HttpServletRequest request, HttpServletResponse response) {

		String name = (String) request.getSession().getAttribute("NAME");
		String path = (String) request.getSession().getAttribute("PATH");
		String cond = (String) request.getSession().getAttribute("EMAIL_USU");
		String nome = (String) request.getSession().getAttribute("NOME");
		String ende = (String) request.getSession().getAttribute("ENDERECO");
		List<Unidades> lista = (List<Unidades>) request.getSession().getAttribute("LISTA");

		ServletContext context = request.getServletContext();
		path = context.getRealPath("/");

		File file = new File(path + "contas/");
		file.mkdirs();

		// if(ServletFileUpload.isMultipartContent(request)){
		try {
			List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

			for (FileItem item : multiparts) {
				if (!item.isFormField()) {
					name = new File(item.getName()).getName();

					item.write(new File(file + File.separator + name));
				}
			}

			EnviarEmail(nome);

			File fl = new File(file + File.separator + name);

			fl.delete();

			// File uploaded successfully
			request.setAttribute("msg", "Conta Enviada com sucesso");

			
			request.setAttribute("cond", nome);
			request.setAttribute("ende", ende);

			request.setAttribute("lista", lista);
			request.getRequestDispatcher("consulta_unidades.jsp").forward(request, response);

		} catch (Exception ex) {
			request.setAttribute("msg", "Erro no envio " + ex);
		}

		// }else{
		// request.setAttribute("message",
		// "Sorry this Servlet only handles file upload request");
		// }
		//

	}

	public void EnviarEmail(String nome) {
		
		

		Mensagem m = new Mensagem();
		m.setPara("medicao@hidroluz.com.br");
		m.setAssunto("CONTA AGUA DO " + nome);
		m.setMensagem("Em anexo a conta de água.");

		try {

			System.out.println(path + "contas/" + name);

			String msg = new EmailUtil().enviarEmailAnexo(m, path + "contas/" + name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ReenviarEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = (String) request.getSession().getAttribute("EMAIL_USU");
		String nome = (String) request.getSession().getAttribute("NOME_USU");
		String bloco = (String) request.getSession().getAttribute("BLOCO_USU");
		String unidade = (String) request.getSession().getAttribute("UNIDADE_USU");
		Integer cod_cli = (Integer) request.getSession().getAttribute("COD_CLI");

		Mensagem m = new Mensagem();
		m.setPara(email);
		m.setAssunto("Cadastro no Portal Hidroluz");
		StringBuffer texto = new StringBuffer();

		String logo = "https://uploaddeimagens.com.br/images/001/089/101/full/LogoOriginal.png";
		String clique = "https://uploaddeimagens.com.br/images/001/089/105/thumb/clique_aqui.png";

		texto.append("<table border=" + 0 + " width=" + 600 + " cellspacing=" + 0 + " cellpadding=" + 0 + " align="
				+ "center" + ">");
		texto.append("<tbody>");
		texto.append("<tr bgcolor=" + "#E9EBEC" + " height=" + 80 + ">");
		texto.append("<td align=" + "center" + "><img src=" + logo + " alt=" + "Logo_Original" + " border=" + 0
				+ " width=" + 132 + " height=" + 48 + ">&nbsp;</td>");
		texto.append("</tr>");
		texto.append("</tbody>");
		texto.append("<tr>");

		texto.append("<table border=" + 0 + " width=" + 600 + " cellspacing=" + 0 + " + align=" + "center" + ">");
		texto.append("<tbody>");
		texto.append("<tr>");

		texto.append("<td align=" + "center" + " height=" + 60 + "width=" + 600 + ">");

		texto.append("<h4>Olá, " + nome.trim() + "!</h4>");
		texto.append("<h4"
				+ ">Obrigado por realizar seu cadastro. Para confirmar seu e-mail e ter acesso as suas informações de consumo de água, clique no botão abaixo:.</h4>");

		texto.append("<h4 style=" + "text-align: center;" + ">&nbsp;<a href="
				+ "http://www.hidroluzportal.com.br/Controle?cmd=primeiroacesso&cod_cli=" + cod_cli + "&bloco=" + bloco
				+ "&unidade=" + unidade + "&email=" + email.toUpperCase().trim() + ">" + "<img src=" + clique
				+ " width=" + 60 + " height=" + 25 + "/><a/>&nbsp;</h3>");

		texto.append("<h4 style=" + "text-align: center;"
				+ ">Se tiver problemas com o botão, copie e cole o link abaixo em uma nova janela de seu navegador:</h4>");

		texto.append("<h4 style=" + "text-align: center;"
				+ ">http://www.hidroluzportal.com.br/Controle?cmd=primeiroacesso&cod_cli=" + cod_cli + "&bloco=" + bloco
				+ "&unidade=" + unidade + "&email=" + email.toUpperCase().trim() + "&nbsp;</h4>");

		texto.append("<h4 style=" + "text-align: center;"
				+ ">Lembramos que cada usuário pode acompanhar seu histórico de consumo pelo Portal Medição ou App.</h4>");

		texto.append("</td></tr><tr><td>");

		texto.append("<table border=" + 0 + " width=" + 600 + " cellspacing=" + 0 + " cellpadding=" + 0 + " align="
				+ "center" + ">");
		texto.append("<tbody><tr bgcolor=" + "#E9EBEC" + " height=" + 80 + ">");
		texto.append("<td style=" + "width: 435px;" + "height=" + 80 + ">");
		texto.append("<h4>&nbsp;Conheça a Hidroluz nas Redes Sociais:</h4>");
		texto.append("</td>");
		texto.append("<td style=" + "width: 77.5px;" + " align=" + "center" + " valign=" + "middle " + " height=" + 50
				+ "><a title=" + "Facebook Hidroluz" + " href=" + "https://www.facebook.com/grupohidroluz/" + "rel="
				+ "noopener" + " data-saferedirecturl="
				+ "https://www.google.com/url?hl=pt-BR&amp;q=https://www.facebook.com/bancointeroficial&amp;source=gmail&amp;ust=1505310262242000&amp;usg=AFQjCNHwA7jbIcJdJJzccSCvsARnMtrF9g"
				+ "><img class=" + "CToWUd" + " src="
				+ "https://ci3.googleusercontent.com/proxy/dhmsFGOPjq7FXnyJaV73yY0xVxFpEP0iXfGDehXkMVzJvz0BA7c_mx4FBEqgae_SjZMdgjC72FujU9PhytaXIiEqdApZxf7MKJFs5I09KNm3O4Mu5Dn0boSui5Qs405am1EfJ6h8lXIZNeuKI2xNs3PwRHffqWs=s0-d-e1-ft#http://marketing.bancointer.com.br/comunicacao/e-mail-mkt-institucional/imagens/icon-facebook.png"
				+ " alt=" + "Facebook Hidroluz" + " width=" + 24 + " height=" + 24 + " border=" + 0 + " /></a></td>");
		texto.append(" <td style=" + "width: 122.5px;" + " height=" + 50 + ">");
		texto.append("<td style=" + "width: 51px;" + ">&nbsp;</td>");
		texto.append("</tr></tbody></table>");
		texto.append("</td></tr>");
		texto.append("</td></tr>");
		texto.append("</tbody></table></table>");

		m.setMensagem(texto.toString());

		try {

			String msg = new EmailUtil().enviarEmail(m);

			request.setAttribute("msg_cad02",
					"Foi enviado para o seu email um link para confirmação de cadastro. Não recebeu ??? ");

			request.getRequestDispatcher("inicio.jsp").forward(request, response);
		} catch (Exception e) {
			
			request.setAttribute("msg",
					"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

			request.getRequestDispatcher("inicio.jsp").forward(request, response);

			e.printStackTrace();

		}

	}

	public void EnviarEmailUsu(String nome, String email, Integer cli, String bloco, String unidade) {

		Mensagem m = new Mensagem();
		m.setPara(email);
		m.setAssunto("Cadastro no Portal Hidroluz");
		StringBuffer texto = new StringBuffer();

		String logo = "https://uploaddeimagens.com.br/images/001/089/101/full/LogoOriginal.png";
		String clique = "https://uploaddeimagens.com.br/images/001/089/105/thumb/clique_aqui.png";

		texto.append("<table border=" + 0 + " width=" + 600 + " cellspacing=" + 0 + " cellpadding=" + 0 + " align="
				+ "center" + ">");
		texto.append("<tbody>");
		texto.append("<tr bgcolor=" + "#E9EBEC" + " height=" + 80 + ">");
		texto.append("<td align=" + "center" + "><img src=" + logo + " alt=" + "Logo_Original" + " border=" + 0
				+ " width=" + 132 + " height=" + 48 + ">&nbsp;</td>");
		texto.append("</tr>");
		texto.append("</tbody>");
		texto.append("<tr>");

		texto.append("<table border=" + 0 + " width=" + 600 + " cellspacing=" + 0 + " + align=" + "center" + ">");
		texto.append("<tbody>");
		texto.append("<tr>");

		texto.append("<td align=" + "center" + " height=" + 60 + "width=" + 600 + ">");

		texto.append("<h4>Olá, " + nome.trim() + "!</h4>");
		texto.append("<h4"
				+ ">Obrigado por realizar seu cadastro. Para confirmar seu e-mail e ter acesso as suas informações de consumo de água, clique no botão abaixo:.</h4>");

		texto.append("<h4 style=" + "text-align: center;" + ">&nbsp;<a href="
				+ "http://www.hidroluzportal.com.br/Controle?cmd=primeiroacesso&cod_cli=" + cli + "&bloco=" + bloco
				+ "&unidade=" + unidade + "&email=" + email.toUpperCase().trim() + ">" + "<img src=" + clique
				+ " width=" + 60 + " height=" + 25 + "/><a/>&nbsp;</h3>");

		texto.append("<h4 style=" + "text-align: center;"
				+ ">Se tiver problemas com o botão, copie e cole o link abaixo em uma nova janela de seu navegador:</h4>");

		texto.append("<h4 style=" + "text-align: center;"
				+ ">http://www.hidroluzportal.com.br/Controle?cmd=primeiroacesso&cod_cli=" + cli + "&bloco=" + bloco
				+ "&unidade=" + unidade + "&email=" + email.toUpperCase().trim() + "&nbsp;</h4>");

		texto.append("<h4 style=" + "text-align: center;"
				+ ">Lembramos que cada usuário pode acompanhar seu histórico de consumo pelo Portal Medição ou App.</h4>");

		texto.append("</td></tr><tr><td>");

		texto.append("<table border=" + 0 + " width=" + 600 + " cellspacing=" + 0 + " cellpadding=" + 0 + " align="
				+ "center" + ">");
		texto.append("<tbody><tr bgcolor=" + "#E9EBEC" + " height=" + 80 + ">");
		texto.append("<td style=" + "width: 435px;" + "height=" + 80 + ">");
		texto.append("<h4>&nbsp;Conheça a Hidroluz nas Redes Sociais:</h4>");
		texto.append("</td>");
		texto.append("<td style=" + "width: 77.5px;" + " align=" + "center" + " valign=" + "middle " + " height=" + 50
				+ "><a title=" + "Facebook Hidroluz" + " href=" + "https://www.facebook.com/grupohidroluz/" + "rel="
				+ "noopener" + " data-saferedirecturl="
				+ "https://www.google.com/url?hl=pt-BR&amp;q=https://www.facebook.com/bancointeroficial&amp;source=gmail&amp;ust=1505310262242000&amp;usg=AFQjCNHwA7jbIcJdJJzccSCvsARnMtrF9g"
				+ "><img class=" + "CToWUd" + " src="
				+ "https://ci3.googleusercontent.com/proxy/dhmsFGOPjq7FXnyJaV73yY0xVxFpEP0iXfGDehXkMVzJvz0BA7c_mx4FBEqgae_SjZMdgjC72FujU9PhytaXIiEqdApZxf7MKJFs5I09KNm3O4Mu5Dn0boSui5Qs405am1EfJ6h8lXIZNeuKI2xNs3PwRHffqWs=s0-d-e1-ft#http://marketing.bancointer.com.br/comunicacao/e-mail-mkt-institucional/imagens/icon-facebook.png"
				+ " alt=" + "Facebook Hidroluz" + " width=" + 24 + " height=" + 24 + " border=" + 0 + " /></a></td>");
		texto.append(" <td style=" + "width: 122.5px;" + " height=" + 50 + ">");
		texto.append("<td style=" + "width: 51px;" + ">&nbsp;</td>");
		texto.append("</tr></tbody></table>");
		texto.append("</td></tr>");
		texto.append("</td></tr>");
		texto.append("</tbody></table></table>");

		m.setMensagem(texto.toString());

		System.out.println(texto.toString());

		try {

			String msg = new EmailUtil().enviarEmail(m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void EnviarEmailADM(Clientes cli, String nome, String email, String bloco, String unidade) {

		for (Cont_cli c : cli.getContatos()) {

			int i = 0;

			try {

				// Uso o Template da classe mensagem
				Mensagem m = new Mensagem();
				m.setPara(c.getEmail());
				m.setAssunto("Cadastro no Portal Hidroluz - " + cli.getNomfant_apel());

				StringBuffer texto = new StringBuffer();

				String center = "center";
				String zero = "0";
				String seis = "600";
				String logo = "https://uploaddeimagens.com.br/images/001/089/101/full/LogoOriginal.png";

				texto.append("<table border=" + 0 + " width=" + 600 + " cellspacing=" + 0 + " cellpadding=" + 0
						+ " align=" + "center" + ">");
				texto.append("<tbody>");
				texto.append("<tr bgcolor=" + "#E9EBEC" + " height=" + 80 + ">");
				texto.append("<td align=" + "center" + "><img src=" + logo + " alt=" + "Logo_Original" + " border=" + 0
						+ " width=" + 132 + " height=" + 48 + ">&nbsp;</td>");
				texto.append("</tr>");
				texto.append("</tbody>");
				texto.append("<tr>");

				texto.append(
						"<table border=" + 0 + " width=" + 600 + " cellspacing=" + 0 + " + align=" + "center" + ">");
				texto.append("<tbody>");
				texto.append("<tr>");

				texto.append("<td align=" + "left" + " height=" + 60 + "width=" + 600 + ">");

				texto.append("<h4 style=" + center + ">Olá, " + cli.getContatos().get(i).getNome() + "!;</h4>");
				texto.append("<h4 style=" + center + ">Informamos que o Morador:.</h4>");
				texto.append("<h4 style=" + center + ">Nome: " + nome.trim() + "</h4>");
				texto.append("<h4 style=" + center + ">Que tem como contato o seguinte e-mail:</h4>");
				texto.append("<h4 style=" + center + ">Email: " + email.trim().toLowerCase() + "</h4>");
				texto.append("<h4 style=" + center + ">Realizou o cadastro na seguinte Unidade:</h4>");
				texto.append("<h4 style=" + center + ">Unidade: " + unidade + " do Bloco " + bloco + "</h4>");
				texto.append("<h4 style=" + center
						+ ">Caso você não reconheça esta solicitação de alteração de dados cadastrais, pedimos que contate nossa equipe de suporte via um dos canais de atendimento.</h4>");
				texto.append("<h4>E-mail: <a href=" + "mailto:medicao@hidroluz.com.br"
						+ ">medicao@hidroluz.com.br</a></h4>");
				texto.append("<h4>Telefone: (21) 2199-9999</h4>");
				texto.append("</td></tr><tr><td>");

				texto.append("<table border=" + 0 + " width=" + 600 + " cellspacing=" + 0 + " cellpadding=" + 0
						+ " align=" + "center" + ">");
				texto.append("<tbody><tr bgcolor=" + "#E9EBEC" + " height=" + 80 + ">");
				texto.append("<td style=" + "width: 435px;" + "height=" + 80 + ">");
				texto.append("<h4>&nbsp;Conheça a Hidroluz nas Redes Sociais:</h4>");
				texto.append("</td>");
				texto.append("<td style=" + "width: 77.5px;" + " align=" + "center" + " valign=" + "middle "
						+ " height=" + 50 + "><a title=" + "Facebook Hidroluz" + " href="
						+ "https://www.facebook.com/grupohidroluz/" + "rel=" + "noopener" + " data-saferedirecturl="
						+ "https://www.google.com/url?hl=pt-BR&amp;q=https://www.facebook.com/bancointeroficial&amp;source=gmail&amp;ust=1505310262242000&amp;usg=AFQjCNHwA7jbIcJdJJzccSCvsARnMtrF9g"
						+ "><img class=" + "CToWUd" + " src="
						+ "https://ci3.googleusercontent.com/proxy/dhmsFGOPjq7FXnyJaV73yY0xVxFpEP0iXfGDehXkMVzJvz0BA7c_mx4FBEqgae_SjZMdgjC72FujU9PhytaXIiEqdApZxf7MKJFs5I09KNm3O4Mu5Dn0boSui5Qs405am1EfJ6h8lXIZNeuKI2xNs3PwRHffqWs=s0-d-e1-ft#http://marketing.bancointer.com.br/comunicacao/e-mail-mkt-institucional/imagens/icon-facebook.png"
						+ " alt=" + "Facebook Hidroluz" + " width=" + 24 + " height=" + 24 + " border=" + 0
						+ " /></a></td>");
				texto.append(" <td style=" + "width: 122.5px;" + " height=" + 50 + ">");
				texto.append("<td style=" + "width: 51px;" + ">&nbsp;</td>");
				texto.append("</tr></tbody></table>");
				texto.append("</td></tr>");
				texto.append("</td></tr>");
				texto.append("</tbody></table></table>");

				m.setMensagem(texto.toString());

				// Enviando o email
				String msg = new EmailUtil().enviarEmail(m);

			} catch (Exception e) {
				// TODO: handle exception
			}

			i++;

		}

	}

	protected void limpar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("oiiii");
		Integer cod = new Integer(request.getParameter("id_unidade"));

		try {

			new UnidadesDao().limpar_dados(cod);
			request.setAttribute("msg", "Dados Excluidos");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			List<Unidades> lista;
			try {
				String nome = (String) request.getSession().getAttribute("NOME");
				String ende = (String) request.getSession().getAttribute("ENDERECO");
				Integer cod_cli_adm = (Integer) request.getSession().getAttribute("COD_CLI_ADM");
				
				lista = new UnidadesDao().findAll(cod_cli_adm);
				request.setAttribute("lista", lista);
				request.setAttribute("cond", nome);
				request.setAttribute("ende", ende);
				request.getRequestDispatcher("consulta_unidades.jsp").forward(request, response);

			} catch (Exception e) {

				request.setAttribute("msg",
						"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

				request.getRequestDispatcher("inicio.jsp").forward(request, response);

				e.printStackTrace();

			}

		}

	}

	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer id_unidade = (Integer) request.getSession().getAttribute("ID_UNUDADE");
		
		String nome = new String(request.getParameter("nome"));
		String email = new String(request.getParameter("email"));
		String telefone = new String(request.getParameter("tel"));
		String login = new String(request.getParameter("login"));
		String senha = new String(request.getParameter("novasenha"));
		String senha_atual = new String(request.getParameter("senhaatual"));

		SimpleMD5 md5 = new SimpleMD5(senha_atual, "");
		senha_atual = md5.toHexString().toUpperCase();

		System.out.println(senha_atual);
		System.out.println(senha);

		if (senha.equalsIgnoreCase(senha_atual)) {

			UnidadesDao ud = new UnidadesDao();

			try {
				ud.alterar(id_unidade, nome, email, telefone, login, senha);

				request.setAttribute("msg", "Dados Alterados com Sucesso");

				RequestDispatcher dispatcher = request.getRequestDispatcher("principal.jsp");
				dispatcher.forward(request, response);

			} catch (Exception e) {
				
				request.setAttribute("msg",
						"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

				request.getRequestDispatcher("inicio.jsp").forward(request, response);

				e.printStackTrace();

			}

		}

		request.setAttribute("msg", "Senha Atual incorreta");

		alterar(request, response);

	}

	protected String logar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = new String(request.getParameter("login"));
		String senha = new String(request.getParameter("senha"));
		String email = (String) request.getSession().getAttribute("EMAIL");
		String path = (String) request.getSession().getAttribute("PATH");
		

		try

		{// TENTA FAZER LOGIN PELAS UNIDADES

			Usuarios resp = new UsuariosDao().logar(login, senha);

			if (resp != null) {
				
				//grava log de acesso dos usuários
				Log_acesso_site l = new Log_acesso_site(false, resp.getNome(), resp.getEmail());
				new Log_acesso_siteDao().create(l);
				
				email = resp.getEmail();
				//telefone_global = resp.getTelefone().trim();
			
				request.getSession().setAttribute("EMAIL_USU", resp.getEmail().toUpperCase());
				request.getSession().setAttribute("ORIGEM", "ADD");
				request.getSession().setAttribute("PICTURE", "img/user.png");
				request.getSession().setAttribute("NOME", resp.getNome());
				

				// SE ACHAR ENVIA PARA A TELA DE CLIENTESS

				List<Unidades> u = new UnidadesDao().findAll_Unidades_Cli(resp.getId_usuario());

				request.getSession().setAttribute("ID_USUARIO", resp.getId_usuario());

				if (u.size() > 1 || u == null || u.isEmpty()) {

					request.setAttribute("nome", resp.getNome());
					request.setAttribute("picture", "img/user.png");

					request.setAttribute("unidades", u);
					
					request.getRequestDispatcher("escolha_unidades.jsp").forward(request, response);

				} else {

					List<Registros_leitura_medicao> reg = new Registros_leitura_medicaoDao()
							.findAll(u.get(0).getCliente().getCodigo(), u.get(0).getBloco(), u.get(0).getUnidade());

					List<Registros_leitura_medicao> reg2 = new Registros_leitura_medicaoDao().findDetalhes(
							u.get(0).getCliente().getCodigo(), u.get(0).getBloco(), u.get(0).getUnidade());

					// Writer writer = new FileWriter("c:\\Output.json");

					// Gson gson = new GsonBuilder().create();
					// gson.toJson(reg, writer);
					// gson.toJson(123, writer);

					// writer.close();

					Integer fim = reg.size();

					if (fim > 3) {

						List<Registros_leitura_medicao> part1 = reg.subList(0, 3);

						List<Registros_leitura_medicao> part2 = reg.subList(3, fim);

						request.setAttribute("lista", part1);
						request.setAttribute("lista2", part2);

					} else {

						List<Registros_leitura_medicao> part1 = reg;
						request.setAttribute("lista", part1);
						request.setAttribute("lista2", null);

					}

					Date dt = reg2.get(0).getData_da_leitura();

					int cont = 0;

					List<Registros_leitura_medicao> reg4 = new ArrayList<Registros_leitura_medicao>();

					for (int i = 0; i < reg2.size(); i++) {

						if (i == reg2.size() - 1) {

							cont++;
							reg4 = new ArrayList<Registros_leitura_medicao>();
							reg4.add(reg2.get(i));
							String lista2 = "listaDetalhes" + cont;
							request.setAttribute(lista2, reg4);
							i--;

							cont--;
							reg4 = new ArrayList<Registros_leitura_medicao>();
							reg4.add(reg2.get(i));
							lista2 = "listaDetalhes" + cont;
							request.setAttribute(lista2, reg4);

							i++;

						}

						if (reg2.get(i).getData_da_leitura().equals(dt)) {
							reg4.add(reg2.get(i));

						}

						if (!dt.equals(reg2.get(i).getData_da_leitura())) {

							dt = reg2.get(i).getData_da_leitura();

							String lista = "listaDetalhes" + cont;
							request.setAttribute(lista, reg4);
							reg4 = new ArrayList<Registros_leitura_medicao>();
							cont++;
							i--;
						}

					}

					ServletContext context = request.getServletContext();
					path = context.getRealPath("/");
					
					String cond = u.get(0).getCliente().getNomfant_apel().trim();
					Integer cod_cli = u.get(0).getCliente().getCodigo();
					String unidade = u.get(0).getUnidade().trim();
					String bloco = u.get(0).getBloco().trim();
					

					// Cria arquivo
					file = new File(path + "Output" + unidade + bloco+ ".json");

					// Se o arquivo nao existir, ele gera
					if (!file.exists()) {
						file.createNewFile();
					}

					Writer writer = new FileWriter(file);

					Gson gson = new GsonBuilder().create();

					List<Registros_leitura_medicao> reg3 = new Registros_leitura_medicaoDao().findAll(cod_cli, bloco,
							unidade);

					Collections.reverse(reg3);

					gson.toJson(reg3, writer);

					writer.close();
					
					
					
				

					// Integer fimDet = reg2.size();

					request.setAttribute("nome", resp.getNome());
					request.setAttribute("picture", "img/user.png");

					request.setAttribute("cond", cond);
					request.setAttribute("ende", u.get(0).getCliente().getEndereco());
					request.setAttribute("unidade", unidade);
					request.setAttribute("bloco", bloco);
					
					request.getSession().setAttribute("CONDO", cond);
					request.getSession().setAttribute("BLOCO", bloco);
					request.getSession().setAttribute("UNIDADE", unidade);
					request.getSession().setAttribute("COD_CLI", cod_cli);


					request.getRequestDispatcher("principal.jsp").forward(request, response);

				}

			} else {

				// SENÃO VERIFICA SE É UM USUARIO DA ADMINISTRACAO
				Administracao_site respadm = null;

				try {

					respadm = new Administracao_siteDao().logar(login, senha);

				} catch (Exception e) {
					request.setAttribute("msg",
							"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

					request.getRequestDispatcher("inicio.jsp").forward(request, response);
				}

				if (respadm != null) {
					
					Log_acesso_site l = new Log_acesso_site(true, respadm.getLogin());
					new Log_acesso_siteDao().create(l);
					
					String ende = respadm.getCliente().getEndereco().trim() + ", "
							+ respadm.getCliente().getBairro().trim() + ", " + respadm.getCliente().getCidade();
					Integer cod_cli_adm = respadm.getCliente().getCodigo();
					List<Unidades> lista = (List<Unidades>) request.getSession().getAttribute("LISTA");
					
					request.getSession().setAttribute("NOME", respadm.getCliente().getNomfant_apel());
					
					request.setAttribute("cond", respadm.getCliente().getNomfant_apel());
					request.setAttribute("ende", ende);

					lista = new UnidadesDao().findAll(cod_cli_adm);
					request.setAttribute("lista", lista);
					request.getSession().setAttribute("COD_CLI_ADM", cod_cli_adm);
					request.getSession().setAttribute("ENDERECO", ende);
					request.getSession().setAttribute("LISTA", lista);
					
					request.getRequestDispatcher("consulta_unidades.jsp").forward(request, response);

				} else {

					// SENÃO ACHAR NAS DUAS TABELAS LIMPA OS CAMPOS E
					// INFORMA

					request.setAttribute("msg", "Usuário ou senha inválidos");

					request.getRequestDispatcher("inicio.jsp").forward(request, response);

					login = "";
					senha = "";

				}

			}

		} catch (Exception e) {

			request.setAttribute("msg",
					"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

			request.getRequestDispatcher("inicio.jsp").forward(request, response);

			e.printStackTrace();


		} finally {

		}
		return "";

	}

	protected void buscar_cliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String codigo = new String(request.getParameter("codigo"));
		
		Integer cod_cli ;

		if (codigo.length() < 7) {

			cod_cli = Integer.parseInt(codigo);

		} else {
			cod_cli = 00000;
		}

		try {

			Clientes cliente = new ClienteDao().findByCode(cod_cli);

			if (cliente != null) {
				
				request.getSession().setAttribute("COD_CLI", cod_cli);

				List<Integer> blocos = new UnidadesDao().FindBlocos(cod_cli);
				List<Integer> unidades = new UnidadesDao().FindUnidades(cod_cli);

				request.setAttribute("nome", cliente.getNomfant_apel());
				request.setAttribute("endereco", cliente.getEndereco().trim() + ", " + cliente.getBairro().trim() + ", "
						+ cliente.getCidade().trim() + ".");
				request.setAttribute("blocos", blocos);
				request.setAttribute("unidades", unidades);

				request.getRequestDispatcher("cadastro_unidades2.jsp").forward(request, response);

			} else {

				List<Clientes> cli = new ClienteDao().findCNPJ(codigo);

				if (!cli.isEmpty()) {
					
					request.getSession().setAttribute("COD_CLI", cli.get(0).getCodigo());

					List<Integer> blocos = new UnidadesDao().FindBlocos(cli.get(0).getCodigo());
					List<Integer> unidades = new UnidadesDao().FindUnidades(cli.get(0).getCodigo());

					request.setAttribute("nome", cli.get(0).getNomfant_apel());
					request.setAttribute("endereco", cli.get(0).getEndereco() + "-" + cli.get(0).getBairro() + "-"
							+ cli.get(0).getCidade() + ".");
					request.setAttribute("blocos", blocos);
					request.setAttribute("unidades", unidades);

					request.getRequestDispatcher("cadastro_unidades2.jsp").forward(request, response);

				} else {

					request.setAttribute("msg", "Esse codígo de cliente ou CNPJ não existe no nosso cadastro.");

					request.getRequestDispatcher("busca_unidade.jsp").forward(request, response);

				}

			}

		} catch (Exception e) {
			
			request.setAttribute("msg",
					"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

			request.getRequestDispatcher("inicio.jsp").forward(request, response);

			e.printStackTrace();

		}

	}

	protected void cadastrarSocial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bloco = new String(request.getParameter("bloco"));
		String unidade = new String(request.getParameter("unidade"));
		UnidadesDao ud = new UnidadesDao();
		Integer cod_cli = (Integer) request.getSession().getAttribute("COD_CLI");
		String path = (String) request.getSession().getAttribute("PATH");
				

		try {
			Clientes cliente = new ClienteDao().findByCode(cod_cli);
			List<Unidades> u = new UnidadesDao().findByCodeUni(cod_cli, unidade, bloco);

			if (u == null || u.isEmpty()) {

				request.setAttribute("msg", "Unidade não encontra, confira se o bloco corresponde com a unidade");

				List<Integer> blocos1 = new UnidadesDao().FindBlocos(cod_cli);
				List<Integer> unidades1 = new UnidadesDao().FindUnidades(cod_cli);

				request.setAttribute("nome", cliente.getNomfant_apel());
				request.setAttribute("endereco",
						cliente.getEndereco() + "-" + cliente.getBairro() + "-" + cliente.getCidade() + ".");
				request.setAttribute("blocos", blocos1);
				request.setAttribute("unidades", unidades1);

				request.getRequestDispatcher("cadastro_unidades2.jsp").forward(request, response);

			} else {

				if (u.get(0).getAcesso_cadastrado() == true) {

					request.setAttribute("msg", "Unidade já cadastrada");

					List<Integer> blocos = new UnidadesDao().FindBlocos(cod_cli);
					List<Integer> unidades = new UnidadesDao().FindUnidades(cod_cli);

					if (cliente != null) {

						request.setAttribute("nome", cliente.getNomfant_apel());
						request.setAttribute("endereco",
								cliente.getEndereco() + "-" + cliente.getBairro() + "-" + cliente.getCidade() + ".");
						request.setAttribute("blocos", blocos);
						request.setAttribute("unidades", unidades);

						request.getRequestDispatcher("cadastro_unidades2.jsp").forward(request, response);
						// TODO: handle exception
					}

				} else {

					String email_usu = (String) request.getSession().getAttribute("EMAIL_USU");

					String picture_usu = (String) request.getSession().getAttribute("PICTURE");
					String nome_usu = (String) request.getSession().getAttribute("NOME");
					String origem = (String) request.getSession().getAttribute("ORIGEM");

					List<Usuarios> usua = new UsuariosDao().findEmail(email_usu.toUpperCase());

					EnviarEmailADM(cliente, usua.get(0).getNome(), usua.get(0).getEmail(), bloco, unidade);

					if (origem.equals("EMAIL")) {

						ud.update_email(cod_cli, bloco, unidade, usua.get(0).getId_usuario());

						request.getSession().setAttribute("BLOCO_USU", bloco);
						request.getSession().setAttribute("UNIDADE_USU", unidade);
						request.getSession().setAttribute("NOME_USU", usua.get(0).getNome());

						EnviarEmailUsu(usua.get(0).getNome(), usua.get(0).getEmail(), cod_cli, bloco, unidade);

						request.setAttribute("msg_cad02",
								"Foi Reenviado para o seu email um link para confirmação de cadastro. Não recebeu ???");

						request.getRequestDispatcher("inicio.jsp").forward(request, response);

					} else {

						ud.update_social(cod_cli, bloco, unidade, usua.get(0).getId_usuario());

						List<Registros_leitura_medicao> reg = new Registros_leitura_medicaoDao().findAll(cod_cli, bloco,
								unidade);

						List<Registros_leitura_medicao> reg2 = new Registros_leitura_medicaoDao().findDetalhes(cod_cli,
								bloco, unidade);

						ServletContext context = request.getServletContext();
						path = context.getRealPath("/");

						// Cria arquivo
						file = new File(path + "Output" + unidade + bloco + ".json");

						// Se o arquivo nao existir, ele gera
						if (!file.exists()) {
							file.createNewFile();
						}

						Writer writer = new FileWriter(file);

						Gson gson = new GsonBuilder().create();

						List<Registros_leitura_medicao> reg3 = new Registros_leitura_medicaoDao().findAll(cod_cli,
								bloco, unidade);

						Collections.reverse(reg3);

						gson.toJson(reg3, writer);

						writer.close();

						Integer fim = reg.size();

						if (fim > 3) {

							List<Registros_leitura_medicao> part1 = reg.subList(0, 3);

							List<Registros_leitura_medicao> part2 = reg.subList(3, fim);

							request.setAttribute("lista", part1);
							request.setAttribute("lista2", part2);

						} else {

							List<Registros_leitura_medicao> part1 = reg;
							request.setAttribute("lista", part1);
							request.setAttribute("lista2", null);

						}

						Date dt = reg2.get(0).getData_da_leitura();

						int cont = 0;

						List<Registros_leitura_medicao> reg4 = new ArrayList<Registros_leitura_medicao>();

						for (int i = 0; i < reg2.size(); i++) {

							if (i == reg2.size() - 1) {

								cont++;
								reg4 = new ArrayList<Registros_leitura_medicao>();
								reg4.add(reg2.get(i));
								String lista2 = "listaDetalhes" + cont;
								request.setAttribute(lista2, reg4);
								i--;

								cont--;
								reg4 = new ArrayList<Registros_leitura_medicao>();
								reg4.add(reg2.get(i));
								lista2 = "listaDetalhes" + cont;
								request.setAttribute(lista2, reg4);

								i++;

							}

							if (reg2.get(i).getData_da_leitura().equals(dt)) {
								reg4.add(reg2.get(i));

							}

							if (!dt.equals(reg2.get(i).getData_da_leitura())) {

								dt = reg2.get(i).getData_da_leitura();

								String lista = "listaDetalhes" + cont;
								request.setAttribute(lista, reg4);
								reg4 = new ArrayList<Registros_leitura_medicao>();
								cont++;
								i--;
							}

						}

						request.setAttribute("nome", nome_usu);

						if (picture_usu == null) {
							request.setAttribute("picture", "img/user.png");

						} else {
							request.setAttribute("picture", picture_usu);
						}
						

						request.setAttribute("cond", u.get(0).getCliente().getNomfant_apel());
						request.setAttribute("ende", u.get(0).getCliente().getEndereco());
						request.setAttribute("unidade", unidade);
						request.setAttribute("bloco", bloco);

						request.setAttribute("msg_cad01", "Usuário cadastrado com sucesso");

						request.getRequestDispatcher("principal.jsp").forward(request, response);

					}

				}
			}
		} catch (Exception e) {
			
			request.setAttribute("msg",
					"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

			request.getRequestDispatcher("inicio.jsp").forward(request, response);

			e.printStackTrace();

		}

	}

	protected void alterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nome = (String) request.getSession().getAttribute("NOME");
		String telefone = (String) request.getSession().getAttribute("TELEFONE");
		String email = (String) request.getSession().getAttribute("EMAIL_USU");
		String login = (String) request.getSession().getAttribute("LOGIN");
		
		request.setAttribute("nome", nome);
		request.setAttribute("telefone", telefone);
		request.setAttribute("login", login);
		request.setAttribute("email", email);

		try {
			request.getRequestDispatcher("altera_cadastro2.jsp").forward(request, response);

		} catch (Exception e) {

			request.setAttribute("msg",
					"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

			request.getRequestDispatcher("inicio.jsp").forward(request, response);

			e.printStackTrace();

		}

	}

	protected void extrato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String caminhoRelatorio = "";

			HttpSession session2 = request.getSession();

			// Se possui conta

			caminhoRelatorio = session2.getServletContext().getRealPath("/ExtratoAgua2.jasper");
			// Senão possui conta

			// Enviando as imagens para o Relátorio
			String caminhoImagem01 = session2.getServletContext().getRealPath("/Logo_Negativa.png");
			String caminhoImagem02 = session2.getServletContext().getRealPath("/seta.png");
			Integer cod_cli_adm =  (Integer) request.getSession().getAttribute("COD_CLI_ADM");

			InputStream is1 = new FileInputStream(caminhoRelatorio);

			// Crio um map para passar os parametros para o relatorio
			Map<String, Object> map = new HashMap<String, Object>();

			// Passando os Paramentros.

			map.put("cod_cad01", cod_cli_adm);

			map.put("IMAGEM01", caminhoImagem01);
			map.put("IMAGEM02", caminhoImagem02);

			byte[] pdf;

			// Gero o Relatorio
			pdf = JasperRunManager.runReportToPdf(is1, map,
					HibernateUtil.getSessionFactory().openSession().connection());

			ServletOutputStream out = response.getOutputStream();

			// Mostro na tela.
			out.write(pdf);
			out.flush();
			out.close();

			// Finalizar o ciclo de vida do jsf (nao voltar para o .xhtml)

		} catch (Exception e) {
			
			request.setAttribute("msg",
					"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");

			request.getRequestDispatcher("inicio.jsp").forward(request, response);

			e.printStackTrace();

		}

	}

	public static void erroConexao() {

		req.setAttribute("msg",
				"Ocorreu um erro interno no sistema, favor tentar novamente, caso o problema permaneça entre em contato com a Hidroluz");
		try {
	
		req.getRequestDispatcher("inicio.jsp").forward(req, re);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

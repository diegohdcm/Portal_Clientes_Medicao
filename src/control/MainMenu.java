package control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entity.Registros_leitura_medicao;
import entity.Unidades;
import entity.Usuarios;
import persistence.Registros_leitura_medicaoDao;
import persistence.UnidadesDao;
import persistence.UsuariosDao;

public class MainMenu extends HttpServlet {

	private String path;
	private List<Unidades> lista;
	private File file;

	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String nome = new String(req.getParameter("user_name"));

		String email = new String(req.getParameter("user_email"));

		String picture = new String(req.getParameter("user_picture"));

		String origem = new String(req.getParameter("user_origem"));

		String id = new String(req.getParameter("user_id"));

		if ((req.getParameter("oe")) != null) {
			String oe = new String(req.getParameter("oe"));
			picture = picture + "&oe=" + oe;
		}
		
		if (filter_email(email)){
		

		try {
			
			List<Usuarios> u = new UsuariosDao().findEmail(email.toUpperCase());

			if (u == null || u.isEmpty()) {

				Usuarios usu = new Usuarios();
				usu.setId_usuario(null);
				usu.setNome(nome);
				usu.setFoto(picture);
				usu.setId_social(id);
				usu.setOrigem(origem);
				usu.setEmail(email.toUpperCase());

				new UsuariosDao().gravar(usu);

				req.getSession().setAttribute("EMAIL_USU", email);
				req.getSession().setAttribute("PICTURE", picture);
				req.getSession().setAttribute("NOME", nome);
				req.getSession().setAttribute("ORIGEM", origem);

				res.sendRedirect("busca_unidade.jsp");

			} else {

				try {
					Integer id_usu =  u.get(0).getId_usuario();
					List<Unidades> uni = new UnidadesDao().findAll_Unidades_Cli(id_usu);
					req.getSession().setAttribute("ID_USUARIO", id_usu);
					
					if (uni.size() > 1 || uni == null || u.isEmpty()) {

						req.setAttribute("nome", nome);

						req.setAttribute("email", email);

						req.setAttribute("picture", picture);

						req.setAttribute("unidades", uni);
						
						req.getRequestDispatcher("escolha_unidades.jsp").forward(req, res);

					} else {

						List<Registros_leitura_medicao> reg = new Registros_leitura_medicaoDao().findAll(
								uni.get(0).getCliente().getCodigo(), uni.get(0).getBloco(), uni.get(0).getUnidade());

						List<Registros_leitura_medicao> reg2 = new Registros_leitura_medicaoDao().findDetalhes(
								uni.get(0).getCliente().getCodigo(), uni.get(0).getBloco(), uni.get(0).getUnidade());

						Integer fim = reg.size();

						if (fim > 3) {

							List<Registros_leitura_medicao> part1 = reg.subList(0, 3);

							List<Registros_leitura_medicao> part2 = reg.subList(3, fim);

							req.setAttribute("lista", part1);
							req.setAttribute("lista2", part2);

						} else {

							List<Registros_leitura_medicao> part1 = reg;
							req.setAttribute("lista", part1);
							req.setAttribute("lista2", null);

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
								req.setAttribute(lista, reg4);
								reg4 = new ArrayList<Registros_leitura_medicao>();
								cont++;
								i--;
							}

						}

						ServletContext context = req.getServletContext();
						path = context.getRealPath("/");
						
						String cond = uni.get(0).getCliente().getNomfant_apel().trim();
						Integer cod_cli = uni.get(0).getCliente().getCodigo();
						String unidade = uni.get(0).getUnidade().trim();
						String bloco = uni.get(0).getBloco().trim();

						// Cria arquivo
						file = new File(path + "Output" + unidade + bloco + ".json");

						// Se o arquivo nao existir, ele gera
						if (!file.exists()) {
							file.createNewFile();
						}

						Writer writer = new FileWriter(file);

						Gson gson = new GsonBuilder().create();

						List<Registros_leitura_medicao> reg3 = new Registros_leitura_medicaoDao().findAll(
								cod_cli, bloco, unidade);

						Collections.reverse(reg3);

						gson.toJson(reg3, writer);

						writer.close();

						req.setAttribute("nome", nome);
						req.setAttribute("picture", picture);

						req.setAttribute("cond", cond);
						req.setAttribute("ende", uni.get(0).getCliente().getEndereco());
						req.setAttribute("unidade", unidade);
						req.setAttribute("bloco", bloco);
						
					
						req.getSession().setAttribute("PICTURE", picture);
						req.getSession().setAttribute("NOME", nome);
						req.getSession().setAttribute("EMAIL_USU", email);
						
						req.getSession().setAttribute("CONDO", cond);
						req.getSession().setAttribute("BLOCO", bloco);
						req.getSession().setAttribute("UNIDADE", unidade);
						req.getSession().setAttribute("COD_CLI", cod_cli);

						req.getRequestDispatcher("principal.jsp").forward(req, res);

					}

				} catch (HibernateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		}else{
			req.setAttribute("msg", "Ocorreu um erro na validação do seu e-mail, tente novamente.");
			req.getRequestDispatcher("inicio.jsp").forward(req, res);
		}

	}
	
	public static Boolean validaEmail(String u) {
        return Boolean.valueOf(Pattern.compile(".+@.+\\.[a-z]+").matcher(u).matches());
    }
	
	public static boolean filter_email(String edt) {
        if (validaEmail(edt).booleanValue()) {
            return true;
        }
        
        return false;
    }


}
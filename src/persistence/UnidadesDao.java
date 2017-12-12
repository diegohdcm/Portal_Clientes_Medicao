package persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.jcommon.encryption.SimpleMD5;

import config.HibernateUtil;
import entity.Clientes;
import entity.Registros_leitura_medicao;
import entity.Unidades;

public class UnidadesDao {

	Session session;
	Transaction transaction;
	Query query;
	Criteria criteria;

	public Unidades logar(String login, String senha) throws Exception {

		Unidades user = null;

		try {
			SimpleMD5 md5 = new SimpleMD5(senha, "");
			senha = md5.toHexString().toUpperCase();
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(Unidades.class);
			// select * from usuario
			criteria.add(Restrictions.eq("login", login.toUpperCase()));
			// where email = u.getEmail()
			criteria.add(Restrictions.eq("senha", senha.toUpperCase()));
			// and senha = u.getSenha()
			user = (Unidades) criteria.uniqueResult();
			// user recebe o resultado da busca feita no banco
			// user pode estar cheio(dados corretos) ou vazio(daados
			// Invalidos;;)
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
		return user;
	}

	public void update(Integer cod_cli, String bloco, String unidade, String nome, String email, String telefone)
			throws Exception {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			query = session.createQuery("update Unidades as u set u.nome  = '" + nome + "', u.email = '" + email
					+ "', u.telefone= '" + telefone + "' where u.cliente.codigo = " + cod_cli + " and u.bloco = '"
					+ bloco + "' and u.unidade = '" + unidade + "'");
			query.executeUpdate();
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
			HibernateUtil.fechar_conexao(session);

		}
	}

	public void acesso(Integer cod_cli, String bloco, String unidade) throws Exception {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			query = session
					.createQuery("update Unidades as u set u.acesso_cadastrado = true" + " where u.cliente.codigo = "
							+ cod_cli + " and u.bloco = '" + bloco + "' and u.unidade = '" + unidade + "'");
			query.executeUpdate();
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
	}

	public void update_social(Integer cod_cli, String bloco, String unidade, Integer id_usuario) throws Exception {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			query = session
					.createQuery("update Unidades as u set u.acesso_cadastrado = true,  u.usuario.id_usuario  = '"
							+ id_usuario + "' where u.cliente.codigo = " + cod_cli + " and u.bloco = '" + bloco
							+ "' and u.unidade = '" + unidade + "'");
			query.executeUpdate();
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
	}

	public void update_email(Integer cod_cli, String bloco, String unidade, Integer id_usuario) throws Exception {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			query = session.createQuery(
					"update Unidades as u set u.usuario.id_usuario  = '" + id_usuario + "' where u.cliente.codigo = "
							+ cod_cli + " and u.bloco = '" + bloco + "' and u.unidade = '" + unidade + "'");
			query.executeUpdate();
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
	}

	public void alterar(Integer id_unidade, String nome, String email, String telefone, String login, String senha)
			throws Exception {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			SimpleMD5 md5 = new SimpleMD5(senha, "");
			senha = md5.toHexString().toUpperCase();
			login = login.toUpperCase();
			query = session.createQuery("update Unidades as u set u.nome  = '" + nome + "', u.email = '" + email
					+ "', u.login = '" + login + "', u.senha= '" + senha + "', u.telefone= '" + telefone
					+ "' where u.id_unidade = " + id_unidade);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
	}

	public void limpar_dados(Integer id_unidade) throws Exception {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			query = session.createQuery(
					"update Unidades as u set u.acesso_cadastrado = false, u.usuario.id_usuario = NULL where u.id_unidade = "
							+ id_unidade);
			System.out.println(query);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
	}

	public List<Unidades> findByCodeUni(Integer cod_cad01, String unidade, String bloco)
			throws HibernateException, SQLException {
		List<Unidades> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			query = session.createQuery("from Unidades as u where u.cliente.codigo = " + cod_cad01 + " and u.bloco = '"
					+ bloco + "' and u.unidade = '" + unidade + "'");
			lista = query.list();
			query.uniqueResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
		return lista;
	}

	public List<Unidades> findAll(Integer codigo) throws HibernateException, SQLException {
		List<Unidades> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			lista = session
					.createQuery("from Unidades as u where u.cliente.codigo= " + codigo + " ORDER BY bloco,unidade")
					.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
		return lista;
	}

	public List<Unidades> findAll_Unidades_Cli(Integer id_usuario) throws HibernateException, SQLException {
		List<Unidades> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			query = session.createQuery(
					"select u.cliente as cliente, u.bloco as bloco, u.unidade as unidade from Unidades as u where  u.usuario.id_usuario= "
							+ id_usuario + " ORDER BY u.bloco, u.unidade");
			query.setResultTransformer(Transformers.aliasToBean(Unidades.class));
			lista = query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
		return lista;
	}

	public List<Integer> FindBlocos(Integer i) throws HibernateException, SQLException {
		List<Integer> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			query = session.createQuery(
					"select DISTINCT bloco from Unidades as u where u.cliente.codigo=" + i + " ORDER BY bloco");
			lista = query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
		return lista;

	}

	public List<Integer> FindUnidades(Integer i) throws HibernateException, SQLException {

		List<Integer> lista = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			query = session.createQuery(
					"select DISTINCT unidade from Unidades as u where u.cliente.codigo=" + i + " ORDER BY unidade");
			lista = query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
		return lista;

	}

	public int aleatoriar() {
		Random random = new Random();
		return random.nextInt((9999 - 1000) + 1) + 1000;
	}

	public static void main(String[] args) {
		UnidadesDao u = new UnidadesDao();

		try {

			System.out.println(u.findAll_Unidades_Cli(621));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

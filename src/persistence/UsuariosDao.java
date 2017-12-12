package persistence;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.jcommon.encryption.SimpleMD5;

//import config.EmailUtil;
import config.HibernateUtil;
//import entity.Registros_leitura_medicao;
//import entity.Unidades;
import entity.Usuarios;
//import jersey.repackaged.org.objectweb.asm.TypeReference;

public class UsuariosDao {

	Session session;
	Transaction transaction;
	Query query;
	Criteria criteria;

	public Usuarios findByCode(Integer id) throws HibernateException, SQLException {
		Usuarios usuarios = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			usuarios = (Usuarios) session.get(Usuarios.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
		return usuarios;
	}

	public List<Usuarios> findEmail(String email) throws HibernateException, SQLException {
		List<Usuarios> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			query = session.createQuery("from Usuarios as u where u.email= '" + email + "'");
			lista = query.list();
			query.uniqueResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
		return lista;

	}

	public void gravar(Usuarios u) throws Exception {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(u);
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
	}

	public void update(String telefone, String senha, String email) throws Exception {
		try {
			SimpleMD5 md5 = new SimpleMD5(senha, "");
			senha = md5.toHexString().toUpperCase();
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			query = session.createQuery("update Usuarios as u set u.telefone='" + telefone + "'  , u.senha ='"
					+ senha.toUpperCase() + "' where u.email='" + email + "'");
			query.executeUpdate();
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
	}

	public void esqueci_senha(String senha, String email) throws Exception {
		try {
			SimpleMD5 md5 = new SimpleMD5(senha, "");
			senha = md5.toHexString().toUpperCase();
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			query = session.createQuery(
					"update Usuarios as u set  u.senha ='" + senha.toUpperCase() + "' where u.email='" + email + "'");
			query.executeUpdate();
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
	}

	public List<Usuarios> findAll(Integer id_usuario) throws HibernateException, SQLException {
		List<Usuarios> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			lista = session.createQuery("from Usuarios as u where u.id_usuario= " + id_usuario).list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
		return lista;
	}

	public Usuarios logar(String email, String senha) throws Exception {
		Usuarios user = null;
		try {
			SimpleMD5 md5 = new SimpleMD5(senha, "");
			senha = md5.toHexString().toUpperCase();

			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(Usuarios.class);
			// select * from usuario
			criteria.add(Restrictions.eq("email", email.toUpperCase()));
			// where email = u.getEmail()
			criteria.add(Restrictions.eq("senha", senha.toUpperCase()));
			// and senha = u.getSenha()
			user = (Usuarios) criteria.uniqueResult();
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

	public static void main(String[] args) {
		try {
			new UsuariosDao().logar("daiane_cruz12@hotmail.com", "daiane123");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package persistence;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import config.HibernateUtil;
import entity.Clientes;
import entity.Unidades;

public class ClienteDao {

	Session session;
	Transaction transaction;
	Query query;
	Criteria criteria;

	public Clientes findByCode(Integer codigo) throws HibernateException, SQLException {

		Clientes cliente = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			cliente = (Clientes) session.get(Clientes.class, codigo);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
		return cliente;
	}

	public List<Clientes> findCNPJ(String codigo) throws HibernateException, SQLException {

		List<Clientes> lista = null;

		try {

			session = HibernateUtil.getSessionFactory().openSession();
			lista = session.createQuery("from Clientes as c where c.cnpj_cpf= '" + codigo + "'").list();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
		return lista;
	}

	public static void main(String[] args) {
		ClienteDao ud = new ClienteDao();
		try {

			System.out.println(ud.findCNPJ("08365370000134"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

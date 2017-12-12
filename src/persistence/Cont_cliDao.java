package persistence;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import config.HibernateUtil;
import entity.Cont_cli;



/*
 * Classe que faz as operações no banco da tabela de Contatos
 */
public class Cont_cliDao {
	
	Session session;
	Transaction transaction;
	Query query;
	Criteria criteria;

	// Faz a consulta pelo codigo do cliente.
	public List<Cont_cli> findAll(Integer i) throws HibernateException, SQLException {

		List<Cont_cli> lista = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			query = session.createQuery("from Cont_cli as c where c.cod_cli=" + i);
			lista = query.list();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
		return lista;

	}


}

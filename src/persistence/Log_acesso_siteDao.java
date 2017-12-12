package persistence;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import config.HibernateUtil;
import entity.Log_acesso_site;


public class Log_acesso_siteDao {
	
	Session session;
	Transaction transaction;
	Query query;
	Criteria criteria;
	
	public void create(Log_acesso_site l) {

		try {

			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			session.save(l);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			HibernateUtil.fechar_conexao(session);

		}
	}

	
}

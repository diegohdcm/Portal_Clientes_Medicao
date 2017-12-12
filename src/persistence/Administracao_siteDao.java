package persistence;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.jcommon.encryption.SimpleMD5;

import config.HibernateUtil;
import control.Controle;
import entity.Administracao_site;
import entity.Unidades;

public class Administracao_siteDao {
	Session session;
	Transaction transaction;
	Query query;
	Criteria criteria;

	public Administracao_site logar(String login, String senha) throws Exception {

		Administracao_site user = null;

		try {

			SimpleMD5 md5 = new SimpleMD5(senha, "");
			senha = md5.toHexString().toUpperCase();
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(Administracao_site.class);
			// select * from usuario
			criteria.add(Restrictions.eq("login", login.toUpperCase()));
			// where email = u.getEmail()
			criteria.add(Restrictions.eq("senha", senha.toUpperCase()));
			// and senha = u.getSenha()
			user = (Administracao_site) criteria.uniqueResult();
			// user recebe o resultado da busca feita no banco
			// user pode estar cheio(dados corretos) ou vazio(dados Invalidos;;)

		} catch (Exception ex) {
			
			Controle.erroConexao();
			
		} finally {

			HibernateUtil.fechar_conexao(session);

		}

		return user;
	}

	public static void main(String[] args) {
		Administracao_siteDao ud = new Administracao_siteDao();
		try {
			System.out.println(ud.logar("VILLaAL084", "23823"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

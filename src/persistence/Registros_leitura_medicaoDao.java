package persistence;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import config.HibernateUtil;
import entity.Registros_leitura_medicao;

public class Registros_leitura_medicaoDao {
	Session session;
	Transaction transaction;
	Query query;
	Criteria criteria;

	public Registros_leitura_medicao findByCode(Integer cod_cad01) throws HibernateException, SQLException {

		Registros_leitura_medicao registros = null;

		try {

			session = HibernateUtil.getSessionFactory().openSession();
			registros = (Registros_leitura_medicao) session.get(Registros_leitura_medicao.class, cod_cad01);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.fechar_conexao(session);
		}
		return registros;
	}

	public List<Registros_leitura_medicao> findAll(Integer cod_cad01, String bloco, String unidade)
			throws HibernateException, SQLException {

		List<Registros_leitura_medicao> lista = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			query = session.createQuery(
					"select r.data_da_leitura as data_da_leitura, r.qtd_dias_lidos as qtd_dias_lidos, r.media_condo as media_condo, r.bloco as bloco, r.unidade as unidade, SUM(r.consumo) as consumo, SUM(r.valor_consumo_unidade) as valor_consumo_unidade, "
							+ "SUM(r.valor_rateio_area_comum) as valor_rateio_area_comum, SUM(r.valor_consumo_total) as valor_consumo_total from Registros_leitura_medicao as r where r.cliente.codigo="
							+ cod_cad01 + " and r.bloco ='" + bloco + "' and r.unidade = '" + unidade
							+ "' GROUP BY r.bloco, r.unidade, r.data_da_leitura, r.qtd_dias_lidos, r.media_condo ORDER BY r.data_da_leitura desc");
			query.setMaxResults(12);
			query.setResultTransformer(Transformers.aliasToBean(Registros_leitura_medicao.class));
			lista = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.fechar_conexao(session);
		}
		return lista;

	}

	public List<Registros_leitura_medicao> findDetalhes(Integer cod_cad01, String bloco, String unidade)
			throws HibernateException, SQLException {

		List<Registros_leitura_medicao> lista = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			query = session.createQuery(
					"select  r.data_da_leitura as data_da_leitura,  r.data_da_leitura_anterior as data_da_leitura_anterior, r.numero_hidrometro as numero_hidrometro, r.localizacao as localizacao, r.indice as indice, r.indice_antigo as indice_antigo,  r.consumo as consumo, r.fluxo_continuo as fluxo_continuo  "
							+ " from Registros_leitura_medicao as r where r.cliente.codigo=" + cod_cad01
							+ " and r.bloco ='" + bloco + "' and r.unidade = '" + unidade
							+ "' ORDER BY r.data_da_leitura desc");
			query.setResultTransformer(Transformers.aliasToBean(Registros_leitura_medicao.class));
			lista = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.fechar_conexao(session);
		}
		return lista;

	}

	public static void main(String[] args) {

		Registros_leitura_medicaoDao r = new Registros_leitura_medicaoDao();

		try {
			System.out.println(r.findDetalhes(19408, "00", "1001"));
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

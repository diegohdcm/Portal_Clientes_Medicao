package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entity.Registros_leitura_medicao;

@ManagedBean
@ViewScoped
public class ChartController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String data;
	private String valor;
	private List<Registros_leitura_medicao> datalistr;

	@PostConstruct
	public void init() {

		List<String> data = Arrays.asList(
				"'06/2016', '07/2016', '08/2016', '09/2016', '10/2016', '11/2016', '12/2016', '01/2017', '02/2017','03/2017', '04/2017', '05/2017' ");

	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public List<Registros_leitura_medicao> getDatalist() {
		return datalistr;
	}

	public void setDatalist(List<Registros_leitura_medicao> datalist) {
		this.datalistr = datalist;
	}

}

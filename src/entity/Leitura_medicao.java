package entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*
 * Classe de Leituras, utilizando Hibernate para mapear o banco.
 */
@Entity
public class Leitura_medicao{


	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id_leitura;

	@Id
	@Column
	private Integer id_leitura_medicao;

	// Relacionamento com a tabela de Clientes.
	@ManyToOne
	@JoinColumn(name = "cod_cad01")
	private Clientes cliente;

	@Column
	private Date data_da_leitura;

	@Column
	private Date data_leitura_cedae;

	@Column
	private Date data_leitura_anterior;

	@Column
	private Double valor_cedae;

	@Column
	private Double consumo_cedae;

	@Column
	private Integer tipo_leitura;

	@Column
	private Integer dias;

	@Column
	private Integer qtd_dias_concessionaria;

	@Column
	private Double valor_area_comum;

	@Column
	private Double rateio_area_comum;

	@Column
	private Double consumo_cedae_considerado;

	@Column
	private String mesano;

	@Column
	private Boolean primeira_leitura;

	@Column
	private Double valor_total_unidades;

	@Column
	private Double valor_medio_unidades;

	@Column
	private Double consumo_unidades;

	@Column
	private Double consumo_medido_concessionaria;

	@Column
	private Double valor_rateio;

	@Column
	private Double percentual_area_comum;

	@Column
	private Double consumo_minimo;

	@Column
	private Double qtd_unid_cadastradas;

	@Column
	private String metodo_calculo_consumo;

	@Column
	private Integer num_contr;

	@Column
	private Integer tipo_de_faturamento;

	@Column
	private Double valor_minimo_conta;

	@Column
	private Double valor_ajuste;

	@Column
	private Date vigencia_progressiva;

	@Column
	private Boolean enviado;

	public Leitura_medicao() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId_leitura() {
		return id_leitura;
	}

	public void setId_leitura(Integer id_leitura) {
		this.id_leitura = id_leitura;
	}

	public Integer getId_leitura_medicao() {
		return id_leitura_medicao;
	}

	public void setId_leitura_medicao(Integer id_leitura_medicao) {
		this.id_leitura_medicao = id_leitura_medicao;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Date getData_da_leitura() {
		return data_da_leitura;
	}

	public void setData_da_leitura(Date data_da_leitura) {
		this.data_da_leitura = data_da_leitura;
	}

	public Date getData_leitura_cedae() {
		return data_leitura_cedae;
	}

	public void setData_leitura_cedae(Date data_leitura_cedae) {
		this.data_leitura_cedae = data_leitura_cedae;
	}

	public Date getData_leitura_anterior() {
		return data_leitura_anterior;
	}

	public void setData_leitura_anterior(Date data_leitura_anterior) {
		this.data_leitura_anterior = data_leitura_anterior;
	}

	public Double getValor_cedae() {
		return valor_cedae;
	}

	public void setValor_cedae(Double valor_cedae) {
		this.valor_cedae = valor_cedae;
	}

	public Double getConsumo_cedae() {
		return consumo_cedae;
	}

	public void setConsumo_cedae(Double consumo_cedae) {
		this.consumo_cedae = consumo_cedae;
	}

	public Integer getTipo_leitura() {
		return tipo_leitura;
	}

	public void setTipo_leitura(Integer tipo_leitura) {
		this.tipo_leitura = tipo_leitura;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public Integer getQtd_dias_concessionaria() {
		return qtd_dias_concessionaria;
	}

	public void setQtd_dias_concessionaria(Integer qtd_dias_concessionaria) {
		this.qtd_dias_concessionaria = qtd_dias_concessionaria;
	}

	public Double getValor_area_comum() {
		return valor_area_comum;
	}

	public void setValor_area_comum(Double valor_area_comum) {
		this.valor_area_comum = valor_area_comum;
	}

	public Double getRateio_area_comum() {
		return rateio_area_comum;
	}

	public void setRateio_area_comum(Double rateio_area_comum) {
		this.rateio_area_comum = rateio_area_comum;
	}

	public Double getConsumo_cedae_considerado() {
		return consumo_cedae_considerado;
	}

	public void setConsumo_cedae_considerado(Double consumo_cedae_considerado) {
		this.consumo_cedae_considerado = consumo_cedae_considerado;
	}

	public String getMesano() {
		return mesano;
	}

	public void setMesano(String mesano) {
		this.mesano = mesano;
	}

	public Boolean getPrimeira_leitura() {
		return primeira_leitura;
	}

	public void setPrimeira_leitura(Boolean primeira_leitura) {
		this.primeira_leitura = primeira_leitura;
	}

	public Double getValor_total_unidades() {
		return valor_total_unidades;
	}

	public void setValor_total_unidades(Double valor_total_unidades) {
		this.valor_total_unidades = valor_total_unidades;
	}

	public Double getValor_medio_unidades() {
		return valor_medio_unidades;
	}

	public void setValor_medio_unidades(Double valor_medio_unidades) {
		this.valor_medio_unidades = valor_medio_unidades;
	}

	public Double getConsumo_unidades() {
		return consumo_unidades;
	}

	public void setConsumo_unidades(Double consumo_unidades) {
		this.consumo_unidades = consumo_unidades;
	}

	public Double getConsumo_medido_concessionaria() {
		return consumo_medido_concessionaria;
	}

	public void setConsumo_medido_concessionaria(Double consumo_medido_concessionaria) {
		this.consumo_medido_concessionaria = consumo_medido_concessionaria;
	}

	public Double getValor_rateio() {
		return valor_rateio;
	}

	public void setValor_rateio(Double valor_rateio) {
		this.valor_rateio = valor_rateio;
	}

	public Double getPercentual_area_comum() {
		return percentual_area_comum;
	}

	public void setPercentual_area_comum(Double percentual_area_comum) {
		this.percentual_area_comum = percentual_area_comum;
	}

	public Double getConsumo_minimo() {
		return consumo_minimo;
	}

	public void setConsumo_minimo(Double consumo_minimo) {
		this.consumo_minimo = consumo_minimo;
	}

	public Double getQtd_unid_cadastradas() {
		return qtd_unid_cadastradas;
	}

	public void setQtd_unid_cadastradas(Double qtd_unid_cadastradas) {
		this.qtd_unid_cadastradas = qtd_unid_cadastradas;
	}

	public String getMetodo_calculo_consumo() {
		return metodo_calculo_consumo;
	}

	public void setMetodo_calculo_consumo(String metodo_calculo_consumo) {
		this.metodo_calculo_consumo = metodo_calculo_consumo;
	}

	public Integer getNum_contr() {
		return num_contr;
	}

	public void setNum_contr(Integer num_contr) {
		this.num_contr = num_contr;
	}

	public Integer getTipo_de_faturamento() {
		return tipo_de_faturamento;
	}

	public void setTipo_de_faturamento(Integer tipo_de_faturamento) {
		this.tipo_de_faturamento = tipo_de_faturamento;
	}

	public Double getValor_minimo_conta() {
		return valor_minimo_conta;
	}

	public void setValor_minimo_conta(Double valor_minimo_conta) {
		this.valor_minimo_conta = valor_minimo_conta;
	}

	public Double getValor_ajuste() {
		return valor_ajuste;
	}

	public void setValor_ajuste(Double valor_ajuste) {
		this.valor_ajuste = valor_ajuste;
	}

	public Date getVigencia_progressiva() {
		return vigencia_progressiva;
	}

	public void setVigencia_progressiva(Date vigencia_progressiva) {
		this.vigencia_progressiva = vigencia_progressiva;
	}

	public Boolean getEnviado() {
		return enviado;
	}

	public void setEnviado(Boolean enviado) {
		this.enviado = enviado;
	}

	public Leitura_medicao(Integer id_leitura, Integer id_leitura_medicao, Clientes cliente, Date data_da_leitura,
			Date data_leitura_cedae, Date data_leitura_anterior, Double valor_cedae, Double consumo_cedae,
			Integer tipo_leitura, Integer dias, Integer qtd_dias_concessionaria, Double valor_area_comum,
			Double rateio_area_comum, Double consumo_cedae_considerado, String mesano, Boolean primeira_leitura,
			Double valor_total_unidades, Double valor_medio_unidades, Double consumo_unidades,
			Double consumo_medido_concessionaria, Double valor_rateio, Double percentual_area_comum,
			Double consumo_minimo, Double qtd_unid_cadastradas, String metodo_calculo_consumo, Integer num_contr,
			Integer tipo_de_faturamento, Double valor_minimo_conta, Double valor_ajuste, Date vigencia_progressiva,
			Boolean enviado) {
		super();
		this.id_leitura = id_leitura;
		this.id_leitura_medicao = id_leitura_medicao;
		this.cliente = cliente;
		this.data_da_leitura = data_da_leitura;
		this.data_leitura_cedae = data_leitura_cedae;
		this.data_leitura_anterior = data_leitura_anterior;
		this.valor_cedae = valor_cedae;
		this.consumo_cedae = consumo_cedae;
		this.tipo_leitura = tipo_leitura;
		this.dias = dias;
		this.qtd_dias_concessionaria = qtd_dias_concessionaria;
		this.valor_area_comum = valor_area_comum;
		this.rateio_area_comum = rateio_area_comum;
		this.consumo_cedae_considerado = consumo_cedae_considerado;
		this.mesano = mesano;
		this.primeira_leitura = primeira_leitura;
		this.valor_total_unidades = valor_total_unidades;
		this.valor_medio_unidades = valor_medio_unidades;
		this.consumo_unidades = consumo_unidades;
		this.consumo_medido_concessionaria = consumo_medido_concessionaria;
		this.valor_rateio = valor_rateio;
		this.percentual_area_comum = percentual_area_comum;
		this.consumo_minimo = consumo_minimo;
		this.qtd_unid_cadastradas = qtd_unid_cadastradas;
		this.metodo_calculo_consumo = metodo_calculo_consumo;
		this.num_contr = num_contr;
		this.tipo_de_faturamento = tipo_de_faturamento;
		this.valor_minimo_conta = valor_minimo_conta;
		this.valor_ajuste = valor_ajuste;
		this.vigencia_progressiva = vigencia_progressiva;
		this.enviado = enviado;
	}

	@Override
	public String toString() {
		return "Leitura_medicao [id_leitura=" + id_leitura + ", id_leitura_medicao=" + id_leitura_medicao + ", cliente="
				+ cliente + ", data_da_leitura=" + data_da_leitura + ", data_leitura_cedae=" + data_leitura_cedae
				+ ", data_leitura_anterior=" + data_leitura_anterior + ", valor_cedae=" + valor_cedae
				+ ", consumo_cedae=" + consumo_cedae + ", tipo_leitura=" + tipo_leitura + ", dias=" + dias
				+ ", qtd_dias_concessionaria=" + qtd_dias_concessionaria + ", valor_area_comum=" + valor_area_comum
				+ ", rateio_area_comum=" + rateio_area_comum + ", consumo_cedae_considerado="
				+ consumo_cedae_considerado + ", mesano=" + mesano + ", primeira_leitura=" + primeira_leitura
				+ ", valor_total_unidades=" + valor_total_unidades + ", valor_medio_unidades=" + valor_medio_unidades
				+ ", consumo_unidades=" + consumo_unidades + ", consumo_medido_concessionaria="
				+ consumo_medido_concessionaria + ", valor_rateio=" + valor_rateio + ", percentual_area_comum="
				+ percentual_area_comum + ", consumo_minimo=" + consumo_minimo + ", qtd_unid_cadastradas="
				+ qtd_unid_cadastradas + ", metodo_calculo_consumo=" + metodo_calculo_consumo + ", num_contr="
				+ num_contr + ", tipo_de_faturamento=" + tipo_de_faturamento + ", valor_minimo_conta="
				+ valor_minimo_conta + ", valor_ajuste=" + valor_ajuste + ", vigencia_progressiva="
				+ vigencia_progressiva + ", enviado=" + enviado + "]";
	}
	
	

}

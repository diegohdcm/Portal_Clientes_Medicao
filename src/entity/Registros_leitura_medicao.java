package entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Registros_leitura_medicao {


	@Id
	@Column
	private Integer id_registros;

	@Column
	private Date data_da_leitura;
	
	@Column
	private Date data_da_leitura_anterior;

	@ManyToOne
	@JoinColumn(name = "cod_cad01")
	private Clientes cliente;

	@Column
	private String localizacao;

	@Column
	private Double indice;

	@Column
	private Double indice_considerado;

	@Column
	private Double indice_antigo;

	@Column
	private Double consumo;

	@Column
	private Double consumo_considerado;

	@Column
	private String numero_hidrometro;

	@Column
	private Double valor_consumo_unidade;

	@Column
	private Double valor_rateio_area_comum;

	@Column
	private Double valor_consumo_total;

	@Column
	private String mesano;

	@Column
	private Boolean leitura_teste;

	@Column
	private Boolean primeira_leitura;

	@Column
	private String descrmes;

	@Column
	private String bloco;

	@Column
	private String unidade;

	@Column
	private Integer qtd_dias_lidos;

	@Column
	private String anomes;

	@Column
	private Double fator1;

	@Column
	private Double fator2;

	@Column
	private Double fator3;

	@Column
	private Double fator4;

	@Column
	private Double fator5;

	@Column
	private Double faixa1;

	@Column
	private Double faixa2;

	@Column
	private Double faixa3;

	@Column
	private Double faixa4;

	@Column
	private Double faixa5;

	@Column
	private Double faixaconsumo1;

	@Column
	private Double faixaconsumo2;

	@Column
	private Double faixaconsumo3;

	@Column
	private Double faixaconsumo4;

	@Column
	private Double faixaconsumo5;

	@Column
	private Integer num_contr;

	@Column
	private Boolean fluxo_continuo;
	
	@Column
	private Double media_condo;

	

	public Double getMedia_condo() {
		return media_condo;
	}

	public void setMedia_condo(Double media_condo) {
		this.media_condo = media_condo;
	}

	public Integer getId_registros() {
		return id_registros;
	}

	public void setId_registros(Integer id_registros) {
		this.id_registros = id_registros;
	}

	public Date getData_da_leitura() {
		return data_da_leitura;
	}

	public void setData_da_leitura(Date data_da_leitura) {
		this.data_da_leitura = data_da_leitura;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Double getIndice() {
		return indice;
	}

	public void setIndice(Double indice) {
		this.indice = indice;
	}

	public Double getIndice_considerado() {
		return indice_considerado;
	}

	public void setIndice_considerado(Double indice_considerado) {
		this.indice_considerado = indice_considerado;
	}

	public Double getIndice_antigo() {
		return indice_antigo;
	}

	public void setIndice_antigo(Double indice_antigo) {
		this.indice_antigo = indice_antigo;
	}

	public Double getConsumo() {
		return consumo;
	}

	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}

	public Double getConsumo_considerado() {
		return consumo_considerado;
	}

	public void setConsumo_considerado(Double consumo_considerado) {
		this.consumo_considerado = consumo_considerado;
	}

	public String getNumero_hidrometro() {
		return numero_hidrometro;
	}

	public void setNumero_hidrometro(String numero_hidrometro) {
		this.numero_hidrometro = numero_hidrometro;
	}

	public Double getValor_consumo_unidade() {
		return valor_consumo_unidade;
	}

	public void setValor_consumo_unidade(Double valor_consumo_unidade) {
		this.valor_consumo_unidade = valor_consumo_unidade;
	}

	public Double getValor_rateio_area_comum() {
		return valor_rateio_area_comum;
	}

	public void setValor_rateio_area_comum(Double valor_rateio_area_comum) {
		this.valor_rateio_area_comum = valor_rateio_area_comum;
	}

	public Double getValor_consumo_total() {
		return valor_consumo_total;
	}

	public void setValor_consumo_total(Double valor_consumo_total) {
		this.valor_consumo_total = valor_consumo_total;
	}

	public String getMesano() {
		return mesano;
	}

	public void setMesano(String mesano) {
		this.mesano = mesano;
	}

	public Boolean getLeitura_teste() {
		return leitura_teste;
	}

	public void setLeitura_teste(Boolean leitura_teste) {
		this.leitura_teste = leitura_teste;
	}

	public Boolean getPrimeira_leitura() {
		return primeira_leitura;
	}

	public void setPrimeira_leitura(Boolean primeira_leitura) {
		this.primeira_leitura = primeira_leitura;
	}

	public String getDescrmes() {
		return descrmes;
	}

	public void setDescrmes(String descrmes) {
		this.descrmes = descrmes;
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public Integer getQtd_dias_lidos() {
		return qtd_dias_lidos;
	}

	public void setQtd_dias_lidos(Integer qtd_dias_lidos) {
		this.qtd_dias_lidos = qtd_dias_lidos;
	}

	public String getAnomes() {
		return anomes;
	}

	public void setAnomes(String anomes) {
		this.anomes = anomes;
	}

	public Double getFator1() {
		return fator1;
	}

	public void setFator1(Double fator1) {
		this.fator1 = fator1;
	}

	public Double getFator2() {
		return fator2;
	}

	public void setFator2(Double fator2) {
		this.fator2 = fator2;
	}

	public Double getFator3() {
		return fator3;
	}

	public void setFator3(Double fator3) {
		this.fator3 = fator3;
	}

	public Double getFator4() {
		return fator4;
	}

	public void setFator4(Double fator4) {
		this.fator4 = fator4;
	}

	public Double getFator5() {
		return fator5;
	}

	public void setFator5(Double fator5) {
		this.fator5 = fator5;
	}

	public Double getFaixa1() {
		return faixa1;
	}

	public void setFaixa1(Double faixa1) {
		this.faixa1 = faixa1;
	}

	public Double getFaixa2() {
		return faixa2;
	}

	public void setFaixa2(Double faixa2) {
		this.faixa2 = faixa2;
	}

	public Double getFaixa3() {
		return faixa3;
	}

	public void setFaixa3(Double faixa3) {
		this.faixa3 = faixa3;
	}

	public Double getFaixa4() {
		return faixa4;
	}

	public void setFaixa4(Double faixa4) {
		this.faixa4 = faixa4;
	}

	public Double getFaixa5() {
		return faixa5;
	}

	public void setFaixa5(Double faixa5) {
		this.faixa5 = faixa5;
	}

	public Double getFaixaconsumo1() {
		return faixaconsumo1;
	}

	public void setFaixaconsumo1(Double faixaconsumo1) {
		this.faixaconsumo1 = faixaconsumo1;
	}

	public Double getFaixaconsumo2() {
		return faixaconsumo2;
	}

	public void setFaixaconsumo2(Double faixaconsumo2) {
		this.faixaconsumo2 = faixaconsumo2;
	}

	public Double getFaixaconsumo3() {
		return faixaconsumo3;
	}

	public void setFaixaconsumo3(Double faixaconsumo3) {
		this.faixaconsumo3 = faixaconsumo3;
	}

	public Double getFaixaconsumo4() {
		return faixaconsumo4;
	}

	public void setFaixaconsumo4(Double faixaconsumo4) {
		this.faixaconsumo4 = faixaconsumo4;
	}

	public Double getFaixaconsumo5() {
		return faixaconsumo5;
	}

	public void setFaixaconsumo5(Double faixaconsumo5) {
		this.faixaconsumo5 = faixaconsumo5;
	}

	public Integer getNum_contr() {
		return num_contr;
	}

	public void setNum_contr(Integer num_contr) {
		this.num_contr = num_contr;
	}

	public Boolean getFluxo_continuo() {
		return fluxo_continuo;
	}

	public void setFluxo_continuo(Boolean fluxo_continuo) {
		this.fluxo_continuo = fluxo_continuo;
	}
	public Registros_leitura_medicao() {
		// TODO Auto-generated constructor stub
	}

	public Date getData_da_leitura_anterior() {
		return data_da_leitura_anterior;
	}

	public void setData_da_leitura_anterior(Date data_da_leitura_anterior) {
		this.data_da_leitura_anterior = data_da_leitura_anterior;
	}

	@Override
	public String toString() {
		return "Registros_leitura_medicao [id_registros=" + id_registros + ", data_da_leitura=" + data_da_leitura
				+ ", data_da_leitura_anterior=" + data_da_leitura_anterior + ", cliente=" + cliente + ", localizacao="
				+ localizacao + ", indice=" + indice + ", indice_considerado=" + indice_considerado + ", indice_antigo="
				+ indice_antigo + ", consumo=" + consumo + ", consumo_considerado=" + consumo_considerado
				+ ", numero_hidrometro=" + numero_hidrometro + ", valor_consumo_unidade=" + valor_consumo_unidade
				+ ", valor_rateio_area_comum=" + valor_rateio_area_comum + ", valor_consumo_total="
				+ valor_consumo_total + ", mesano=" + mesano + ", leitura_teste=" + leitura_teste
				+ ", primeira_leitura=" + primeira_leitura + ", descrmes=" + descrmes + ", bloco=" + bloco
				+ ", unidade=" + unidade + ", qtd_dias_lidos=" + qtd_dias_lidos + ", anomes=" + anomes + ", fator1="
				+ fator1 + ", fator2=" + fator2 + ", fator3=" + fator3 + ", fator4=" + fator4 + ", fator5=" + fator5
				+ ", faixa1=" + faixa1 + ", faixa2=" + faixa2 + ", faixa3=" + faixa3 + ", faixa4=" + faixa4
				+ ", faixa5=" + faixa5 + ", faixaconsumo1=" + faixaconsumo1 + ", faixaconsumo2=" + faixaconsumo2
				+ ", faixaconsumo3=" + faixaconsumo3 + ", faixaconsumo4=" + faixaconsumo4 + ", faixaconsumo5="
				+ faixaconsumo5 + ", num_contr=" + num_contr + ", fluxo_continuo=" + fluxo_continuo + ", media_condo="
				+ media_condo + "]";
	}

	public Registros_leitura_medicao(Integer id_registros, Date data_da_leitura, Date data_da_leitura_anterior,
			Clientes cliente, String localizacao, Double indice, Double indice_considerado, Double indice_antigo,
			Double consumo, Double consumo_considerado, String numero_hidrometro, Double valor_consumo_unidade,
			Double valor_rateio_area_comum, Double valor_consumo_total, String mesano, Boolean leitura_teste,
			Boolean primeira_leitura, String descrmes, String bloco, String unidade, Integer qtd_dias_lidos,
			String anomes, Double fator1, Double fator2, Double fator3, Double fator4, Double fator5, Double faixa1,
			Double faixa2, Double faixa3, Double faixa4, Double faixa5, Double faixaconsumo1, Double faixaconsumo2,
			Double faixaconsumo3, Double faixaconsumo4, Double faixaconsumo5, Integer num_contr, Boolean fluxo_continuo,
			Double media_condo) {
		super();
		this.id_registros = id_registros;
		this.data_da_leitura = data_da_leitura;
		this.data_da_leitura_anterior = data_da_leitura_anterior;
		this.cliente = cliente;
		this.localizacao = localizacao;
		this.indice = indice;
		this.indice_considerado = indice_considerado;
		this.indice_antigo = indice_antigo;
		this.consumo = consumo;
		this.consumo_considerado = consumo_considerado;
		this.numero_hidrometro = numero_hidrometro;
		this.valor_consumo_unidade = valor_consumo_unidade;
		this.valor_rateio_area_comum = valor_rateio_area_comum;
		this.valor_consumo_total = valor_consumo_total;
		this.mesano = mesano;
		this.leitura_teste = leitura_teste;
		this.primeira_leitura = primeira_leitura;
		this.descrmes = descrmes;
		this.bloco = bloco;
		this.unidade = unidade;
		this.qtd_dias_lidos = qtd_dias_lidos;
		this.anomes = anomes;
		this.fator1 = fator1;
		this.fator2 = fator2;
		this.fator3 = fator3;
		this.fator4 = fator4;
		this.fator5 = fator5;
		this.faixa1 = faixa1;
		this.faixa2 = faixa2;
		this.faixa3 = faixa3;
		this.faixa4 = faixa4;
		this.faixa5 = faixa5;
		this.faixaconsumo1 = faixaconsumo1;
		this.faixaconsumo2 = faixaconsumo2;
		this.faixaconsumo3 = faixaconsumo3;
		this.faixaconsumo4 = faixaconsumo4;
		this.faixaconsumo5 = faixaconsumo5;
		this.num_contr = num_contr;
		this.fluxo_continuo = fluxo_continuo;
		this.media_condo = media_condo;
	}

	

	
}

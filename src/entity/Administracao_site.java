package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Administracao_site  {


	@Id
	@Column
	private Integer id_lanc;

	@Column(length = 20)
	private String login;

	@Column(length = 50)
	private String senha;

	@Column
	private Integer num_contr;

	@Column(length = 20)
	private String posicao;

	@OneToOne
	@JoinColumn(name = "cod_cli")
	private Clientes cliente;


	public Integer getId_lanc() {
		return id_lanc;
	}

	public void setId_lanc(Integer id_lanc) {
		this.id_lanc = id_lanc;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getNum_contr() {
		return num_contr;
	}

	public void setNum_contr(Integer num_contr) {
		this.num_contr = num_contr;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}



	public Administracao_site() {
		// TODO Auto-generated constructor stub
	}

	public Administracao_site(Integer id_lanc, String login, String senha, Integer num_contr, String posicao,
			Integer cod_cli) {
		super();
		this.id_lanc = id_lanc;
		this.login = login;
		this.senha = senha;
		this.num_contr = num_contr;
		this.posicao = posicao;

	}

	@Override
	public String toString() {
		return "Administracao_site [id_lanc=" + id_lanc + ", login=" + login + ", senha=" + senha + ", num_contr="
				+ num_contr + ", posicao=" + posicao + ", cliente=" + cliente + "]";
	}



}

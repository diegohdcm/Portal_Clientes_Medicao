package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Unidades  {


	@Id
	@Column
	private Integer id_unidade;

	@ManyToOne
	@JoinColumn(name = "cod_cad01")
	private Clientes cliente;

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(optional = true)
	@JoinColumn(name = "id_usuario")
	private Usuarios usuario;

	@Column(length = 2)
	private String bloco;

	@Column(length = 10)
	private String unidade;

	@Column
	private Integer fracao_ideal;

	@Column(length = 50)
	private String senha;

	@Column
	private Integer qtd_hidrometros;

	@Column
	private Boolean totaliza_grupo;

	@Column
	private Boolean rateia_valor_grupo;

	@Column(length = 2)
	private String grupo;

	@Column(length = 20)
	private String login;

	@Column(length = 40)
	private String nome;

	@Column(length = 20)
	private String telefone;

	@Column(length = 150)
	private String email;

	@Column
	private Boolean acesso_cadastrado;

	@Column
	private Boolean nao_rateia_area_comum;

	@Column
	private Boolean soma_area_comum;

	@Column(length = 40)
	private String senha_antiga;

	public Integer getId_unidade() {
		return id_unidade;
	}

	public void setId_unidade(Integer id_unidade) {
		this.id_unidade = id_unidade;
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

	public Integer getFracao_ideal() {
		return fracao_ideal;
	}

	public void setFracao_ideal(Integer fracao_ideal) {
		this.fracao_ideal = fracao_ideal;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getQtd_hidrometros() {
		return qtd_hidrometros;
	}

	public void setQtd_hidrometros(Integer qtd_hidrometros) {
		this.qtd_hidrometros = qtd_hidrometros;
	}

	public Boolean getTotaliza_grupo() {
		return totaliza_grupo;
	}

	public void setTotaliza_grupo(Boolean totaliza_grupo) {
		this.totaliza_grupo = totaliza_grupo;
	}

	public Boolean getRateia_valor_grupo() {
		return rateia_valor_grupo;
	}

	public void setRateia_valor_grupo(Boolean rateia_valor_grupo) {
		this.rateia_valor_grupo = rateia_valor_grupo;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAcesso_cadastrado() {
		return acesso_cadastrado;
	}

	public void setAcesso_cadastrado(Boolean acesso_cadastrado) {
		this.acesso_cadastrado = acesso_cadastrado;
	}

	public Boolean getNao_rateia_area_comum() {
		return nao_rateia_area_comum;
	}

	public void setNao_rateia_area_comum(Boolean nao_rateia_area_comum) {
		this.nao_rateia_area_comum = nao_rateia_area_comum;
	}

	public Boolean getSoma_area_comum() {
		return soma_area_comum;
	}

	public void setSoma_area_comum(Boolean soma_area_comum) {
		this.soma_area_comum = soma_area_comum;
	}

	public String getSenha_antiga() {
		return senha_antiga;
	}

	public void setSenha_antiga(String senha_antiga) {
		this.senha_antiga = senha_antiga;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Unidades() {
		// TODO Auto-generated constructor stub
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Unidades(Integer id_unidade, String bloco, String unidade, Integer fracao_ideal, String senha,
			Integer qtd_hidrometros, Boolean totaliza_grupo, Boolean rateia_valor_grupo, String grupo, String login,
			String nome, String telefone, String email, Boolean acesso_cadastrado, Boolean nao_rateia_area_comum,
			Boolean soma_area_comum, String senha_antiga) {
		super();
		this.id_unidade = id_unidade;
		this.bloco = bloco;
		this.unidade = unidade;
		this.fracao_ideal = fracao_ideal;
		this.senha = senha;
		this.qtd_hidrometros = qtd_hidrometros;
		this.totaliza_grupo = totaliza_grupo;
		this.rateia_valor_grupo = rateia_valor_grupo;
		this.grupo = grupo;
		this.login = login;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.acesso_cadastrado = acesso_cadastrado;
		this.nao_rateia_area_comum = nao_rateia_area_comum;
		this.soma_area_comum = soma_area_comum;
		this.senha_antiga = senha_antiga;
	}

	@Override
	public String toString() {
		return "Unidades [id_unidade=" + id_unidade + ", cliente=" + cliente + ", usuario=" + usuario + ", bloco="
				+ bloco + ", unidade=" + unidade + ", fracao_ideal=" + fracao_ideal + ", senha=" + senha
				+ ", qtd_hidrometros=" + qtd_hidrometros + ", totaliza_grupo=" + totaliza_grupo
				+ ", rateia_valor_grupo=" + rateia_valor_grupo + ", grupo=" + grupo + ", login=" + login + ", nome="
				+ nome + ", telefone=" + telefone + ", email=" + email + ", acesso_cadastrado=" + acesso_cadastrado
				+ ", nao_rateia_area_comum=" + nao_rateia_area_comum + ", soma_area_comum=" + soma_area_comum
				+ ", senha_antiga=" + senha_antiga + "]";
	}



}

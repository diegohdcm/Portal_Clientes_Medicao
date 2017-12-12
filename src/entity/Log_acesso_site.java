package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Log_acesso_site implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@SequenceGenerator(name = "log_acesso_site_registro_seq", sequenceName = "log_acesso_site_registro_seq")
	@GeneratedValue(generator = "log_acesso_site_registro_seq", strategy = GenerationType.AUTO)
	private Integer registro;
	@Column
	private Integer cod_cad01;
	@Column
	private String bloco;
	@Column
	private String unidade;
	@Column
	private Boolean admin;
	@Column
	private String nom_admin;
	@Column
	private String nome;
	@Column
	private String email;

	public Log_acesso_site() {

	}

	public Log_acesso_site(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}
	
	

	public Log_acesso_site(Boolean admin, String nome, String email) {
		super();
		this.admin = admin;
		this.nome = nome;
		this.email = email;
	}

	public Log_acesso_site(Boolean admin, String nom_admin) {
		super();
		this.admin = admin;
		this.nom_admin = nom_admin;
	}

	public Integer getRegistro() {
		return registro;
	}

	public void setRegistro(Integer registro) {
		this.registro = registro;
	}

	public Integer getCod_cad01() {
		return cod_cad01;
	}

	public void setCod_cad01(Integer cod_cad01) {
		this.cod_cad01 = cod_cad01;
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

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}


	public String getNome() {
		return nome;
	}

	public String getNom_admin() {
		return nom_admin;
	}

	public void setNom_admin(String nom_admin) {
		this.nom_admin = nom_admin;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

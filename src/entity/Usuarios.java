package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity

public class Usuarios implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer id_usuario;
	
	@OneToMany(mappedBy = "usuario")
	private List<Unidades> unidades;

	@Column(length = 100)
	private String nome;

	@Column(length = 150)
	private String email;

	@Column(length = 200)
	private String foto;

	@Column(length = 200)
	private String senha;

	@Column(length = 30)
	private String telefone;

	@Column
	private String origem;

	@Column
	private String id_social;

	
	
	public List<Unidades> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidades> unidades) {
		this.unidades = unidades;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNome() {
		return nome;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getId_social() {
		return id_social;
	}

	public void setId_social(String id_social) {
		this.id_social = id_social;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Usuarios() {
		// TODO Auto-generated constructor stub
	}

	public Usuarios(Integer id_usuario, String nome, String email, String foto, String senha, String telefone,
			String origem, String id_social) {
		super();
		this.id_usuario = id_usuario;
		this.nome = nome;
		this.email = email;
		this.foto = foto;
		this.senha = senha;
		this.telefone = telefone;
		this.origem = origem;
		this.id_social = id_social;
	}

	@Override
	public String toString() {
		return "Usuarios [id_usuario=" + id_usuario + ", nome=" + nome + ", email=" + email + ", foto=" + foto
				+ ", senha=" + senha + ", Telefone=" + telefone + ", origem=" + origem + ", id_social=" + id_social
				+ "]";
	}

}

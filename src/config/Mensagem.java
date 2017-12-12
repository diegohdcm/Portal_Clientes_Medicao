package config;


/*
 *Classe que serve como container para o envio do email, recebe os parametros para passar para a classe de envio. 
 */
 

public class Mensagem {

	private String de;
	private String para;
	private String assunto;
	private String mensagem;

	public Mensagem() {
	}

	public Mensagem(String de, String para, String assunto, String mensagem) {
		super();
		this.de = de;
		this.para = para;
		this.assunto = assunto;
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return "Mensagem [de=" + de + ", para=" + para + ", assunto=" + assunto + ", mensagem=" + mensagem + "]";
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}

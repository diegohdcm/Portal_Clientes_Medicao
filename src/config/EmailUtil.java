package config;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.hibernate.Session;

public class EmailUtil {

	/*
	 * Classe Responsável pelo envio do email, recebe as informações do cliente
	 * e o arquivo em anexo. Envia para a Quantidade de clientes que estão
	 * contidos no Cadastro. Utiliza o smtp da locaweb para não ter problemas
	 * com a quantidade emails enviados. https://smtplw.com.br/panel
	 */

	// Constante do email;
	private static final String HOSTNAME = "smtplw.com.br";
	private static final String USERNAME = "hidroluz";
	private static final String PASSWORD = "WAAqqUbQ2338";

	// Parametros para conexão com o email.
	public static Email conectaEmail() throws EmailException {
		Email email = new HtmlEmail();
		email.setHostName(HOSTNAME);
		email.setSmtpPort(587);
		email.setAuthentication(USERNAME, PASSWORD);
		email.setTLS(true);
		email.setSSL(true);
		email.setFrom("medicao@hidroluz.com.br");
		// Email para cópia, serve para reencaminhar a msg caso o cliente diga
		// que não recebeu.
		return email;
	}

	// Metodo de envio, e usado na classe ManagerBean
	public static String enviarEmail(Mensagem m) throws Exception {
		HtmlEmail email = new HtmlEmail();

		// Para anexar o arquivo.

		email = (HtmlEmail) conectaEmail();

		email.setSubject(m.getAssunto());
		email.addTo(m.getPara());
		email.setCharset("utf-8");
		email.setMsg(m.getMensagem());

		email.send();

		return "Email enviado com Sucesso";
	}

	public static String enviarEmailAnexo(Mensagem m, String anexo) throws Exception {
		HtmlEmail email = new HtmlEmail();

		// Para anexar o arquivo.
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(anexo);
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		email = (HtmlEmail) conectaEmail();

		for (int i = 1; i < 2; i++) {
			email.setSubject(m.getAssunto());
			email.addTo(m.getPara());
			email.setCharset("utf-8");
			email.setMsg(m.getMensagem());
			email.attach(attachment);
			email.send();
		}

		return "Email enviado com Sucesso";
	}

	public static void main(String[] args) {
		String origem = "EMAIL";

		if (origem == "EMAIL") {

			System.out.println("OK");

		}
	}
	
	 

}

package br.com.sinaldasorte.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage simpleMailMessage) {
		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(simpleMailMessage.getTo());
			helper.setSubject(simpleMailMessage.getSubject());
			
			String textos[] = simpleMailMessage.getText().split("<split>");
			String texto = textos[0];
			String html = textos[1];
			
			helper.setText(String.format(texto), html);
			

		} catch (MessagingException e) {
			throw new MailParseException("Ocorreu um erro ao tentar preparar o e-mail para envio: "+ e.getMessage());
		}
		LOG.info("Enviando email...");
		javaMailSender.send(message);
		LOG.info("Email enviado");
	}
}

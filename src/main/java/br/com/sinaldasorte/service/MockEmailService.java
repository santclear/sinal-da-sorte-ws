package br.com.sinaldasorte.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private final Logger LOGGER = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOGGER.debug("Simulando envio de email...");
		LOGGER.debug(msg.toString());
		LOGGER.debug("Email enviado");
	}
}

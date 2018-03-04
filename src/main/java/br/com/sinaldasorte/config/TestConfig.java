package br.com.sinaldasorte.config;

import java.rmi.RemoteException;
import java.text.ParseException;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.sinaldasorte.service.DBService;
import br.com.sinaldasorte.service.EmailService;
import br.com.sinaldasorte.service.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException, RemoteException, ServiceException {
		dbService.instantiateTestDatabase();
		return true;
	}
	
	// @Bean faz com que o método esteja disponível no sistema como componente
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}

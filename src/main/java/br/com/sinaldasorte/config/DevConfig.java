package br.com.sinaldasorte.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.sinaldasorte.service.CurrentTimeDateTimeService;
import br.com.sinaldasorte.service.MockEmailService;
import br.com.sinaldasorte.service.interfaces.DateTimeService;
import br.com.sinaldasorte.service.interfaces.EmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	// @Bean faz com que o método esteja disponível no sistema como componente
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
	
    @Bean
    DateTimeService currentTimeDateTimeService() {
        return new CurrentTimeDateTimeService();
    }
}

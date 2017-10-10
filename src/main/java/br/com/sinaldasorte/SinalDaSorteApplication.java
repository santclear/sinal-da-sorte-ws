package br.com.sinaldasorte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SinalDaSorteApplication extends ServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SinalDaSorteApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SinalDaSorteApplication.class);
	}
}

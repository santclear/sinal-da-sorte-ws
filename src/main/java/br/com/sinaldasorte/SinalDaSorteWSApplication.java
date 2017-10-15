package br.com.sinaldasorte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SinalDaSorteWSApplication extends ServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SinalDaSorteWSApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SinalDaSorteWSApplication.class);
	}
}

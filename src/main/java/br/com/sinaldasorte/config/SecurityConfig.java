package br.com.sinaldasorte.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// 1. Quais caminhos que por padrão estarão liberados. Nesse caso, a partir do "/h2-console/" todos os caminhos "**" estão liberados

	private static final String[] PUBLIC_MATCHERS_GET = {
			"/concursos/**",
			"/loterias/**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// cors() ativa o corsConfigurationSource()
		// csrf() desabilitado, pois o sistema é stateless, não mantém sessão
		http.cors().and().csrf().disable();
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()/* permite somente recuperar os dados (HttpMethod.GET), não podendo inserir, deletar, etc. */
			.anyRequest().authenticated();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);/* assegura que o backend não armazenará sessão */
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// Permitindo acesso básico por multiplas fontes para todos os endpoints
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}
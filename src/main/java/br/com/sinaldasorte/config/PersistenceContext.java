package br.com.sinaldasorte.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.sinaldasorte.provider.AuditingDateTimeProvider;
import br.com.sinaldasorte.security.ContaAuditorAware;
import br.com.sinaldasorte.service.interfaces.DateTimeService;
  
  
@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@EnableJpaRepositories(basePackages = {
        "br.com.sinaldasorte.repository"
})
@EnableTransactionManagement
class PersistenceContext {
 
    @Bean
    AuditorAware<String> auditorProvider() {
        return new ContaAuditorAware();
    }
  
    @Bean
    DateTimeProvider dateTimeProvider(DateTimeService dateTimeService) {
        return new AuditingDateTimeProvider();
    }
}


package br.com.sinaldasorte.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
 
public class ContaAuditorAware implements AuditorAware<String> {
 
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
 
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.of("Anônimo");
        }
        
        if(authentication.getPrincipal() instanceof String) {
        	String anonymous = (String) authentication.getPrincipal();
        	if("anonymousUser".equals(anonymous)) return Optional.of("Anônimo");
        } else if(authentication.getPrincipal() instanceof ContaAuth) {
        	return Optional.of(((ContaAuth) authentication.getPrincipal()).getUsername());
        }
        return Optional.of("Indefinido"); 
    }
}
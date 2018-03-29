package br.com.sinaldasorte.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
 
public class UsernameAuditorAware implements AuditorAware<String> {
 
    @Override
    public String getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
 
        if (authentication == null || !authentication.isAuthenticated()) {
            return "Anônimo";
        }
        
        if(authentication.getPrincipal() instanceof String) {
        	String anonymous = (String) authentication.getPrincipal();
        	if("anonymousUser".equals(anonymous)) return "Anônimo";
        } else if(authentication.getPrincipal() instanceof ContaAuth) {
        	return ((ContaAuth) authentication.getPrincipal()).getUsername();
        }
        return "Indefinido"; 
    }
}

package br.com.sinaldasorte.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.sinaldasorte.security.ContaAuth;

public class UserService {
	
	public static ContaAuth authenticated() {
		try {
			// Retorna o usuário logado
			return (ContaAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}

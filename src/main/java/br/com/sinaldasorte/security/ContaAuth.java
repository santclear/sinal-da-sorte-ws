package br.com.sinaldasorte.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.sinaldasorte.domain.enums.Perfil;

public class ContaAuth implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String senha;
	// Perfis de usuário
	private Collection<? extends GrantedAuthority> authorities;
	
	public ContaAuth() {}
	
	public ContaAuth(Long id, String email, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		// Converte a lista de ROLES para o tipo SimpleGrantedAuthority
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}
	
	// A conta não está expirada? Retornando true, para que, por padrão, não esteja.
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// A conta não está bloqueada? Retornando true, para que, por padrão, não esteja.
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// As credenciais não estão expiradas? Retornando true, para que por padrão não esteja.
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// O usuário está ativo? Retornando true, para que por padrão não esteja.
	@Override
	public boolean isEnabled() {
		return true;
	}
}
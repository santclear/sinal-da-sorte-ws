package br.com.sinaldasorte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.repository.ContaRepository;
import br.com.sinaldasorte.security.ContaAuth;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ContaRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Conta cli = repo.findByEmail(email);
		
		if (cli == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new ContaAuth(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}
}

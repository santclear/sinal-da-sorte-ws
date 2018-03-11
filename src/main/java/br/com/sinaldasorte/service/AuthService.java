package br.com.sinaldasorte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.repository.ContaRepository;
import br.com.sinaldasorte.service.exceptions.ObjetoNaoEncontradoException;
import br.com.sinaldasorte.util.Util;

@Service
public class AuthService {

	@Autowired
	private ContaRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService service;
	
	public void sendNewPassword(String email) {
		
		Conta conta = repo.findByEmail(email);
		if (conta == null) {
			throw new ObjetoNaoEncontradoException("Email não encontrado");
		}
		
		String newPass = Util.newPassword();
		conta.setSenha(pe.encode(newPass));
		
		repo.save(conta);
		service.sendNewPasswordEmail(conta, newPass);
	}
}
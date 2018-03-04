package br.com.sinaldasorte.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.repository.ContaRepository;
import br.com.sinaldasorte.service.exceptions.ObjetoNaoEncontradoException;

@Service
public class AuthService {

	@Autowired
	private ContaRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService service;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		
		Conta conta = repo.findByEmail(email);
		if (conta == null) {
			throw new ObjetoNaoEncontradoException("Email não encontrado");
		}
		
		String newPass = newPassword();
		conta.setSenha(pe.encode(newPass));
		
		repo.save(conta);
		service.sendNewPasswordEmail(conta, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
package br.com.sinaldasorte.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Conta;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder encriptadorDeSenha;
	
	@Autowired
	private ContaService contaService;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Conta conta1 = new Conta(null, "sant@gmail.com", null, null, null, encriptadorDeSenha.encode("123"));
		
		contaService.insert(conta1);
	}
}
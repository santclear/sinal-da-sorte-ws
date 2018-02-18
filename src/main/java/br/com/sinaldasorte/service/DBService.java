package br.com.sinaldasorte.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.domain.enums.Perfil;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder encriptadorDeSenha;
	
	@Autowired
	private ContaService contaService;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Conta conta1 = new Conta(null, "santclear@gmail.com", null, null, null, encriptadorDeSenha.encode("123"));
		conta1.addPerfil(Perfil.ADMIN);
		conta1.addPerfil(Perfil.ASSINANTE);
		contaService.insert(conta1);
		
		Conta conta2 = new Conta(null, "bruna@gmail.com", null, null, null, encriptadorDeSenha.encode("123"));
		conta2.addPerfil(Perfil.ASSINANTE);
		contaService.insert(conta2);
		
		Conta conta3 = new Conta(null, "caurlson@gmail.com", null, null, null, encriptadorDeSenha.encode("123"));
		contaService.insert(conta3);
		
		Conta conta4 = new Conta(null, "1", null, null, null, encriptadorDeSenha.encode("1"));
		conta4.addPerfil(Perfil.ADMIN);
		conta4.addPerfil(Perfil.ASSINANTE);
		contaService.insert(conta4);
	}
}
package br.com.sinaldasorte.service;

import java.rmi.RemoteException;
import java.text.ParseException;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.domain.enums.Perfil;
import br.com.sinaldasorte.domain.enums.Situacoes;
import br.com.sinaldasorte.dto.ContaNewDto;
import br.com.sinaldasorte.dto.EnderecoCorreiosDto;
import br.com.sinaldasorte.dto.EnderecoDto;
import br.com.sinaldasorte.dto.UsuarioDto;

@Service
public class DBService {
	
	@Autowired
	private ContaService contaService;
	@Autowired
	private LogradouroService logradouroService;
	
	public void instantiateTestDatabase() throws ParseException, RemoteException, ServiceException {
		EnderecoCorreiosDto endc2 = logradouroService.procureCepCorreios("88053-310");

		EnderecoDto end2 = new EnderecoDto(endc2.getCep(), endc2.getLogradouro(), 
				"1","Na frente da praia",endc2.getBairro(), endc2.getCidade(), endc2.getUf());
		
		UsuarioDto usu2 = new UsuarioDto(null,
				"Sant'Clear","Costa","27716419250",
				"1983-01-13","1",end2,
				"48977337733", "", "");
		ContaNewDto cdto2 = new ContaNewDto("santclear@gmail.com","12345678", usu2);
		Conta con2 = contaService.dtoParaEntidade(cdto2);
		con2.setSituacao(Situacoes.ATIVO);
		con2.addPerfil(Perfil.ADMIN);
		con2.addPerfil(Perfil.ASSINANTE);
		contaService.insira(con2);
	}
}
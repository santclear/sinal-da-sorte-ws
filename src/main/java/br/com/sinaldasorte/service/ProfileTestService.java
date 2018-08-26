package br.com.sinaldasorte.service;

import java.rmi.RemoteException;
import java.text.ParseException;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.domain.enums.Perfil;
import br.com.sinaldasorte.dto.ContaNewDto;
import br.com.sinaldasorte.dto.EnderecoCorreiosDto;
import br.com.sinaldasorte.dto.EnderecoDto;
import br.com.sinaldasorte.dto.UsuarioDto;

@Service
public class ProfileTestService {
	
	@Autowired
	private ContaService contaService;
//	@Autowired
//	private LogradouroService logradouroService;
	
	public void instantiateTestDatabase() throws ParseException, RemoteException, ServiceException {
//		EnderecoCorreiosDto endc = logradouroService.procureCepCorreios("88053-310");

//		EnderecoDto end = new EnderecoDto(endc.getCep(), endc.getLogradouro(), 
//				"1","Na frente da praia",endc.getBairro(), endc.getCidade(), endc.getUf());
//		
		UsuarioDto usu = new UsuarioDto(null,
				"Sant'Clear","Costa","27716419250",
				"1983-01-13",1,null,
				"48977337733", "", "");
		ContaNewDto cdto = new ContaNewDto("s@s","12345678", usu);
		Conta con = contaService.dtoParaEntidade(cdto);
		con.addPerfil(Perfil.ADMIN);
		con.addPerfil(Perfil.ASSINANTE);//qKfT37Rx9v
		contaService.insira(con);
		
		UsuarioDto usu2 = new UsuarioDto(null,
				"Sinal da Sorte","Anônimo","27716419250",
				"1983-01-13",1,null,
				"48977337733", "", "");
		ContaNewDto cdto2 = new ContaNewDto("sinaldasorteanonimo@gmail.com","qKfT37Rx9v", usu2);
		Conta con2 = contaService.dtoParaEntidade(cdto2);
		con2.addPerfil(Perfil.ADMIN);
		con2.addPerfil(Perfil.ASSINANTE);//qKfT37Rx9v
		contaService.insira(con2);
	}
}
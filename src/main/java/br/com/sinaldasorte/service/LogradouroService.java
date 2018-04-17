package br.com.sinaldasorte.service;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.correios.bsb.sigep.master.bean.cliente.AtendeCliente;
import br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteServiceLocator;
import br.com.correios.bsb.sigep.master.bean.cliente.EnderecoERP;
import br.com.sinaldasorte.domain.Bairro;
import br.com.sinaldasorte.domain.Logradouro;
import br.com.sinaldasorte.dto.EnderecoCorreiosDto;
import br.com.sinaldasorte.dto.LogradouroDto;
import br.com.sinaldasorte.repository.BairroRepository;
import br.com.sinaldasorte.repository.LogradouroRepository;
import br.com.sinaldasorte.service.exceptions.CorreiosException;

@Service
public class LogradouroService {
	
	@Autowired
	private LogradouroRepository repo;
	
	@Autowired
	private BairroRepository bairroRepo;
	
	public Logradouro procure(String cep) {
		Optional<Logradouro> obj = repo.findById(cep);
		
		return obj.orElse(null);
	}
	
	public Logradouro insira(Logradouro obj) {
		obj.setCep(obj.getCep());
		repo.save(obj);
		return obj;
	}
	
	public List<Logradouro> procureTodos() {
		return repo.findAll();
	}
	
	public Logradouro dtoParaEntidade(LogradouroDto objDTO) {
		Bairro bairro = this.bairroRepo.getOne(objDTO.getBairroId());
		
		return new Logradouro(objDTO.getCep(), objDTO.getNome(), bairro);
	}
	
	public EnderecoCorreiosDto procureCepCorreios(String cep) {
		try {
			AtendeClienteServiceLocator serviceLocatorCorreios = new AtendeClienteServiceLocator();
			AtendeCliente serviceCorreios = serviceLocatorCorreios.getAtendeClientePort();
			EnderecoERP enderecoCorreios = serviceCorreios.consultaCEP(cep);
			
			String cepMask = enderecoCorreios.getCep().replace("-", "");
			String logradouro = enderecoCorreios.getEnd();
			String bairro = enderecoCorreios.getBairro();
			String cidade = enderecoCorreios.getCidade();
			String uf = enderecoCorreios.getUf();
			
			return new EnderecoCorreiosDto(cepMask, logradouro, bairro, cidade, uf);
		} catch(ServiceException | RemoteException e) {
			throw new CorreiosException("Ocorreu um erro ao tentar carregar os dados de endereço dos Correios. Tente inserir o CEP novamente.");
		}
	}
}

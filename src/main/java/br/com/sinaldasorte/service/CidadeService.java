package br.com.sinaldasorte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Cidade;
import br.com.sinaldasorte.domain.UF;
import br.com.sinaldasorte.dto.CidadeDto;
import br.com.sinaldasorte.repository.CidadeRepository;
import br.com.sinaldasorte.repository.UFRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repo;
	
	@Autowired
	private UFRepository ufRepo;
	
	public Cidade procure(Long id) {
		Cidade obj = repo.findOne(id);
		
		return obj;
	}
	
	public Cidade procure(String nome) {
		Cidade obj = repo.procureCidade(nome);
		
		return obj;
	}
	
	public Cidade insira(Cidade obj) {
		obj.setId(null);
		repo.save(obj);
		return obj;
	}
	
	public List<Cidade> procureTodos() {
		return repo.findAll();
	}
	
	public Cidade dtoParaEntidade(CidadeDto objDTO) {
		UF uf = this.ufRepo.getOne(objDTO.getUfId());
		
		return new Cidade(null, objDTO.getNome(), uf);
	}
}

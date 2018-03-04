package br.com.sinaldasorte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.UF;
import br.com.sinaldasorte.dto.UFDto;
import br.com.sinaldasorte.repository.UFRepository;

@Service
public class UFService {
	
	@Autowired
	private UFRepository repo;
	
	public UF procure(Long id) {
		UF obj = repo.findOne(id);
		
		return obj;
	}
	
	public UF procure(String nome) {
		UF obj = repo.findByNome(nome);
		
		return obj;
	}
	
	public UF insira(UF obj) {
		obj.setId(null);
		repo.save(obj);
		return obj;
	}
	
	public List<UF> procureTodos() {
		return repo.findAll();
	}
	
	public UF dtoParaEntidade(UFDto objDTO) {		
		return new UF(null, objDTO.getNome());
	}
}
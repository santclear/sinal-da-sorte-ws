package br.com.sinaldasorte.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Cidade;
import br.com.sinaldasorte.domain.UF;
import br.com.sinaldasorte.dto.CidadeDto;
import br.com.sinaldasorte.repository.CidadeRepository;
import br.com.sinaldasorte.repository.UFRepository;
import br.com.sinaldasorte.service.exceptions.ObjetoNaoEncontradoException;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repo;
	
	@Autowired
	private UFRepository ufRepo;
	
	public Cidade procure(Long id) {
		Optional<Cidade> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}
	
	public Cidade procure(String cidade, String uf) {
		Cidade obj = repo.procureCidade(cidade, uf);
		
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

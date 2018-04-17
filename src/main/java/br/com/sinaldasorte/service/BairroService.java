package br.com.sinaldasorte.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Bairro;
import br.com.sinaldasorte.domain.Cidade;
import br.com.sinaldasorte.dto.BairroDto;
import br.com.sinaldasorte.repository.BairroRepository;
import br.com.sinaldasorte.repository.CidadeRepository;
import br.com.sinaldasorte.service.exceptions.ObjetoNaoEncontradoException;

@Service
public class BairroService {
	
	@Autowired
	private BairroRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	public Bairro procure(Long id) {
		Optional<Bairro> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado! Id: " + id + ", Tipo: " + Bairro.class.getName()));
	}
	
	public Bairro procure(String bairro, String cidade, String uf) {
		Bairro obj = repo.procureBairro(bairro, cidade, uf);
		
		return obj;
	}
	
	public Bairro insira(Bairro obj) {
		obj.setId(null);
		repo.save(obj);
		return obj;
	}
	
	public List<Bairro> procureTodos() {
		return repo.findAll();
	}
	
	public Bairro dtoParaEntidade(BairroDto objDTO) {
		Cidade cidade = this.cidadeRepo.getOne(objDTO.getCidadeId());
		
		return new Bairro(null, objDTO.getNome(), cidade);
	}
}

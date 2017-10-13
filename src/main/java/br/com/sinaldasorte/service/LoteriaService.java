package br.com.sinaldasorte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Loteria;
import br.com.sinaldasorte.repository.LoteriaRepository;

@Service
public class LoteriaService {

	@Autowired
	private LoteriaRepository repo;

	public List<Loteria> procurePorIdMaiorQue(Long id) {
		return this.repo.procurePorIdMaiorQue(id);
	}
}

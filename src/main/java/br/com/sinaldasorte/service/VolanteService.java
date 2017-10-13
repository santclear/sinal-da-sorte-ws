package br.com.sinaldasorte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Volante;
import br.com.sinaldasorte.repository.VolanteRepository;

@Service
public class VolanteService {
	
	@Autowired
	private VolanteRepository repo;

	public List<Volante> findByNome(String nome) {
		return this.repo.findByNome(nome);
	}
}

package br.com.sinaldasorte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Estado;
import br.com.sinaldasorte.repository.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repo;
	
	public List<Estado> findAll() {
		return repo.findAllByOrderByNome();
	}
}
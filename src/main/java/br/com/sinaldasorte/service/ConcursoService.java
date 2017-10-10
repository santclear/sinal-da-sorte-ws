package br.com.sinaldasorte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Concurso;
import br.com.sinaldasorte.repository.ConcursoRepository;
import br.com.sinaldasorte.repository.specification.ConcursoEspecification;

@Service
public class ConcursoService {

	@Autowired
	private ConcursoRepository repository;

	public List <Concurso> findByNumero(Integer numero) {
		return repository.findAll(ConcursoEspecification.numero(numero));
	}
}

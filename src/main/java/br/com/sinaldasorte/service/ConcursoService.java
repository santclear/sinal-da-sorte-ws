package br.com.sinaldasorte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Concurso;
import br.com.sinaldasorte.repository.ConcursoRepository;

@Service
public class ConcursoService {

	@Autowired
	private ConcursoRepository repo;

	public List<Concurso> procureConcursosComNumeroMaiorQue(Integer numero, Long idLoteria) {
//		if(numero > 0) return this.repo.procureConcursosComNumeroMaiorQue(numero, idLoteria);
//		else return this.repo.procureConcursosComNumeroMenorQue(31, idLoteria);
		return this.repo.procureConcursosComNumeroMaiorQue(numero, idLoteria);
	}
	
	public List<Concurso> procureConcursosComNumeroMenorQue(Integer numero, Long idLoteria) {
		return this.repo.procureConcursosComNumeroMenorQue(numero, idLoteria);
	}
}

package br.com.sinaldasorte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Concurso;
import br.com.sinaldasorte.dto.EstatisticaDTO;
import br.com.sinaldasorte.repository.ConcursoRepository;

@Service
public class ConcursoService {

	@Autowired
	private ConcursoRepository repo;

	public List<Concurso> procurePorLoteriaIdIgualAENumeroMaiorQueESorteioNumeroIgualA(Long loteriaId, Integer numero) {
		return this.repo.procurePorLoteriaIdIgualAENumeroMaiorQueESorteioNumeroIgualA(loteriaId, numero);
	}
	
	public List<Concurso> procurePorLoteriaIdIgualAENumeroMenorQueESorteioNumeroIgualA(Long loteriaId, Integer numero) {
		return this.repo.procurePorLoteriaIdIgualAENumeroMenorQueESorteioNumeroIgualA(loteriaId, numero);
	}
	
	public List<EstatisticaDTO> calculeFrequenciasTotaisDasDezenas(Long loteriaId, Integer numeroDoSorteio) {
		return this.repo.calculeFrequenciasTotaisDasDezenas(loteriaId, numeroDoSorteio);
	}
}

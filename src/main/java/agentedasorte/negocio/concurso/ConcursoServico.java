package agentedasorte.negocio.concurso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConcursoServico {

	@Autowired
	private ConcursoRepositorio repository;

	public List <Concurso> findByNumero(Integer numero) {
		return repository.findAll(ConcursoEspecificacao.numero(numero));
	}
}

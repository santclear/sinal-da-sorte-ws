package br.com.agentedasorte.negocio.concurso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcursoRepositorio extends JpaRepository<Concurso, Long>, JpaSpecificationExecutor<Concurso> {
	
	@Query(
		"SELECT concurso "
	  + "FROM Concurso concurso "
	  + "INNER JOIN concurso.loteria loteria "
	  + "LEFT JOIN concurso.sorteios sorteio "
	  + "WHERE loteria.id = ?1 AND "
	  + "concurso.numero > ?2")
	public List<Concurso> procurePorLoteriaIdIgualAENumeroMaiorQue(Long loteriaId, Integer numero);
	
	@Query(
		"SELECT concurso "
	  + "FROM Concurso concurso "
	  + "INNER JOIN concurso.loteria loteria "
	  + "LEFT JOIN concurso.sorteios sorteio "
	  + "WHERE loteria.id = ?1 AND "
	  + "concurso.numero < ?2")
	public List<Concurso> procurePorLoteriaIdIgualAENumeroMenorQue(Long loteriaId, Integer numero);
}

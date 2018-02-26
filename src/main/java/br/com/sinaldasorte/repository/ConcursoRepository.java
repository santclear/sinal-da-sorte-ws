package br.com.sinaldasorte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sinaldasorte.domain.Concurso;
import br.com.sinaldasorte.dto.EstatisticaDto;

@Repository
public interface ConcursoRepository extends JpaRepository<Concurso, Long>, JpaSpecificationExecutor<Concurso> {
	
	@Query(
		"SELECT concurso "
	  + "FROM Concurso concurso "
	  + "INNER JOIN concurso.loteria loteria "
	  + "WHERE concurso.numero > ?1 AND "
	  + "loteria.id = ?2")
	List<Concurso> procureConcursosComNumeroMaiorQue(Integer numero, Long idLoteria);
	
	@Query(
		"SELECT concurso "
	  + "FROM Concurso concurso "
	  + "INNER JOIN concurso.loteria loteria "
	  + "WHERE concurso.numero < ?1 AND "
	  + "loteria.id = ?2")
	List<Concurso> procureConcursosComNumeroMenorQue(Integer numero, Long idLoteria);
	
	@Query(
	    "SELECT new br.com.sinaldasorte.dto.EstatisticaDto("
	  + "	dez.numero AS dezena, "
	  + "	("
	  + "		SELECT COUNT(*) "
	  + "		FROM Sorteio sort "
	  + "		JOIN sort.concurso conc "
	  + "		JOIN conc.loteria lot "
	  + "		WHERE lot.id = ?1 "
	  + "		AND sort.numero = ?2 "
	  + "		AND sort.numerosSorteados LIKE concat('%',dez.numero,'%')"
	  + "	) AS frequencia, "
	  + "	("
	  + "		SELECT (COUNT(*) * 1.0 * 100) / max(conc.numero) "
	  + "		FROM Sorteio sort "
	  + "		JOIN sort.concurso conc "
	  + "		JOIN conc.loteria lot "
	  + "		WHERE lot.id = ?1 "
	  + "		AND sort.numero = ?2 "
	  + "		AND sort.numerosSorteados LIKE concat('%',dez.numero,'%')"
	  + "	) AS frequenciaPorCento) "
	  + "FROM Dezena dez "
	  + "JOIN dez.loteria l "
	  + "WHERE l.id = ?1 "
	  + "ORDER BY frequencia DESC")
	List<EstatisticaDto> calculeFrequenciasTotaisDasDezenas(Long idLoteria, Integer numeroDoSorteio);
	
}

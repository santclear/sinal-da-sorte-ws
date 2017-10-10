package br.com.sinaldasorte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sinaldasorte.domain.Concurso;
import br.com.sinaldasorte.dto.EstatisticaDTO;

@Repository
public interface ConcursoRepository extends JpaRepository<Concurso, Long>, JpaSpecificationExecutor<Concurso> {
	
	@Query(
		"SELECT concurso "
	  + "FROM Concurso concurso "
	  + "INNER JOIN concurso.loteria loteria "
	  + "WHERE loteria.id = ?1 AND "
	  + "concurso.numero > ?2 ")
	public List<Concurso> procurePorLoteriaIdIgualAENumeroMaiorQueESorteioNumeroIgualA(Long loteriaId, Integer numero);
	
	@Query(
		"SELECT concurso "
	  + "FROM Concurso concurso "
	  + "INNER JOIN concurso.loteria loteria "
	  + "WHERE loteria.id = ?1 AND "
	  + "concurso.numero < ?2")
	public List<Concurso> procurePorLoteriaIdIgualAENumeroMenorQueESorteioNumeroIgualA(Long loteriaId, Integer numero);
	
	@Query(
	    "SELECT new br.com.sinaldasorte.dto.EstatisticaDTO("
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
	public List<EstatisticaDTO> calculeFrequenciasTotaisDasDezenas(Long loteriaId, Integer numeroDoSorteio);
	
}

package br.com.agentedasorte.negocio.concurso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.agentedasorte.negocio.dto.EstatisticaDTO;

@Repository
public interface ConcursoRepositorio extends JpaRepository<Concurso, Long>, JpaSpecificationExecutor<Concurso> {
	
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
	    "SELECT new br.com.agentedasorte.negocio.dto.EstatisticaDTO("
	  + "	c.numero AS dezena, "
	  + "	("
	  + "		SELECT COUNT(*) "
	  + "		FROM Sorteio sort "
	  + "		JOIN sort.concurso conc "
	  + "		JOIN conc.loteria lot "
	  + "		WHERE lot.id = ?1 "
	  + "		AND sort.numerosSorteados LIKE concat('%', "
	  + "				CASE WHEN c.numero >= 1 AND c.numero <= 9 "
	  + "					THEN concat('0', c.numero) "
	  + "					ELSE c.numero "
	  + "				END ,"
	  + "			'%')"
	  + "	) AS frequencia, "
	  + "	("
	  + "		SELECT (COUNT(*) * 1.0 * 100) / max(conc.numero) "
	  + "		FROM Sorteio sort "
	  + "		JOIN sort.concurso conc "
	  + "		JOIN conc.loteria lot "
	  + "		WHERE lot.id = ?1 "
	  + "		AND sort.numerosSorteados LIKE concat('%', "
	  + "				CASE WHEN c.numero >= 1 AND c.numero <= 9 "
	  + "					THEN concat('0', c.numero) "
	  + "					ELSE c.numero "
	  + "				END ,"
	  + "			'%')"
	  + "	) AS frequenciaPorCento) "
	  + "FROM Concurso c "
	  + "JOIN c.loteria l "
	  + "WHERE l.id = ?1 "
	  + "AND c.numero >= ?2 "
	  + "AND c.numero <= ?3 "
	  + "ORDER BY frequencia DESC")
	public List<EstatisticaDTO> calculeFrequenciasTotaisDasDezenas(Long loteriaId, Integer numeroConcursoInicial, Integer numeroConcursoFinal);
	
}

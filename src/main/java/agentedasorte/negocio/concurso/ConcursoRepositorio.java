package agentedasorte.negocio.concurso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcursoRepositorio extends JpaRepository<Concurso, Long>, JpaSpecificationExecutor<Concurso> {
	
	@Query("SELECT concurso, loteria FROM Concurso concurso inner join concurso.loteria loteria WHERE loteria.id = ?1 AND concurso.numero > ?2")
	List<Concurso> procurePorLoteriaIdIgualAoENumeroMaiorQue(Long loteriaId, Integer numero);
	
//	@Query("SELECT concurso, loteria FROM Concurso concurso inner join concurso.loteria loteria WHERE concurso.numero < ?1 AND loteria.nome like ?2")
//	List<Concurso> procurePorNumeroMenorQueELoteriaNomeComo(Integer numero, String loteriaNome);
//	
//	@Query("select c, l.nome from Concurso c join fetch c.loteria l where c.numerosSorteados like ?1 and l.nome = ?2 and c.numero >= ?3 and c.numero <= ?4")
//	List<Concurso> procurePorNumerosSorteadosComoELoteriaNomeENumeroMaiorqueENumeroMenorque(String numeroSorteado, String nomeLoteria, Integer numeroConcursoInicial, Integer numeroConcursoFinal);

}

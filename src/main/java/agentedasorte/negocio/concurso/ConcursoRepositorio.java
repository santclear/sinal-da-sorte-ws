package agentedasorte.negocio.concurso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcursoRepositorio extends JpaRepository<Concurso, Long>, JpaSpecificationExecutor<Concurso> {

//	@Query("select c from Concurso c inner join c.loteria l where numero = :numero")
//	List<Concurso> findByNumero(@Param("numero") Integer numero);

//	@Query("select c.id as _id, c.numero, c.numerosSorteados, l.nome from Concurso c join fetch c.loteria l where c.numero = ?1")
//	List<Concurso> listarTodos();
	
//	@Query("select c, l from Concurso c inner join c.loteria l where c.id <= 1")
//	List<Concurso> listarTodos();
	
	@Query("SELECT concurso, loteria FROM Concurso concurso inner join concurso.loteria loteria WHERE concurso.numero > ?1 AND loteria.nome like ?2")
	List<Concurso> procurePorNumeroMaiorQueELoteriaNomeComo(Integer numero, String loteriaNome);
	
//	@Query("SELECT concurso, loteria FROM Concurso concurso inner join concurso.loteria loteria WHERE concurso.id < ?1")
	@Query("SELECT concurso, loteria FROM Concurso concurso inner join concurso.loteria loteria WHERE concurso.numero < ?1 AND loteria.nome like ?2")
	List<Concurso> procurePorNumeroMenorQueELoteriaNomeComo(Integer numero, String loteriaNome);
	
	@Query("select c, l.nome from Concurso c join fetch c.loteria l where c.numerosSorteados like ?1 and l.nome = ?2 and c.numero >= ?3 and c.numero <= ?4")
	List<Concurso> procurePorNumerosSorteadosComoELoteriaNomeENumeroMaiorqueENumeroMenorque(String numeroSorteado, String nomeLoteria, Integer numeroConcursoInicial, Integer numeroConcursoFinal);
	
//	@Query("select c from Concurso c where c.numero like ?1")
//	List<Concurso> procure(Integer numero);
}

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

	
	@Query("select c, l.nome from Concurso c join fetch c.loteria l where c.numero = ?1")
	List<Concurso> procure(Integer numero);
	
//	@Query("select c from Concurso c where c.numero like ?1")
//	List<Concurso> procure(Integer numero);
}

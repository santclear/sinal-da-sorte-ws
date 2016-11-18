package br.com.agentedasorte.negocio.loteria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteriaRepositorio extends JpaRepository<Loteria, Long>, JpaSpecificationExecutor<Loteria> {

	@Query("FROM Loteria WHERE id > ?1")
	List<Loteria> procurePorIdMaiorQue(Long id);

}

package br.com.sinaldasorte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sinaldasorte.domain.Sorteio;

@Repository
public interface SorteioRepository extends JpaRepository<Sorteio, Long>, JpaSpecificationExecutor<Sorteio> {
	
	@Query("FROM Sorteio sorteio inner join sorteio.concurso concurso WHERE concurso.id = ?1 AND sorteio.numero = ?2")
	public Sorteio procurePorConcursoIdIgualAo(Long concursoId, Integer numero);
}

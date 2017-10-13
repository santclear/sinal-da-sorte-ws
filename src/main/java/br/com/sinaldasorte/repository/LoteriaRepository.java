package br.com.sinaldasorte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sinaldasorte.domain.Loteria;

@Repository
public interface LoteriaRepository extends JpaRepository<Loteria, Long> {

	@Query("FROM Loteria WHERE id > ?1")
	public List<Loteria> procurePorIdMaiorQue(Long id);

}

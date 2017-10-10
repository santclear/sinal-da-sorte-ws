package br.com.sinaldasorte.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sinaldasorte.domain.Volante;

@Repository
public interface VolanteRepository extends JpaRepository<Volante, Long>, JpaSpecificationExecutor<Volante> {

	@Query("select v from Volante v where v.dezenasMarcadas like ?1")
	List<Volante> findByNome(String nome);
}

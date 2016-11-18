package br.com.agentedasorte.negocio.volante;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VolanteRepositorio extends JpaRepository<Volante, Long>, JpaSpecificationExecutor<Volante> {

	@Query("select v from Volante v where v.dezenasMarcadas like ?1")
	List<Volante> findByNome(String nome);
}

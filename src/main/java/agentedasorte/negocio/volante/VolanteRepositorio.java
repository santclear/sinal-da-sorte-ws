package agentedasorte.negocio.volante;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VolanteRepositorio extends JpaRepository<Volante, Long>, JpaSpecificationExecutor<Volante> {

	@Query("select c from Volante c where c.nome like ?1")
	List<Volante> findByNome(String nome);
}

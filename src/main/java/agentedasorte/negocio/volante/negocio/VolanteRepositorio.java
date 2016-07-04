package agentedasorte.negocio.volante.negocio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VolanteRepositorio extends JpaRepository<Volante, Long>, JpaSpecificationExecutor<Volante> {

	@Query("select c from Volante c where c.nome like ?1")
	List<Volante> findByNome(String nome);
//
//	@Query("select c from Volante c where c.sobrenome like ?1")
//	List<Volante> findBySobrenome(String sobrenome);
//
//	@Query("select c from Volante c where c.idade >= 18")
//	List<Volante> findByMaioridade();
//
//	@Query("select c from Volante c where c.sexo like ?1")
//	List<Volante> findBySexo(String sexo);
//
//	@Query("select c from Volante c where c.sexo like ?1 and c.idade >= 18")
//	List<Volante> findBySexoMaioridade(String sexo);
}

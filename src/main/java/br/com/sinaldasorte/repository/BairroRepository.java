package br.com.sinaldasorte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sinaldasorte.domain.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {

	@Transactional(readOnly=true) // Define que não haverá transação com o banco de dados
	@Query("SELECT b FROM Bairro b "
			+ "LEFT JOIN b.cidade c "
			+ "LEFT JOIN c.uf u "
			+ "WHERE b.nome = :bairro "
			+ "AND c.nome = :cidade "
			+ "AND u.nome = :uf")
	Bairro procureBairro(@Param("bairro") String bairro, @Param("cidade") String cidade, @Param("uf") String uf);
}

package br.com.sinaldasorte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sinaldasorte.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	@Transactional(readOnly=true) // Define que não haverá transação com o banco de dados
	@Query("SELECT c FROM Cidade c LEFT JOIN c.uf u WHERE c.nome = :nome")
	Cidade procureCidade(@Param("nome") String nome);
}

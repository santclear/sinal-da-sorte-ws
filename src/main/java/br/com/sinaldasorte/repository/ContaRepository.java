package br.com.sinaldasorte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sinaldasorte.domain.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
	
	@Transactional(readOnly=true) // Define que não haverá transação com o banco de dados
	Conta findByEmail(String email);
}
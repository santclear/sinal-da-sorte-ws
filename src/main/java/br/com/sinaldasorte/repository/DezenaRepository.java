package br.com.sinaldasorte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sinaldasorte.domain.Dezena;

@Repository
public interface DezenaRepository extends JpaRepository<Dezena, Long> {
	
	
}

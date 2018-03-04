package br.com.sinaldasorte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sinaldasorte.domain.UF;

@Repository
public interface UFRepository extends JpaRepository<UF, Long> {

	@Transactional(readOnly=true)
	UF findByNome(String nome);
}

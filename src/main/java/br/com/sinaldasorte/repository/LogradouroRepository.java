package br.com.sinaldasorte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sinaldasorte.domain.Logradouro;

@Repository
public interface LogradouroRepository extends JpaRepository<Logradouro, String> {

}

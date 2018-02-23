package br.com.sinaldasorte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sinaldasorte.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Transactional(readOnly=true) // Define que não haverá transação com o banco de dados
	Usuario findById(Long id);
}
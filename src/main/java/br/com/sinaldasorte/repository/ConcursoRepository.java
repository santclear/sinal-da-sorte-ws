package br.com.sinaldasorte.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.sinaldasorte.domain.Concurso;

@Repository
public interface ConcursoRepository extends JpaRepository<Concurso, Long>, JpaSpecificationExecutor<Concurso> {
	
	@Transactional(readOnly=true, propagation = Propagation.SUPPORTS)
	@Cacheable("procureConcursosComNumeroMaiorQue")
	@Query(
		"SELECT concurso "
	  + "FROM Concurso concurso "
	  + "INNER JOIN concurso.loteria loteria "
	  + "WHERE concurso.numero > ?1 AND "
	  + "loteria.id = ?2")
	List<Concurso> procureConcursosComNumeroMaiorQue(Integer numero, Long idLoteria);
	
//	@Transactional(readOnly=true, propagation = Propagation.SUPPORTS)
//	@Query(
//		"SELECT concurso "
//	  + "FROM Concurso concurso "
//	  + "INNER JOIN concurso.loteria loteria "
//	  + "WHERE concurso.numero < ?1 AND "
//	  + "loteria.id = ?2")
//	List<Concurso> procureConcursosComNumeroMenorQue(Integer numero, Long idLoteria);	
}

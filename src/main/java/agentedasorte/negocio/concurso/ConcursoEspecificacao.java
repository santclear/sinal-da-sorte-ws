package agentedasorte.negocio.concurso;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ConcursoEspecificacao {
	
	public static Specification<Concurso> numero(Integer numero) {
		return new Specification<Concurso>() {
			
			@Override
			public Predicate toPredicate(Root<Concurso> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				return cb.equal(root.get("numero"), numero);
			}
			
		};
	}

}

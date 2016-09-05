package agentedasorte.negocio.volante;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class VolanteEspecificacao {
	
	public static Specification<Volante> nome(String nome) {
		return new Specification<Volante>() {
			
			@Override
			public Predicate toPredicate(Root<Volante> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				return cb.like(root.get("nome"), nome);
			}
			
		};
	}
//
//    public static Specification<Volante> maioridade() {
//        return new Specification<Volante>() {
//
//			@Override
//			public Predicate toPredicate(Root<Volante> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//				
//				return cb.greaterThanOrEqualTo(root.get("idade"), 18);
//			}
//        	
//		};
//    }
//
//    public static Specification<Volante> sexo(String sexo) {
//    	
//    	return new Specification<Volante>() {
//
//			@Override
//			public Predicate toPredicate(Root<Volante> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//				
//				return cb.like(root.get("sexo"), sexo);
//			}
//        	
//		};
//    }
//
//
//    public static Specification<Volante> sobrenome(String sobrenome) {
//    	return new Specification<Volante>() {
//
//			@Override
//			public Predicate toPredicate(Root<Volante> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//				
//				return cb.like(root.get("sobrenome"), sobrenome);
//			}
//        	
//		};
//    }
//    
//    public static Specification<Volante> idade(Integer idade) {
//    	return new Specification<Volante>() {
//
//			@Override
//			public Predicate toPredicate(Root<Volante> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//				
//				query.orderBy(
//						cb.asc(root.get("nome")), cb.asc(root.get("sobrenome"))
//				);
//				
//				return cb.equal(root.get("idade"), idade);
//			}
//        	
//		};
//    }
}

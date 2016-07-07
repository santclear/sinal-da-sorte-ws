package agentedasorte.negocio.volante.negocio.volante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolanteServico {

	@Autowired
	private VolanteRepositorio repository;

//	public List<Volante> findBySexoMaioridade(String sexo) {
//		return repository.findAll(Specifications.where(VolanteEspecificacao.sexo(sexo)).and(VolanteEspecificacao.maioridade()));
//	}
//
//	public long findByGenero(String sexo) {
//		return repository.count(VolanteEspecificacao.sexo(sexo));
//	}
//
//	public Volante findByNomeCompleto(String nome, String sobrenome) {
//		return repository.findOne(Specifications.where(VolanteEspecificacao.nome(nome)).and(VolanteEspecificacao.sobrenome(sobrenome)));
//	}
//
//	public List<Volante> findByMenoridadeAndGenero(String sexo) {
//		return repository.findAll(Specifications.not(VolanteEspecificacao.maioridade()).and(VolanteEspecificacao.sexo(sexo)));
//	}
//
//	public List<Volante> findByIdade(int idade) {
//		return repository.findAll(VolanteEspecificacao.idade(idade));
//	}
}

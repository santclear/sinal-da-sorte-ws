package br.com.sinaldasorte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.repository.VolanteRepository;

@Service
public class VolanteService {

	@Autowired
	private VolanteRepository repository;

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

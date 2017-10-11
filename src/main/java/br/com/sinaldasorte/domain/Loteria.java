package br.com.sinaldasorte.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Loteria extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 1L;

	@Column(length = 20)
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "loteria")
	private List<Concurso> concursos = new LinkedList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "loteria")
	private List<Volante> volantes = new LinkedList<>();

	public Loteria() {}

	public Loteria(String nome) {
		this.nome = nome;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Concurso> getConcursos() {
		return concursos;
	}

	public void setConcursos(List<Concurso> concursos) {
		this.concursos = concursos;
	}

	public List<Volante> getVolantes() {
		return volantes;
	}

	public void setVolantes(List<Volante> volantes) {
		this.volantes = volantes;
	}
}

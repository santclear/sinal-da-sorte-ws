package br.com.sinaldasorte.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Loteria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(length = 20)
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "loteria")
	private List<Concurso> concursos = new LinkedList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "loteria")
	private List<Volante> volantes = new LinkedList<>();

	public Loteria() {}

	public Loteria(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loteria other = (Loteria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

package br.com.agentedasorte.negocio.loteria;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.agentedasorte.negocio.concurso.Concurso;
import br.com.agentedasorte.negocio.volante.Volante;

@Entity
@Table(name = "loteria", catalog = "bdagente")
public class Loteria extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 5002731677180850999L;

	@Column(name = "nome", length = 20)
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "loteria")
	private List<Concurso> concursos;
	
	@JsonIgnore
	@OneToMany(mappedBy = "loteria")
	private List<Volante> volantes;

	public Loteria() {
	}

	public Loteria(String nome, List<Concurso> concursos, List<Volante> volantes) {
		super();
		this.nome = nome;
		this.concursos = concursos;
		this.volantes = volantes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((concursos == null) ? 0 : concursos.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((volantes == null) ? 0 : volantes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loteria other = (Loteria) obj;
		if (concursos == null) {
			if (other.concursos != null)
				return false;
		} else if (!concursos.equals(other.concursos))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (volantes == null) {
			if (other.volantes != null)
				return false;
		} else if (!volantes.equals(other.volantes))
			return false;
		return true;
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

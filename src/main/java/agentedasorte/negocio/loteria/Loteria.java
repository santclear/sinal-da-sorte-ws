package agentedasorte.negocio.loteria;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import agentedasorte.negocio.concurso.Concurso;

@Entity
@Table(name = "loteria", catalog = "bdagente")
public class Loteria extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 4798417603243979594L;

	@Column(name = "nome", length = 20)
	private String nome;
	
	@JsonIgnore
	@OneToMany
	private List<Concurso> concursos;

	public Loteria() {
	}

	public Loteria(String nome, List<Concurso> concursos) {
		this.nome = nome;
		this.concursos = concursos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((concursos == null) ? 0 : concursos.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Loteria))
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
		return true;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Concurso> getConcursos() {
		return this.concursos;
	}

	public void setConcursos(List<Concurso> concursos) {
		this.concursos = concursos;
	}
}

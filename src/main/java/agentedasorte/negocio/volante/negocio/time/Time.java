package agentedasorte.negocio.volante.negocio.time;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import agentedasorte.negocio.volante.negocio.concurso.Concurso;
import agentedasorte.negocio.volante.negocio.volante.Volante;

@Entity
@Table(name = "time", catalog = "bdagente")
public class Time implements java.io.Serializable {

	private Integer id;
	private String nome;
	private Set<Concurso> concursos = new HashSet<Concurso>(0);
	private Set<Volante> volantes = new HashSet<Volante>(0);

	public Time() {
	}

	public Time(String nome, Set<Concurso> concursos, Set volantes) {
		this.nome = nome;
		this.concursos = concursos;
		this.volantes = volantes;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((concursos == null) ? 0 : concursos.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((volantes == null) ? 0 : volantes.hashCode());
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
		Time other = (Time) obj;
		if (concursos == null) {
			if (other.concursos != null)
				return false;
		} else if (!concursos.equals(other.concursos))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nome", length = 60)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "time")
	public Set<Concurso> getConcursos() {
		return this.concursos;
	}

	public void setConcursos(Set<Concurso> concursos) {
		this.concursos = concursos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "time")
	public Set<Volante> getVolantes() {
		return this.volantes;
	}

	public void setVolantes(Set<Volante> volantes) {
		this.volantes = volantes;
	}

}

package br.com.sinaldasorte.domain;

import java.util.List;

import javax.persistence.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "acesso", catalog = "bdagente")
public class Acesso extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -7484015907876279087L;

	@Column(name = "tipo", nullable = false, length = 10)
	private String tipo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "acesso")
	private List<Conta> contas;

	public Acesso() {
	}

	public Acesso(String tipo) {
		this.tipo = tipo;
	}

	public Acesso(String tipo, List<Conta> contas) {
		this.tipo = tipo;
		this.contas = contas;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((contas == null) ? 0 : contas.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Acesso))
			return false;
		Acesso other = (Acesso) obj;
		if (contas == null) {
			if (other.contas != null)
				return false;
		} else if (!contas.equals(other.contas))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Conta> getContas() {
		return this.contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
}

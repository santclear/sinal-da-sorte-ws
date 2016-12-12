package br.com.agentedasorte.negocio.dezena;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.agentedasorte.negocio.loteria.Loteria;

@Entity
@Table(name = "dezena", catalog = "bdagente")
public class Dezena extends AbstractPersistable<Long> {
	private static final long serialVersionUID = 6042111376337839786L;

	@ManyToOne
	@JoinColumn(name = "loteria_id", nullable = false)
	private Loteria loteria;

	@Column(name = "numero", length = 4)
	private String numero;

	public Dezena() {
	}

	public Dezena(Loteria loteria, String numero) {
		super();
		this.loteria = loteria;
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((loteria == null) ? 0 : loteria.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Dezena other = (Dezena) obj;
		if (loteria == null) {
			if (other.loteria != null)
				return false;
		} else if (!loteria.equals(other.loteria))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public Loteria getLoteria() {
		return loteria;
	}

	public void setLoteria(Loteria loteria) {
		this.loteria = loteria;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}

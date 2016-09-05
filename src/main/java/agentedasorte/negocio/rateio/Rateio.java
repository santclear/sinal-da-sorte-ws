package agentedasorte.negocio.rateio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import agentedasorte.negocio.concurso.Concurso;

@Entity
@Table(name = "rateio", catalog = "bdagente")
public class Rateio extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -936987954898554377L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "concurso_id", nullable = false)
	private Concurso concurso;

	@Column(name = "rateio", nullable = false, precision = 15, scale = 2)
	private BigDecimal rateio;

	@Column(name = "numero_de_ganhadores", nullable = false)
	private int numeroDeGanhadores;

	public Rateio() {
	}

	public Rateio(Concurso concurso, BigDecimal rateio, int numeroDeGanhadores) {
		this.concurso = concurso;
		this.rateio = rateio;
		this.numeroDeGanhadores = numeroDeGanhadores;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((concurso == null) ? 0 : concurso.hashCode());
		result = prime * result + numeroDeGanhadores;
		result = prime * result + ((rateio == null) ? 0 : rateio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Rateio))
			return false;
		Rateio other = (Rateio) obj;
		if (concurso == null) {
			if (other.concurso != null)
				return false;
		} else if (!concurso.equals(other.concurso))
			return false;
		if (numeroDeGanhadores != other.numeroDeGanhadores)
			return false;
		if (rateio == null) {
			if (other.rateio != null)
				return false;
		} else if (!rateio.equals(other.rateio))
			return false;
		return true;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public Concurso getConcurso() {
		return this.concurso;
	}

	public void setConcurso(Concurso concurso) {
		this.concurso = concurso;
	}

	public BigDecimal getRateio() {
		return this.rateio;
	}

	public void setRateio(BigDecimal rateio) {
		this.rateio = rateio;
	}

	public int getNumeroDeGanhadores() {
		return this.numeroDeGanhadores;
	}

	public void setNumeroDeGanhadores(int numeroDeGanhadores) {
		this.numeroDeGanhadores = numeroDeGanhadores;
	}
}

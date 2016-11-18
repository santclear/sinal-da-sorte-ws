package br.com.agentedasorte.negocio.rateio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.agentedasorte.negocio.sorteio.Sorteio;

@Entity
@Table(name = "rateio", catalog = "bdagente")
public class Rateio extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 5648673744764754855L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "sorteio_id", nullable = false)
	private Sorteio sorteio;

	@Column(name = "rateio", nullable = false, precision = 15, scale = 2)
	private BigDecimal rateio;

	@Column(name = "numero_de_ganhadores", nullable = false)
	private int numeroDeGanhadores;
	
	@Column(name = "acumulado_para_o_proximo_concurso", precision = 15, scale = 2)
	private BigDecimal acumuladoParaOProximoConcurso;
	
	@Column(name = "tipo_de_premio", nullable = false, length = 45)
	private String tipoDePremio;
	
	public Rateio() {
	}

	public Rateio(Sorteio sorteio, BigDecimal rateio, int numeroDeGanhadores, BigDecimal acumuladoParaOProximoConcurso, String tipoDePremio) {
		this.sorteio = sorteio;
		this.rateio = rateio;
		this.numeroDeGanhadores = numeroDeGanhadores;
		this.acumuladoParaOProximoConcurso = acumuladoParaOProximoConcurso;
		this.tipoDePremio = tipoDePremio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((acumuladoParaOProximoConcurso == null) ? 0 : acumuladoParaOProximoConcurso.hashCode());
		result = prime * result + numeroDeGanhadores;
		result = prime * result + ((rateio == null) ? 0 : rateio.hashCode());
		result = prime * result + ((sorteio == null) ? 0 : sorteio.hashCode());
		result = prime * result + ((tipoDePremio == null) ? 0 : tipoDePremio.hashCode());
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
		Rateio other = (Rateio) obj;
		if (acumuladoParaOProximoConcurso == null) {
			if (other.acumuladoParaOProximoConcurso != null)
				return false;
		} else if (!acumuladoParaOProximoConcurso.equals(other.acumuladoParaOProximoConcurso))
			return false;
		if (numeroDeGanhadores != other.numeroDeGanhadores)
			return false;
		if (rateio == null) {
			if (other.rateio != null)
				return false;
		} else if (!rateio.equals(other.rateio))
			return false;
		if (sorteio == null) {
			if (other.sorteio != null)
				return false;
		} else if (!sorteio.equals(other.sorteio))
			return false;
		if (tipoDePremio == null) {
			if (other.tipoDePremio != null)
				return false;
		} else if (!tipoDePremio.equals(other.tipoDePremio))
			return false;
		return true;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public Sorteio getSorteio() {
		return sorteio;
	}

	public void setSorteio(Sorteio sorteio) {
		this.sorteio = sorteio;
	}

	public BigDecimal getRateio() {
		return rateio;
	}

	public void setRateio(BigDecimal rateio) {
		this.rateio = rateio;
	}

	public int getNumeroDeGanhadores() {
		return numeroDeGanhadores;
	}

	public void setNumeroDeGanhadores(int numeroDeGanhadores) {
		this.numeroDeGanhadores = numeroDeGanhadores;
	}

	public BigDecimal getAcumuladoParaOProximoConcurso() {
		return acumuladoParaOProximoConcurso;
	}

	public void setAcumuladoParaOProximoConcurso(BigDecimal acumuladoParaOProximoConcurso) {
		this.acumuladoParaOProximoConcurso = acumuladoParaOProximoConcurso;
	}

	public String getTipoDePremio() {
		return tipoDePremio;
	}

	public void setTipoDePremio(String tipoDePremio) {
		this.tipoDePremio = tipoDePremio;
	}
}

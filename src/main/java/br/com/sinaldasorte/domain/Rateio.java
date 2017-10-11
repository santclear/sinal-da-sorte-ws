package br.com.sinaldasorte.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Rateio extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Sorteio sorteio;

	@Column(nullable = false, precision = 15, scale = 2)
	private BigDecimal rateio;

	@Column(nullable = false)
	private int numeroDeGanhadores;
	
	@Column(precision = 15, scale = 2)
	private BigDecimal acumuladoParaOProximoConcurso;
	
	@Column(nullable = false, length = 45)
	private String tipoDePremio;
	
	public Rateio() {}

	public Rateio(Sorteio sorteio, BigDecimal rateio, int numeroDeGanhadores, BigDecimal acumuladoParaOProximoConcurso, String tipoDePremio) {
		this.sorteio = sorteio;
		this.rateio = rateio;
		this.numeroDeGanhadores = numeroDeGanhadores;
		this.acumuladoParaOProximoConcurso = acumuladoParaOProximoConcurso;
		this.tipoDePremio = tipoDePremio;
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

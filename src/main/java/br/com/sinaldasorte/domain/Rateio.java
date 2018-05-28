package br.com.sinaldasorte.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.sinaldasorte.dto.QtdCidadeUfDto;

@Entity
public class Rateio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
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
	
	@JsonIgnore
	@Column(length = 10000)
	private String cidades;
	
	@JsonIgnore
	@Column(length = 10000)
	private String ufs;
	
	public Rateio() {}

	public Rateio(Long id, Sorteio sorteio, BigDecimal rateio, 
			int numeroDeGanhadores, BigDecimal acumuladoParaOProximoConcurso, 
			String tipoDePremio, String cidades, String ufs) {
		super();
		this.id = id;
		this.sorteio = sorteio;
		this.rateio = rateio;
		this.numeroDeGanhadores = numeroDeGanhadores;
		this.acumuladoParaOProximoConcurso = acumuladoParaOProximoConcurso;
		this.tipoDePremio = tipoDePremio;
		this.cidades = cidades;
		this.ufs = ufs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCidades() {
		return cidades;
	}

	public void setCidades(String cidades) {
		this.cidades = cidades;
	}

	public String getUfs() {
		return ufs;
	}

	public void setUfs(String ufs) {
		this.ufs = ufs;
	}

	@Transient
	public List<QtdCidadeUfDto> getQtdCidadesUfs() {
		List<QtdCidadeUfDto> qtdCidadesUfs = new ArrayList<>();
		if(cidades != null) {
			String cidades[] = this.cidades.split(";");
			String ufs[] = this.ufs.split(";");
			Map<String, Integer> qtdCidadesUfsMap = new HashMap<>();
			for(int i = 0; i < cidades.length; i++) {
				Integer qtd = qtdCidadesUfsMap.get(cidades[i].toUpperCase()+"/"+ufs[i].toUpperCase());
				if(qtdCidadesUfsMap.get(cidades[i].toUpperCase()) == null) qtdCidadesUfsMap.put(cidades[i].toUpperCase()+"/"+ufs[i].toUpperCase(), 1);
				else qtdCidadesUfsMap.put(cidades[i].toUpperCase()+"/"+ufs[i].toUpperCase(), qtd + 1);
			}
			qtdCidadesUfsMap.forEach((cidadeUf,qtd)-> qtdCidadesUfs.add(new QtdCidadeUfDto(cidadeUf, qtd)));
		} else if(cidades == null && ufs != null) {
			String ufs[] = this.ufs.split(";");
			Map<String, Integer> qtdCidadesUfsMap = new HashMap<>();
			for(int i = 0; i < ufs.length; i++) {
				Integer qtd = qtdCidadesUfsMap.get(ufs[i].toUpperCase());
				if(qtdCidadesUfsMap.get(ufs[i].toUpperCase()) == null) qtdCidadesUfsMap.put(ufs[i].toUpperCase(), 1);
				else qtdCidadesUfsMap.put(ufs[i].toUpperCase(), qtd + 1);
			}
			qtdCidadesUfsMap.forEach((cidadeUf,qtd)-> qtdCidadesUfs.add(new QtdCidadeUfDto(cidadeUf, qtd)));
		}
		return qtdCidadesUfs;
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
		Rateio other = (Rateio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

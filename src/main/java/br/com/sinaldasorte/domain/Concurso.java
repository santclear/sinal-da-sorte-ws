package br.com.sinaldasorte.domain;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Concurso extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Loteria loteria;
	
	@Column(nullable = false)
	private int numero;
	
	@Temporal(TemporalType.DATE)
	@Column(length = 10)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEEE, dd/MM/yyyy", locale = "pt", timezone = "Brazil/East")
	private Calendar dataDoSorteio;
	
	@Column(precision = 15, scale = 2)
	private BigDecimal arrecadacaoTotal;
	
	@Column(length = 2048)
	private String cidade;
	
	@Column(length = 2048)
	private String uf;
	
	@Column(precision = 15, scale = 2)
	private BigDecimal estimativaDePremioParaOProximoConcurso;
	
	@Column(precision = 15, scale = 2)
	private BigDecimal acumuladoEspecial;
	
	@OneToMany(mappedBy = "concurso")
	private List<Sorteio> sorteios = new LinkedList<>();
	
	public Concurso() {}
	
	public Concurso(Loteria loteria, int numero, Calendar dataDoSorteio, BigDecimal arrecadacaoTotal, String cidade, String uf,
		BigDecimal estimativaDePremioParaOProximoConcurso, BigDecimal acumuladoEspecial) {
		this.loteria = loteria;
		this.numero = numero;
		this.dataDoSorteio = dataDoSorteio;
		this.arrecadacaoTotal = arrecadacaoTotal;
		this.cidade = cidade;
		this.uf = uf;
		this.estimativaDePremioParaOProximoConcurso = estimativaDePremioParaOProximoConcurso;
		this.acumuladoEspecial = acumuladoEspecial;
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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Calendar getDataDoSorteio() {
		return dataDoSorteio;
	}

	public void setDataDoSorteio(Calendar dataDoSorteio) {
		this.dataDoSorteio = dataDoSorteio;
	}

	public BigDecimal getArrecadacaoTotal() {
		return arrecadacaoTotal;
	}

	public void setArrecadacaoTotal(BigDecimal arrecadacaoTotal) {
		this.arrecadacaoTotal = arrecadacaoTotal;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public BigDecimal getEstimativaDePremioParaOProximoConcurso() {
		return estimativaDePremioParaOProximoConcurso;
	}

	public void setEstimativaDePremioParaOProximoConcurso(BigDecimal estimativaDePremioParaOProximoConcurso) {
		this.estimativaDePremioParaOProximoConcurso = estimativaDePremioParaOProximoConcurso;
	}

	public BigDecimal getAcumuladoEspecial() {
		return acumuladoEspecial;
	}

	public void setAcumuladoEspecial(BigDecimal acumuladoEspecial) {
		this.acumuladoEspecial = acumuladoEspecial;
	}

	public List<Sorteio> getSorteios() {
		return sorteios;
	}

	public void setSorteios(List<Sorteio> sorteios) {
		this.sorteios = sorteios;
	}
		
	public void addSorteio(Sorteio sorteio) {
		sorteio.setConcurso(this);
		this.sorteios.add(sorteio);
	}
}

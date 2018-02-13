package br.com.sinaldasorte.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Sorteio extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Concurso concurso;
	
	private int numero;
	
	@Column(nullable = false, length = 199)
	private String numerosSorteados;
	
	// Se excluir um sorteio excluirá os rateios relacionados
	@OneToMany(mappedBy = "sorteio", cascade=CascadeType.ALL)
	private List<Rateio> rateios = new LinkedList<>();
	
	public Sorteio() {}
	
	public Sorteio(Concurso concurso, int numero, String numerosSorteados) {
		this.concurso = concurso;
		this.numero = numero;
		this.numerosSorteados = numerosSorteados;
	}
	
	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public Concurso getConcurso() {
		return concurso;
	}

	public void setConcurso(Concurso concurso) {
		this.concurso = concurso;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNumerosSorteados() {
		return numerosSorteados;
	}

	public void setNumerosSorteados(String numerosSorteados) {
		this.numerosSorteados = numerosSorteados;
	}

	public List<Rateio> getRateios() {
		return rateios;
	}

	public void setRateios(List<Rateio> rateios) {
		this.rateios = rateios;
	}
}

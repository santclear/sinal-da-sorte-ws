package br.com.sinaldasorte.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Volante extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Conta conta;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Loteria loteria;

	@Column(nullable = false, length = 99)
	private String dezenasMarcadas;

	@Column(nullable = false)
	private int quantidadeDeDezenasNoVolante;

	@Column(name = "teimosinha")
	private Integer teimosinha;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false, length = 10)
	private Date dataDaAposta;

	public Volante() {}

	public Volante(Conta conta, Loteria loteria, String dezenasMarcadas, int quantidadeDeDezenasNoVolante,
			Integer teimosinha, Date dataDaAposta) {
		this.conta = conta;
		this.loteria = loteria;
		this.dezenasMarcadas = dezenasMarcadas;
		this.quantidadeDeDezenasNoVolante = quantidadeDeDezenasNoVolante;
		this.teimosinha = teimosinha;
		this.dataDaAposta = dataDaAposta;
	}
	
	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Loteria getLoteria() {
		return loteria;
	}

	public void setLoteria(Loteria loteria) {
		this.loteria = loteria;
	}

	public String getDezenasMarcadas() {
		return dezenasMarcadas;
	}

	public void setDezenasMarcadas(String dezenasMarcadas) {
		this.dezenasMarcadas = dezenasMarcadas;
	}

	public int getQuantidadeDeDezenasNoVolante() {
		return quantidadeDeDezenasNoVolante;
	}

	public void setQuantidadeDeDezenasNoVolante(int quantidadeDeDezenasNoVolante) {
		this.quantidadeDeDezenasNoVolante = quantidadeDeDezenasNoVolante;
	}

	public Integer getTeimosinha() {
		return teimosinha;
	}

	public void setTeimosinha(Integer teimosinha) {
		this.teimosinha = teimosinha;
	}

	public Date getDataDaAposta() {
		return dataDaAposta;
	}

	public void setDataDaAposta(Date dataDaAposta) {
		this.dataDaAposta = dataDaAposta;
	}
}

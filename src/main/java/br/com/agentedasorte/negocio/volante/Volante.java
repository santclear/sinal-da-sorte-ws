package br.com.agentedasorte.negocio.volante;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.agentedasorte.negocio.conta.Conta;
import br.com.agentedasorte.negocio.loteria.Loteria;

@Entity
@Table(name = "volante", catalog = "bdagente")
public class Volante extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 4318883576437030241L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "conta_email", nullable = false)
	private Conta conta;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "loteria_id", nullable = false)
	private Loteria loteria;

	@Column(name = "dezenas_marcadas", nullable = false, length = 99)
	private String dezenasMarcadas;

	@Column(name = "quantidade_de_dezenas_no_volante", nullable = false)
	private int quantidadeDeDezenasNoVolante;

	@Column(name = "teimosinha")
	private Integer teimosinha;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_da_aposta", nullable = false, length = 10)
	private Date dataDaAposta;

	public Volante() {
	}

	public Volante(Conta conta, Loteria loteria, String dezenasMarcadas, int quantidadeDeDezenasNoVolante,
			Integer teimosinha, Date dataDaAposta) {
		super();
		this.conta = conta;
		this.loteria = loteria;
		this.dezenasMarcadas = dezenasMarcadas;
		this.quantidadeDeDezenasNoVolante = quantidadeDeDezenasNoVolante;
		this.teimosinha = teimosinha;
		this.dataDaAposta = dataDaAposta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((dataDaAposta == null) ? 0 : dataDaAposta.hashCode());
		result = prime * result + ((dezenasMarcadas == null) ? 0 : dezenasMarcadas.hashCode());
		result = prime * result + ((loteria == null) ? 0 : loteria.hashCode());
		result = prime * result + quantidadeDeDezenasNoVolante;
		result = prime * result + ((teimosinha == null) ? 0 : teimosinha.hashCode());
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
		Volante other = (Volante) obj;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (dataDaAposta == null) {
			if (other.dataDaAposta != null)
				return false;
		} else if (!dataDaAposta.equals(other.dataDaAposta))
			return false;
		if (dezenasMarcadas == null) {
			if (other.dezenasMarcadas != null)
				return false;
		} else if (!dezenasMarcadas.equals(other.dezenasMarcadas))
			return false;
		if (loteria == null) {
			if (other.loteria != null)
				return false;
		} else if (!loteria.equals(other.loteria))
			return false;
		if (quantidadeDeDezenasNoVolante != other.quantidadeDeDezenasNoVolante)
			return false;
		if (teimosinha == null) {
			if (other.teimosinha != null)
				return false;
		} else if (!teimosinha.equals(other.teimosinha))
			return false;
		return true;
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

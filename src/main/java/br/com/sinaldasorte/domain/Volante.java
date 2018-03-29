package br.com.sinaldasorte.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Volante implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="conta_id", nullable = false)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Conta conta;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="loteria_id", nullable = false)
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

	public Volante(Long id, Conta conta, Loteria loteria, String dezenasMarcadas, int quantidadeDeDezenasNoVolante,
			Integer teimosinha, Date dataDaAposta) {
		super();
		this.id = id;
		this.conta = conta;
		this.loteria = loteria;
		this.dezenasMarcadas = dezenasMarcadas;
		this.quantidadeDeDezenasNoVolante = quantidadeDeDezenasNoVolante;
		this.teimosinha = teimosinha;
		this.dataDaAposta = dataDaAposta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		Volante other = (Volante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

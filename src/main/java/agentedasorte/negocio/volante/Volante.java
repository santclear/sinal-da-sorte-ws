package agentedasorte.negocio.volante;

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

import agentedasorte.negocio.concurso.Concurso;
import agentedasorte.negocio.conta.Conta;

@Entity
@Table(name = "volante", catalog = "bdagente")
public class Volante extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -5362493727639083200L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "conta_email", nullable = false)
	private Conta conta;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "concurso_id", nullable = false)
	private Concurso concurso;

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

	public Volante(Conta conta, String dezenasMarcadas, int quantidadeDeDezenasNoVolante, Date dataDaAposta) {
		this.conta = conta;
		this.dezenasMarcadas = dezenasMarcadas;
		this.quantidadeDeDezenasNoVolante = quantidadeDeDezenasNoVolante;
		this.dataDaAposta = dataDaAposta;
	}

	public Volante(Conta conta, String dezenasMarcadas, int quantidadeDeDezenasNoVolante, Integer teimosinha, Date dataDaAposta) {
		this.conta = conta;
		this.dezenasMarcadas = dezenasMarcadas;
		this.quantidadeDeDezenasNoVolante = quantidadeDeDezenasNoVolante;
		this.teimosinha = teimosinha;
		this.dataDaAposta = dataDaAposta;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((concurso == null) ? 0 : concurso.hashCode());
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((dataDaAposta == null) ? 0 : dataDaAposta.hashCode());
		result = prime * result + ((dezenasMarcadas == null) ? 0 : dezenasMarcadas.hashCode());
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
		if (!(obj instanceof Volante))
			return false;
		Volante other = (Volante) obj;
		if (concurso == null) {
			if (other.concurso != null)
				return false;
		} else if (!concurso.equals(other.concurso))
			return false;
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
		if (quantidadeDeDezenasNoVolante != other.quantidadeDeDezenasNoVolante)
			return false;
		if (teimosinha == null) {
			if (other.teimosinha != null)
				return false;
		} else if (!teimosinha.equals(other.teimosinha))
			return false;
		return true;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public Conta getConta() {
		return this.conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Concurso getConcurso() {
		return concurso;
	}

	public void setConcurso(Concurso concurso) {
		this.concurso = concurso;
	}

	public String getDezenasMarcadas() {
		return this.dezenasMarcadas;
	}

	public void setDezenasMarcadas(String dezenasMarcadas) {
		this.dezenasMarcadas = dezenasMarcadas;
	}

	public int getQuantidadeDeDezenasNoVolante() {
		return this.quantidadeDeDezenasNoVolante;
	}

	public void setQuantidadeDeDezenasNoVolante(int quantidadeDeDezenasNoVolante) {
		this.quantidadeDeDezenasNoVolante = quantidadeDeDezenasNoVolante;
	}

	public Integer getTeimosinha() {
		return this.teimosinha;
	}

	public void setTeimosinha(Integer teimosinha) {
		this.teimosinha = teimosinha;
	}

	public Date getDataDaAposta() {
		return this.dataDaAposta;
	}

	public void setDataDaAposta(Date dataDaAposta) {
		this.dataDaAposta = dataDaAposta;
	}
}

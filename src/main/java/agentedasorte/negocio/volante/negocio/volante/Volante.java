package agentedasorte.negocio.volante.negocio.volante;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import agentedasorte.negocio.volante.negocio.conta.Conta;
import agentedasorte.negocio.volante.negocio.loteria.Loteria;
import agentedasorte.negocio.volante.negocio.time.Time;

@Entity
@Table(name = "volante", catalog = "bdagente")
public class Volante implements java.io.Serializable {

	private Integer id;
	private Conta conta;
	private Loteria loteria;
	private Time time;
	private String dezenasMarcadas;
	private int quantidadeDeDezenasNoVolante;
	private Integer teimosinha;
	private Date dataDaAposta;
	private int contaId;

	public Volante() {
	}

	public Volante(Conta conta, Loteria loteria, String dezenasMarcadas, int quantidadeDeDezenasNoVolante,
			Date dataDaAposta, int contaId) {
		this.conta = conta;
		this.loteria = loteria;
		this.dezenasMarcadas = dezenasMarcadas;
		this.quantidadeDeDezenasNoVolante = quantidadeDeDezenasNoVolante;
		this.dataDaAposta = dataDaAposta;
		this.contaId = contaId;
	}

	public Volante(Conta conta, Loteria loteria, Time time, String dezenasMarcadas, int quantidadeDeDezenasNoVolante,
			Integer teimosinha, Date dataDaAposta, int contaId) {
		this.conta = conta;
		this.loteria = loteria;
		this.time = time;
		this.dezenasMarcadas = dezenasMarcadas;
		this.quantidadeDeDezenasNoVolante = quantidadeDeDezenasNoVolante;
		this.teimosinha = teimosinha;
		this.dataDaAposta = dataDaAposta;
		this.contaId = contaId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conta_email", nullable = false)
	public Conta getConta() {
		return this.conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loteria_id", nullable = false)
	public Loteria getLoteria() {
		return this.loteria;
	}

	public void setLoteria(Loteria loteria) {
		this.loteria = loteria;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "time_id")
	public Time getTime() {
		return this.time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	@Column(name = "dezenas_marcadas", nullable = false, length = 99)
	public String getDezenasMarcadas() {
		return this.dezenasMarcadas;
	}

	public void setDezenasMarcadas(String dezenasMarcadas) {
		this.dezenasMarcadas = dezenasMarcadas;
	}

	@Column(name = "quantidade_de_dezenas_no_volante", nullable = false)
	public int getQuantidadeDeDezenasNoVolante() {
		return this.quantidadeDeDezenasNoVolante;
	}

	public void setQuantidadeDeDezenasNoVolante(int quantidadeDeDezenasNoVolante) {
		this.quantidadeDeDezenasNoVolante = quantidadeDeDezenasNoVolante;
	}

	@Column(name = "teimosinha")
	public Integer getTeimosinha() {
		return this.teimosinha;
	}

	public void setTeimosinha(Integer teimosinha) {
		this.teimosinha = teimosinha;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_da_aposta", nullable = false, length = 10)
	public Date getDataDaAposta() {
		return this.dataDaAposta;
	}

	public void setDataDaAposta(Date dataDaAposta) {
		this.dataDaAposta = dataDaAposta;
	}

	@Column(name = "conta_id", nullable = false)
	public int getContaId() {
		return this.contaId;
	}

	public void setContaId(int contaId) {
		this.contaId = contaId;
	}

}

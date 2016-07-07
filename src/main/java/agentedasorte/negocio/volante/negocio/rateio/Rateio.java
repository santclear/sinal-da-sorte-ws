package agentedasorte.negocio.volante.negocio.rateio;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import agentedasorte.negocio.volante.negocio.concurso.Concurso;

@Entity
@Table(name = "rateio", catalog = "bdagente")
public class Rateio implements java.io.Serializable {

	private Integer id;
	private Concurso concurso;
	private BigDecimal rateio;
	private int numeroDeGanhadores;

	public Rateio() {
	}

	public Rateio(Concurso concurso, BigDecimal rateio, int numeroDeGanhadores) {
		this.concurso = concurso;
		this.rateio = rateio;
		this.numeroDeGanhadores = numeroDeGanhadores;
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
	@JoinColumn(name = "concurso_id", nullable = false)
	public Concurso getConcurso() {
		return this.concurso;
	}

	public void setConcurso(Concurso concurso) {
		this.concurso = concurso;
	}

	@Column(name = "rateio", nullable = false, precision = 15)
	public BigDecimal getRateio() {
		return this.rateio;
	}

	public void setRateio(BigDecimal rateio) {
		this.rateio = rateio;
	}

	@Column(name = "numeroDeGanhadores", nullable = false)
	public int getNumeroDeGanhadores() {
		return this.numeroDeGanhadores;
	}

	public void setNumeroDeGanhadores(int numeroDeGanhadores) {
		this.numeroDeGanhadores = numeroDeGanhadores;
	}

}

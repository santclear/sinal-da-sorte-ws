package agentedasorte.negocio.volante.negocio.concurso;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import agentedasorte.negocio.volante.negocio.loteria.Loteria;
import agentedasorte.negocio.volante.negocio.rateio.Rateio;
import agentedasorte.negocio.volante.negocio.time.Time;

@Entity
@Table(name = "concurso", catalog = "bdagente")
public class Concurso implements java.io.Serializable {

	private static final long serialVersionUID = -7784166962967036002L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loteria_id", nullable = false)
	private Loteria loteria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "time_id")
	private Time time;
	
	@Column(name = "concurso", nullable = false)
	private int concurso;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_do_sorteio", length = 10)
	private Date dataDoSorteio;
	
	@Column(name = "numeros_sorteados", nullable = false, length = 199)
	private String numerosSorteados;
	
	@Column(name = "arrecadacao_total", precision = 15)
	private BigDecimal arrecadacaoTotal;
	
	@Column(name = "estimativa_de_premio_para_o_proximo_concurso", precision = 15)
	private BigDecimal estimativaDePremioParaOProximoConcurso;
	
	@Column(name = "acumulado_para_o_proximo_concurso", precision = 15)
	private BigDecimal acumuladoParaOProximoConcurso;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "concurso")
	private Set<Rateio> rateios = new HashSet<Rateio>(0);

	public Concurso() {
	}

	public Concurso(Loteria loteria, int concurso, String numerosSorteados) {
		this.loteria = loteria;
		this.concurso = concurso;
		this.numerosSorteados = numerosSorteados;
	}

	public Concurso(Loteria loteria, Time time, int concurso, Date dataDoSorteio, String numerosSorteados,
			BigDecimal arrecadacaoTotal, BigDecimal estimativaDePremioParaOProximoConcurso,
			BigDecimal acumuladoParaOProximoConcurso, Set<Rateio> rateios) {
		this.loteria = loteria;
		this.time = time;
		this.concurso = concurso;
		this.dataDoSorteio = dataDoSorteio;
		this.numerosSorteados = numerosSorteados;
		this.arrecadacaoTotal = arrecadacaoTotal;
		this.estimativaDePremioParaOProximoConcurso = estimativaDePremioParaOProximoConcurso;
		this.acumuladoParaOProximoConcurso = acumuladoParaOProximoConcurso;
		this.rateios = rateios;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acumuladoParaOProximoConcurso == null) ? 0 : acumuladoParaOProximoConcurso.hashCode());
		result = prime * result + ((arrecadacaoTotal == null) ? 0 : arrecadacaoTotal.hashCode());
		result = prime * result + concurso;
		result = prime * result + ((dataDoSorteio == null) ? 0 : dataDoSorteio.hashCode());
		result = prime * result + ((estimativaDePremioParaOProximoConcurso == null) ? 0 : estimativaDePremioParaOProximoConcurso.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((loteria == null) ? 0 : loteria.hashCode());
		result = prime * result + ((numerosSorteados == null) ? 0 : numerosSorteados.hashCode());
		result = prime * result + ((rateios == null) ? 0 : rateios.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		Concurso other = (Concurso) obj;
		if (acumuladoParaOProximoConcurso == null) {
			if (other.acumuladoParaOProximoConcurso != null)
				return false;
		} else if (!acumuladoParaOProximoConcurso.equals(other.acumuladoParaOProximoConcurso))
			return false;
		if (arrecadacaoTotal == null) {
			if (other.arrecadacaoTotal != null)
				return false;
		} else if (!arrecadacaoTotal.equals(other.arrecadacaoTotal))
			return false;
		if (concurso != other.concurso)
			return false;
		if (dataDoSorteio == null) {
			if (other.dataDoSorteio != null)
				return false;
		} else if (!dataDoSorteio.equals(other.dataDoSorteio))
			return false;
		if (estimativaDePremioParaOProximoConcurso == null) {
			if (other.estimativaDePremioParaOProximoConcurso != null)
				return false;
		} else if (!estimativaDePremioParaOProximoConcurso.equals(other.estimativaDePremioParaOProximoConcurso))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (loteria == null) {
			if (other.loteria != null)
				return false;
		} else if (!loteria.equals(other.loteria))
			return false;
		if (numerosSorteados == null) {
			if (other.numerosSorteados != null)
				return false;
		} else if (!numerosSorteados.equals(other.numerosSorteados))
			return false;
		if (rateios == null) {
			if (other.rateios != null)
				return false;
		} else if (!rateios.equals(other.rateios))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Loteria getLoteria() {
		return this.loteria;
	}

	public void setLoteria(Loteria loteria) {
		this.loteria = loteria;
	}

	
	public Time getTime() {
		return this.time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	
	public int getConcurso() {
		return this.concurso;
	}

	public void setConcurso(int concurso) {
		this.concurso = concurso;
	}

	
	public Date getDataDoSorteio() {
		return this.dataDoSorteio;
	}

	public void setDataDoSorteio(Date dataDoSorteio) {
		this.dataDoSorteio = dataDoSorteio;
	}

	
	public String getNumerosSorteados() {
		return this.numerosSorteados;
	}

	public void setNumerosSorteados(String numerosSorteados) {
		this.numerosSorteados = numerosSorteados;
	}

	public BigDecimal getArrecadacaoTotal() {
		return this.arrecadacaoTotal;
	}

	public void setArrecadacaoTotal(BigDecimal arrecadacaoTotal) {
		this.arrecadacaoTotal = arrecadacaoTotal;
	}

	public BigDecimal getEstimativaDePremioParaOProximoConcurso() {
		return this.estimativaDePremioParaOProximoConcurso;
	}

	public void setEstimativaDePremioParaOProximoConcurso(BigDecimal estimativaDePremioParaOProximoConcurso) {
		this.estimativaDePremioParaOProximoConcurso = estimativaDePremioParaOProximoConcurso;
	}

	public BigDecimal getAcumuladoParaOProximoConcurso() {
		return this.acumuladoParaOProximoConcurso;
	}

	public void setAcumuladoParaOProximoConcurso(BigDecimal acumuladoParaOProximoConcurso) {
		this.acumuladoParaOProximoConcurso = acumuladoParaOProximoConcurso;
	}

	public Set<Rateio> getRateios() {
		return this.rateios;
	}

	public void setRateios(Set<Rateio> rateios) {
		this.rateios = rateios;
	}

}

package agentedasorte.negocio.concurso;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import agentedasorte.negocio.loteria.Loteria;
import agentedasorte.negocio.rateio.Rateio;
import agentedasorte.negocio.time.Time;
import agentedasorte.negocio.volante.Volante;

@Entity
@Table(name = "concurso", catalog = "bdagente")
public class Concurso extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 7578741384015464256L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "loteria_id", nullable = false)
	private Loteria loteria;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "time_id")
	private Time time;
	
	@Column(name = "numero", nullable = false)
	private int numero;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_do_sorteio", length = 10)
	private Date dataDoSorteio;
	
	@Column(name = "numeros_sorteados", nullable = false, length = 199)
	private String numerosSorteados;
	
	@Column(name = "arrecadacao_total", precision = 15, scale = 2)
	private BigDecimal arrecadacaoTotal;
	
	@Column(name = "estimativa_de_premio_para_o_proximo_concurso", precision = 15, scale = 2)
	private BigDecimal estimativaDePremioParaOProximoConcurso;
	
	@Column(name = "acumulado_para_o_proximo_concurso", precision = 15, scale = 2)
	private BigDecimal acumuladoParaOProximoConcurso;
	
	@JsonIgnore
	@OneToMany(mappedBy = "concurso")
	private List<Volante> volantes;
	
	@JsonIgnore
	@OneToMany(mappedBy = "concurso")
	private List<Rateio> rateios;

	public Concurso() {
	}

	public Concurso(Loteria loteria, int numero, String numerosSorteados) {
		this.loteria = loteria;
		this.numero = numero;
		this.numerosSorteados = numerosSorteados;
	}

	public Concurso(Loteria loteria, Time time, int numero, Date dataDoSorteio, String numerosSorteados, BigDecimal arrecadacaoTotal, BigDecimal estimativaDePremioParaOProximoConcurso,
			BigDecimal acumuladoParaOProximoConcurso, List<Rateio> rateios) {
		this.loteria = loteria;
		this.time = time;
		this.numero = numero;
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
		int result = super.hashCode();
		result = prime * result + ((acumuladoParaOProximoConcurso == null) ? 0 : acumuladoParaOProximoConcurso.hashCode());
		result = prime * result + ((arrecadacaoTotal == null) ? 0 : arrecadacaoTotal.hashCode());
		result = prime * result + ((dataDoSorteio == null) ? 0 : dataDoSorteio.hashCode());
		result = prime * result + ((estimativaDePremioParaOProximoConcurso == null) ? 0 : estimativaDePremioParaOProximoConcurso.hashCode());
		result = prime * result + ((loteria == null) ? 0 : loteria.hashCode());
		result = prime * result + numero;
		result = prime * result + ((numerosSorteados == null) ? 0 : numerosSorteados.hashCode());
		result = prime * result + ((rateios == null) ? 0 : rateios.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((volantes == null) ? 0 : volantes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Concurso))
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
		if (loteria == null) {
			if (other.loteria != null)
				return false;
		} else if (!loteria.equals(other.loteria))
			return false;
		if (numero != other.numero)
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
		if (volantes == null) {
			if (other.volantes != null)
				return false;
		} else if (!volantes.equals(other.volantes))
			return false;
		return true;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
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

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
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

	public List<Volante> getVolantes() {
		return volantes;
	}

	public void setVolantes(List<Volante> volantes) {
		this.volantes = volantes;
	}

	public List<Rateio> getRateios() {
		return this.rateios;
	}

	public void setRateios(List<Rateio> rateios) {
		this.rateios = rateios;
	}
}

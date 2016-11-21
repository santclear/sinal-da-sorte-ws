package br.com.agentedasorte.negocio.concurso;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.agentedasorte.negocio.loteria.Loteria;
import br.com.agentedasorte.negocio.sorteio.Sorteio;

@Entity
@Table(name = "concurso", catalog = "bdagente")
public class Concurso extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = -9052592205799796485L;

	
	@ManyToOne
	@JoinColumn(name = "loteria_id", nullable = false)
	private Loteria loteria;
	
	@Column(name = "numero", nullable = false)
	private int numero;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_do_sorteio", length = 10)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEEE, dd/MM/yyyy", locale = "pt", timezone = "Brazil/East")
	private Calendar dataDoSorteio;
	
	@Column(name = "arrecadacao_total", precision = 15, scale = 2)
	private BigDecimal arrecadacaoTotal;
	
	@Column(name = "cidade", length = 2048)
	private String cidade;
	
	@Column(name = "uf", length = 2048)
	private String uf;
	
	@Column(name = "estimativa_de_premio_para_o_proximo_concurso", precision = 15, scale = 2)
	private BigDecimal estimativaDePremioParaOProximoConcurso;
	
	@Column(name = "acumulado_especial", precision = 15, scale = 2)
	private BigDecimal acumuladoEspecial;
	
	@OneToMany(mappedBy = "concurso")
	private List<Sorteio> sorteios;
	
	public Concurso() {
		this.sorteios = new LinkedList<>();
	}

	public Concurso(Loteria loteria, int numero, Calendar dataDoSorteio, BigDecimal arrecadacaoTotal, String cidade,
			String uf, BigDecimal estimativaDePremioParaOProximoConcurso, BigDecimal acumuladoEspecial) {
		this.loteria = loteria;
		this.numero = numero;
		this.dataDoSorteio = dataDoSorteio;
		this.arrecadacaoTotal = arrecadacaoTotal;
		this.cidade = cidade;
		this.uf = uf;
		this.estimativaDePremioParaOProximoConcurso = estimativaDePremioParaOProximoConcurso;
		this.acumuladoEspecial = acumuladoEspecial;
	}

	public Concurso(Loteria loteria, int numero, Calendar dataDoSorteio, BigDecimal arrecadacaoTotal, String cidade,
			String uf, BigDecimal estimativaDePremioParaOProximoConcurso, BigDecimal acumuladoEspecial,
			List<Sorteio> sorteios) {
		this.loteria = loteria;
		this.numero = numero;
		this.dataDoSorteio = dataDoSorteio;
		this.arrecadacaoTotal = arrecadacaoTotal;
		this.cidade = cidade;
		this.uf = uf;
		this.estimativaDePremioParaOProximoConcurso = estimativaDePremioParaOProximoConcurso;
		this.acumuladoEspecial = acumuladoEspecial;
		this.sorteios = sorteios;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((acumuladoEspecial == null) ? 0 : acumuladoEspecial.hashCode());
		result = prime * result + ((arrecadacaoTotal == null) ? 0 : arrecadacaoTotal.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((dataDoSorteio == null) ? 0 : dataDoSorteio.hashCode());
		result = prime * result + ((estimativaDePremioParaOProximoConcurso == null) ? 0 : estimativaDePremioParaOProximoConcurso.hashCode());
		result = prime * result + ((loteria == null) ? 0 : loteria.hashCode());
		result = prime * result + numero;
		result = prime * result + ((sorteios == null) ? 0 : sorteios.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
		Concurso other = (Concurso) obj;
		if (acumuladoEspecial == null) {
			if (other.acumuladoEspecial != null)
				return false;
		} else if (!acumuladoEspecial.equals(other.acumuladoEspecial))
			return false;
		if (arrecadacaoTotal == null) {
			if (other.arrecadacaoTotal != null)
				return false;
		} else if (!arrecadacaoTotal.equals(other.arrecadacaoTotal))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
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
		if (sorteios == null) {
			if (other.sorteios != null)
				return false;
		} else if (!sorteios.equals(other.sorteios))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
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

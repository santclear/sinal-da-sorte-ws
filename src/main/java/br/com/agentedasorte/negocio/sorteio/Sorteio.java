package br.com.agentedasorte.negocio.sorteio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.agentedasorte.negocio.concurso.Concurso;
import br.com.agentedasorte.negocio.rateio.Rateio;

@Entity
@Table(name = "sorteio", catalog = "bdagente")
public class Sorteio extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 7695081512529031004L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "concurso_id", nullable = false)
	private Concurso concurso;
	
	@Column(name = "numero")
	private int numero;
	
	@Column(name = "numeros_sorteados", nullable = false, length = 199)
	private String numerosSorteados;
	
	@OneToMany(mappedBy = "sorteio")
	private List<Rateio> rateios;
	
	public Sorteio() {
	}
	
	public Sorteio(Concurso concurso, int numero, String numerosSorteados) {
		this.concurso = concurso;
		this.numero = numero;
		this.numerosSorteados = numerosSorteados;
	}

	public Sorteio(Concurso concurso, int numero, String numerosSorteados, List<Rateio> rateios) {
		super();
		this.concurso = concurso;
		this.numero = numero;
		this.numerosSorteados = numerosSorteados;
		this.rateios = rateios;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((concurso == null) ? 0 : concurso.hashCode());
		result = prime * result + numero;
		result = prime * result + ((numerosSorteados == null) ? 0 : numerosSorteados.hashCode());
		result = prime * result + ((rateios == null) ? 0 : rateios.hashCode());
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
		Sorteio other = (Sorteio) obj;
		if (concurso == null) {
			if (other.concurso != null)
				return false;
		} else if (!concurso.equals(other.concurso))
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
		return true;
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

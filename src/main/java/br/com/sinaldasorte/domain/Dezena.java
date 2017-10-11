package br.com.sinaldasorte.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Dezena extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Loteria loteria;

	@Column(length = 4)
	private String numero;

	public Dezena() {}

	public Dezena(Loteria loteria, String numero) {
		this.loteria = loteria;
		this.numero = numero;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}

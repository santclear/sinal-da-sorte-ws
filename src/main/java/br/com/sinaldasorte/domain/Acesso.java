package br.com.sinaldasorte.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Acesso extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 10)
	private String tipo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "acesso")
	private List<Conta> contas;

	public Acesso() {
	}

	public Acesso(String tipo) {
		this.tipo = tipo;
	}

	public Acesso(String tipo, List<Conta> contas) {
		this.tipo = tipo;
		this.contas = contas;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Conta> getContas() {
		return this.contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
}

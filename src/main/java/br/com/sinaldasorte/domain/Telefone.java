package br.com.sinaldasorte.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Telefone extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@Column(length = 20)
	private String telefone;

	public Telefone() {}

	public Telefone(Usuario usuario, String telefone) {
		this.usuario = usuario;
		this.telefone = telefone;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}

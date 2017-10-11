package br.com.sinaldasorte.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Situacao extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 10)
	private String descricao;

	@JsonIgnore
	@OneToMany(mappedBy = "situacao")
	private List<Conta> contas = new LinkedList<>();

	public Situacao() {}

	public Situacao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<Conta> getContas() {
		return this.contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
}

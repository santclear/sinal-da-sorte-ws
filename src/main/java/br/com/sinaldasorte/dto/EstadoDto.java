package br.com.sinaldasorte.dto;

import java.io.Serializable;

import br.com.sinaldasorte.domain.Estado;

public class EstadoDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	
	public EstadoDto() {
	}

	public EstadoDto(Estado obj) {
		id = obj.getId();
		nome = obj.getNome();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
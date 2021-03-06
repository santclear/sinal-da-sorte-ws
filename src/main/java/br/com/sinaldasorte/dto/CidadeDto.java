package br.com.sinaldasorte.dto;

import java.io.Serializable;

public class CidadeDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Long ufId;
	
	public CidadeDto() {
	}
	
	public CidadeDto(Long id, String nome, Long ufId) {
		this.id = id;
		this.nome = nome;
		this.ufId = ufId;
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

	public Long getUfId() {
		return ufId;
	}

	public void setUfId(Long ufId) {
		this.ufId = ufId;
	}
}
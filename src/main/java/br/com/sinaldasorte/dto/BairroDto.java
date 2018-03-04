package br.com.sinaldasorte.dto;

import java.io.Serializable;

public class BairroDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Long cidadeId;
	
	public BairroDto() {
	}

	public BairroDto(Long id, String nome, Long cidadeId) {
		this.id = id;
		this.nome = nome;
		this.cidadeId = cidadeId;
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

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}
}
package br.com.sinaldasorte.dto;

import java.io.Serializable;

public class LogradouroDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String cep;
	private String nome;
	private Long bairroId;
	
	public LogradouroDto() {
	}

	public LogradouroDto(String cep, String nome, Long bairroId) {
		this.cep = cep;
		this.nome = nome;
		this.bairroId = bairroId;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getBairroId() {
		return bairroId;
	}

	public void setBairroId(Long bairroId) {
		this.bairroId = bairroId;
	}
}
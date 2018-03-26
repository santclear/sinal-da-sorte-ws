package br.com.sinaldasorte.dto;

import java.io.Serializable;

public class LogradouroDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String cep;
	private String nome;
	private String numero;
	private String complemento;
	private Long bairroId;
	
	public LogradouroDto() {
	}

	public LogradouroDto(String cep, String nome, String numero, String complemento, Long bairroId) {
		this.cep = cep;
		this.nome = nome;
		this.numero = numero;
		this.complemento = complemento;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Long getBairroId() {
		return bairroId;
	}

	public void setBairroId(Long bairroId) {
		this.bairroId = bairroId;
	}
}
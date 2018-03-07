package br.com.sinaldasorte.dto;

import java.io.Serializable;

public class EnderecoCorreiosDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String cep;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String uf;
	
	public EnderecoCorreiosDto() {}

	public EnderecoCorreiosDto(String cep, String logradouro, String bairro, String cidade, String uf) {
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
}
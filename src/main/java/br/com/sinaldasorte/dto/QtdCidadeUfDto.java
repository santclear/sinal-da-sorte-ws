package br.com.sinaldasorte.dto;

import java.io.Serializable;

public class QtdCidadeUfDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String cidadeUf;
	private Integer qtd;
	
	public QtdCidadeUfDto() {}

	public QtdCidadeUfDto(String cidadeUf, Integer qtd) {
		this.cidadeUf = cidadeUf;
		this.qtd = qtd;
	}

	public String getCidadeUf() {
		return cidadeUf;
	}
	
	public void setCidadeUf(String cidadeUf) {
		this.cidadeUf = cidadeUf;
	}
	
	public Integer getQtd() {
		return qtd;
	}
	
	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

}

package br.com.sinaldasorte.dto;

import java.io.Serializable;

public class UtilDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String mensagem;

	public UtilDto() {}

	public UtilDto(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}

package br.com.sinaldasorte.dto;

import java.io.Serializable;

public class ContatoDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String para;
	private String assunto;
	private String mensagem;
	private String contato;
	
	public String getPara() {
		return para;
	}
	
	public void setPara(String para) {
		this.para = para;
	}
	
	public String getAssunto() {
		return assunto;
	}
	
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
}

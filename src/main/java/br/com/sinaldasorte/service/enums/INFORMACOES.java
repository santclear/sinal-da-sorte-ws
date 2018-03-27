package br.com.sinaldasorte.service.enums;

public enum INFORMACOES {
	
	ATUALIZAR_EMAIL("i1", "Atualização de e-mail"),
	ATUALIZAR_EMAIL_SENHA("i1", "Atualização de e-mail e senha");
	
	private String cod;
	private String mensagem;
	
	private INFORMACOES(String cod, String mensagem) {
		this.cod = cod;
		this.mensagem = mensagem;
	}

	public String getCod() {
		return cod;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	public static INFORMACOES toEnum(String cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(INFORMACOES x: INFORMACOES.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código de exceção inválido: "+ cod);
	}
}
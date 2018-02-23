package br.com.sinaldasorte.domain.enums;

public enum Situacoes {
	
	ATIVO(1, "Ativo"),
	INATIVO(2, "Inativo"),
	INADIMPLENTE(3, "Inadimplente");
	
	private int cod;
	private String descricao;
	
	private Situacoes(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Situacoes toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(Situacoes x: Situacoes.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+ cod);
	}
}
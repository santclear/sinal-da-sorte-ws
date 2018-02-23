package br.com.sinaldasorte.domain.enums;

public enum Generos {
	
	HOMEM(1, "Homem"),
	MULHER(2, "Mulher"),
	OUTRO(3, "Outro");
	
	private int cod;
	private String descricao;
	
	private Generos(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Generos toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(Generos x: Generos.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+ cod);
	}
}
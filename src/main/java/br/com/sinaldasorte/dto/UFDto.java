package br.com.sinaldasorte.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class UFDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotEmpty(message="Preenchimento obrigatório")
	private String nome;

	public UFDto() {}

	public UFDto(Long id, String nome) {
		this.id = id;
		this.nome = nome;
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
}

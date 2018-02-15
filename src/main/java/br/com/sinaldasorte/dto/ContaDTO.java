package br.com.sinaldasorte.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.service.validation.ContaUpdate;

@ContaUpdate
public class ContaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
//	@NotEmpty(message="Preenchimento obrigatório")
//	@Length(min=5, max=120, message="O tamanho de ser entre 5 e 120 caracteres")
//	private String nome;
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	public ContaDTO() {}

	public ContaDTO(Conta obj) {
		this.id = obj.getId();
//		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
//
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

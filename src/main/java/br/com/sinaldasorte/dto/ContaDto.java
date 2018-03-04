package br.com.sinaldasorte.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.service.validation.ContaUpdate;

@ContaUpdate
public class ContaDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	public ContaDto() {}

	public ContaDto(Conta obj) {
		this.id = obj.getId();
		this.email = obj.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

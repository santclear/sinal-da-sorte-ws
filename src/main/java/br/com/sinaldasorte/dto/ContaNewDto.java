package br.com.sinaldasorte.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.sinaldasorte.service.validation.ContaInsert;

@ContaInsert
public class ContaNewDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	@NotEmpty(message="Preenchimento obrigatório")
	private String senha;
	private UsuarioDto usuario;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha; 
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public UsuarioDto getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}
}

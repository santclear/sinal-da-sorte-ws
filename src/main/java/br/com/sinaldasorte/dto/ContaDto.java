package br.com.sinaldasorte.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;

import br.com.sinaldasorte.service.validation.ContaUpdate;

@ContaUpdate
public class ContaDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	@Email(message="Email inválido")
	private String email;
	private String senha;
	private String novaSenha;
	private UsuarioDto usuario;
	
	public ContaDto() {}

	public ContaDto(Long id, String email, String senha, String novaSenha, UsuarioDto usuario) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.senha = novaSenha;
		this.usuario = usuario;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}
}

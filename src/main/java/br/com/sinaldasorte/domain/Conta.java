package br.com.sinaldasorte.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Conta implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false, length = 60)
	private String email;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Acesso acesso;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Situacao situacao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;
	
	@Column(nullable = false, length = 2048)
	private String senha;
	
	@JsonIgnore
	@OneToMany(mappedBy = "conta")
	private List<Volante> volantes = new LinkedList<>();

	public Conta() {}
	
	public Conta(String email, Acesso acesso, Situacao situacao, Usuario usuario, String senha) {
		this.email = email;
		this.acesso = acesso;
		this.situacao = situacao;
		this.usuario = usuario;
		this.senha = senha;
	}

	public String getEmail() {
		return this.email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public Acesso getAcesso() {
		return this.acesso;
	}

	public void setAcesso(Acesso acesso) {
		this.acesso = acesso;
	}

	public Situacao getSituacao() {
		return this.situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Volante> getVolantes() {
		return this.volantes;
	}

	public void setVolantes(List<Volante> volantes) {
		this.volantes = volantes;
	}
}

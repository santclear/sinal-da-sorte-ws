package br.com.agentedasorte.negocio.conta;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.agentedasorte.negocio.acesso.Acesso;
import br.com.agentedasorte.negocio.situacao.Situacao;
import br.com.agentedasorte.negocio.usuario.Usuario;
import br.com.agentedasorte.negocio.volante.Volante;

@Entity
@Table(name = "conta", catalog = "bdagente")
public class Conta implements Serializable {

	private static final long serialVersionUID = 5165621896779378468L;

	@Id
	@Column(name = "email", unique = true, nullable = false, length = 60)
	private String email;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "acesso_id", nullable = false)
	private Acesso acesso;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "situacao_id", nullable = false)
	private Situacao situacao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	@Column(name = "senha", nullable = false, length = 2048)
	private String senha;
	
	@JsonIgnore
	@OneToMany(mappedBy = "conta")
	private List<Volante> volantes;

	public Conta() {
	}

	public Conta(String email, Acesso acesso, Situacao situacao, Usuario usuario, String senha) {
		this.email = email;
		this.acesso = acesso;
		this.situacao = situacao;
		this.usuario = usuario;
		this.senha = senha;
	}

	public Conta(String email, Acesso acesso, Situacao situacao, Usuario usuario, String senha, List<Volante> volantes) {
		this.email = email;
		this.acesso = acesso;
		this.situacao = situacao;
		this.usuario = usuario;
		this.senha = senha;
		this.volantes = volantes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acesso == null) ? 0 : acesso.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((volantes == null) ? 0 : volantes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Conta))
			return false;
		Conta other = (Conta) obj;
		if (acesso == null) {
			if (other.acesso != null)
				return false;
		} else if (!acesso.equals(other.acesso))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (volantes == null) {
			if (other.volantes != null)
				return false;
		} else if (!volantes.equals(other.volantes))
			return false;
		return true;
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

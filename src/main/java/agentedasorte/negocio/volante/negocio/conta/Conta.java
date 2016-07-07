package agentedasorte.negocio.volante.negocio.conta;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import agentedasorte.negocio.volante.negocio.acesso.Acesso;
import agentedasorte.negocio.volante.negocio.situacao.Situacao;
import agentedasorte.negocio.volante.negocio.usuario.Usuario;

@Entity
@Table(name = "conta", catalog = "bdagente")
public class Conta implements java.io.Serializable {

	private String email;
	private Acesso acesso;
	private Situacao situacao;
	private Usuario usuario;
	private String senha;
	private Set volantes = new HashSet(0);

	public Conta() {
	}

	public Conta(String email, Acesso acesso, Situacao situacao, Usuario usuario, String senha) {
		this.email = email;
		this.acesso = acesso;
		this.situacao = situacao;
		this.usuario = usuario;
		this.senha = senha;
	}

	public Conta(String email, Acesso acesso, Situacao situacao, Usuario usuario, String senha, Set volantes) {
		this.email = email;
		this.acesso = acesso;
		this.situacao = situacao;
		this.usuario = usuario;
		this.senha = senha;
		this.volantes = volantes;
	}

	@Id

	@Column(name = "email", unique = true, nullable = false, length = 60)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acesso_id", nullable = false)
	public Acesso getAcesso() {
		return this.acesso;
	}

	public void setAcesso(Acesso acesso) {
		this.acesso = acesso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "situacao_id", nullable = false)
	public Situacao getSituacao() {
		return this.situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "senha", nullable = false, length = 2048)
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "conta")
	public Set getVolantes() {
		return this.volantes;
	}

	public void setVolantes(Set volantes) {
		this.volantes = volantes;
	}

}

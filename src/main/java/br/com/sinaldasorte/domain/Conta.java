package br.com.sinaldasorte.domain;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.sinaldasorte.domain.enums.Perfil;

@Entity
public class Conta extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 1L;
	
	@Column(unique = true, nullable = false, length = 60)
	private String email;
	
	@ManyToOne
	//FIXME permitido null temporariamente para testes. O correto é nullable = false
	@JoinColumn(nullable = true)
	private Acesso acesso;
	
	@ManyToOne
	//FIXME permitido null temporariamente para testes. O correto é nullable = false
	@JoinColumn(nullable = true)
	private Situacao situacao;
	
	@ManyToOne
	//FIXME permitido null temporariamente para testes. O correto é nullable = false
	@JoinColumn(nullable = true)
	private Usuario usuario;
	
	@JsonIgnore
	@Column(nullable = false, length = 2048)
	private String senha;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	@OneToMany(mappedBy = "conta", cascade=CascadeType.ALL)
	private List<Volante> volantes = new LinkedList<>();

	public Conta() {
		addPerfil(Perfil.CLIENTE);
	}
	
	public Conta(Long id, String email, Acesso acesso, Situacao situacao, Usuario usuario, String senha) {
		super();
		super.setId(id);
		this.email = email;
		this.acesso = acesso;
		this.situacao = situacao;
		this.usuario = usuario;
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
	}
	
	@Override
	public void setId(Long id) {
		super.setId(id);
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

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
		
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	public List<Volante> getVolantes() {
		return this.volantes;
	}

	public void setVolantes(List<Volante> volantes) {
		this.volantes = volantes;
	}
}

package br.com.sinaldasorte.domain;

import java.io.Serializable;
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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.sinaldasorte.domain.enums.Perfil;
import br.com.sinaldasorte.domain.enums.Situacoes;

@Entity
public class Conta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/* A geração de chave está no Usuário. Isso foi definido pelo "@MapsId Usuario usuario" 
	 * Como é um relacionamento 1-1 por questões de otimização foi aplicada essa estratégia */
	@Id
	private Long id;
	
	@Column(unique = true, nullable = false, length = 60)
	private String email;
	
	private Integer situacao;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="usuario_id")
	@MapsId
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
		this.setSituacao(Situacoes.ATIVO);
		addPerfil(Perfil.GRATUITO);
	}
	
	public Conta(Long id, String email, Usuario usuario, String senha) {
		super();
		this.id = id;
		this.email = email;
//		this.situacao = (situacao == null) ? null : situacao.getCod();
		this.usuario = usuario;
		this.senha = senha;
		this.setSituacao(Situacoes.ATIVO);
		addPerfil(Perfil.GRATUITO);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public Situacoes getSituacao() {
		return Situacoes.toEnum(situacao);
	}

	public void setSituacao(Situacoes situacao) {
		this.situacao = situacao.getCod();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

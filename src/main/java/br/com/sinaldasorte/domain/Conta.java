package br.com.sinaldasorte.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.sinaldasorte.domain.enums.Perfil;
import br.com.sinaldasorte.domain.enums.Situacoes;

@Audited
@AuditTable(value = "zconta_aud")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Conta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/* A geração de chave está no Usuário. Isso foi definido pelo "@MapsId Usuario usuario" 
	 * Como é um relacionamento 1-1 por questões de otimização foi aplicada essa estratégia */
	@Id
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	private String emailAtualizacao;
	
	private Integer situacao;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	@MapsId
    private Usuario usuario;
	
	@JsonIgnore
	@Column(nullable = false)
	private String senha;
	
	@JsonIgnore
	@Column(unique = true)
	private String hashConfirmacao;
	
	@AuditJoinTable(name="zperfis_aud")
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	@NotAudited
	@OneToMany(mappedBy = "conta", cascade=CascadeType.ALL)
	private List<Volante> volantes = new LinkedList<>();
	
	private String operacao;

	private Date timestamp;

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private Date dataCriacao;

	@LastModifiedDate
	private Date dataModificacao;

	@LastModifiedBy
	private String modificadoPor;

	public Conta() {
		addPerfil(Perfil.GRATUITO);
	}
	
	public Conta(Long id, String email, Usuario usuario, String senha) {
		super();
		this.id = id;
		this.email = email;
		this.usuario = usuario;
		this.senha = senha;
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

	public String getEmailAtualizacao() {
		return emailAtualizacao;
	}

	public void setEmailAtualizacao(String emailAtualizacao) {
		this.emailAtualizacao = emailAtualizacao;
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

	public String getHashConfirmacao() {
		return hashConfirmacao;
	}

	public void setHashConfirmacao(String hashConfirmacao) {
		this.hashConfirmacao = hashConfirmacao;
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
	
	/**
	 * Auditoria
	 * */
	public String getOperacao() {
		return this.operacao;
	}
	
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public String getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	@PrePersist
    public void onPrePersist() {
        audit("CRIAÇÃO");
    }
      
    @PreUpdate
    public void onPreUpdate() {
        audit("ATUALIZAÇÃO");
    }
      
    @PreRemove
    public void onPreRemove() {
        audit("EXCLUSÃO");
    }
      
    private void audit(String operacao) {
        setOperacao(operacao);
        setTimestamp(new Date());
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

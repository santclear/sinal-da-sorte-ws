package br.com.sinaldasorte.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
@AuditTable(value="zlogradouro_aud")
@Entity
public class Logradouro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String cep;
	
	@NotAudited
	private String nome;
	
	@NotAudited
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bairro_id")
	private Bairro bairro;
	
	@NotAudited
	@JsonIgnore
	@OneToMany(mappedBy="logradouro")
	private List<Usuario> usuarios = new LinkedList<>();

	public Logradouro() {}

	public Logradouro(String cep, String nome, Bairro bairro) {
		super();
		this.cep = cep==null?cep:cep.replace("-", "");
		this.nome = nome;
		this.bairro = bairro;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep==null?cep:cep.replace("-", "");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}

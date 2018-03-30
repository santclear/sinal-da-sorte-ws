package br.com.sinaldasorte.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.sinaldasorte.domain.enums.Generos;

@Audited
@AuditTable(value = "zusuario_aud")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false, length = 130)
	private String nome;

	@Column(nullable = false, length = 130)
	private String sobrenome;

	private Integer genero;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt", timezone = "Brazil/East")
	private Date dataDeNascimento;

	@Column(nullable = false)
	private String cpf;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="logradouro_id")
	private Logradouro logradouro;
	
	private String numero;
	
	private String complemento;
	
	@JsonIgnore
	@OneToOne(cascade=CascadeType.ALL, mappedBy="usuario")
	private Conta conta;

	/* Telefone é uma entidade fraca, tem apenas um atributo, não é necessário criar uma classe entidade para ela.
	 * Essas anotações estão definindo que será criado uma tabela chamada TELEFONE no banco de dados.
	 * */
	@AuditJoinTable(name="ztelefone_aud")
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Map<String, String> telefones = new HashMap<>();
	
    private String operacao;
      
    private long timestamp;
    
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private long dataCriacao;
 
    @LastModifiedDate
    private long dataModificacao;
 
    @LastModifiedBy
    private String modificadoPor;

	public Usuario() {}

	public Usuario(Long id, String nome, 
			String sobrenome, Generos genero, 
			Date dataDeNascimento, String cpf, 
			Logradouro logradouro, 
			String numero, String complemento) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.genero = (genero == null) ? null : genero.getCod();
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf==null?cpf:cpf.replaceAll("\\.|-", "");
		this.logradouro = logradouro;
		this.numero = numero;		
		this.complemento = complemento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Integer getGenero() {
		return genero;
	}
	
	public void setGenero(Generos genero) {
		this.genero = genero.getCod();
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf==null?cpf:cpf.replaceAll("\\.|-", "");
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public Map<String, String> getTelefones() {
		return telefones;
	}
	
	public void setTelefones(Map<String, String> telefones) {
		this.telefones = telefones;
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

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public long getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(long dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public long getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(long dataModificacao) {
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
        setTimestamp((new Date()).getTime());
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

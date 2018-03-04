package br.com.sinaldasorte.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sinaldasorte.domain.enums.Generos;

@Entity
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

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt", timezone = "Brazil/East")
	private Date dataDeNascimento;

	@Column(unique = true, nullable = false)
	private String cpf;
	
	private String complemento;

	@ManyToOne
	@JoinColumn(name="logradouro_id")
	private Logradouro logradouro;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="usuario")
	private Conta conta;

	/* Telefone é uma entidade fraca, tem apenas um atributo, não é necessário criar uma classe entidade para ela.
	 * Essas anotações estão definindo que será criado uma tabela chamada TELEFONE no banco de dados.
	 * */
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();

	public Usuario() {}

	public Usuario(Long id, String nome, 
			String sobrenome, Generos genero, 
			Date dataDeNascimento, String cpf, 
			String complemento, Logradouro logradouro) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.genero = (genero == null) ? null : genero.getCod();
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
		this.complemento = complemento;
		this.logradouro = logradouro;		
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

	public void setGenero(Integer genero) {
		this.genero = genero;
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
		this.cpf = cpf;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public Set<String> getTelefones() {
		return telefones.stream().map(x -> x).collect(Collectors.toSet());
	}
		
	public void addTelefone(String telefone) {
		if(Objects.nonNull(telefone) && !"".equals(telefone))telefones.add(telefone);
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

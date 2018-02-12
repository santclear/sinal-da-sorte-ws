package br.com.sinaldasorte.domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 30)
	private String nome;

	@Column(nullable = false, length = 130)
	private String sobrenome;

	@Column(nullable = false, length = 1)
	private char sexo;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false, length = 10)
	private Date dataDeNascimento;

	@Column(nullable = false, length = 11)
	private String cpf;

	@Column(length = 200)
	private String logradouro;

	@Column(length = 60)
	private String cidade;

	@Column(length = 2)
	private String uf;

	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Conta> contas = new LinkedList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Telefone> telefones = new LinkedList<>();

	public Usuario() {}

	public Usuario(String nome, String sobrenome, char sexo, Date dataDeNascimento, String cpf, String logradouro,
			String cidade, String uf) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.uf = uf;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return this.sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public char getSexo() {
		return this.sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Date getDataDeNascimento() {
		return this.dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public List<Conta> getContas() {
		return this.contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public List<Telefone> getTelefones() {
		return this.telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
}

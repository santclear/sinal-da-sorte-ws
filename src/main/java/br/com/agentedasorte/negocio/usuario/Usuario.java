package br.com.agentedasorte.negocio.usuario;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.agentedasorte.negocio.conta.Conta;
import br.com.agentedasorte.negocio.telefone.Telefone;

@Entity
@Table(name = "usuario", catalog = "bdagente")
public class Usuario extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 3087347360833943196L;

	@Column(name = "nome", nullable = false, length = 30)
	private String nome;

	@Column(name = "sobrenome", nullable = false, length = 130)
	private String sobrenome;

	@Column(name = "sexo", nullable = false, length = 1)
	private char sexo;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_de_nascimento", nullable = false, length = 10)
	private Date dataDeNascimento;

	@Column(name = "cpf", nullable = false, length = 11)
	private String cpf;

	@Column(name = "email_recupercao", nullable = false, length = 60)
	private String emailRecupercao;

	@Column(name = "logradouro", length = 200)
	private String logradouro;

	@Column(name = "cidade", length = 60)
	private String cidade;

	@Column(name = "uf", length = 2)
	private String uf;

	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Conta> contas;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Telefone> telefones;

	public Usuario() {
	}

	public Usuario(String nome, String sobrenome, char sexo, Date dataDeNascimento, String cpf, String emailRecupercao) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
		this.emailRecupercao = emailRecupercao;
	}

	public Usuario(String nome, String sobrenome, char sexo, Date dataDeNascimento, String cpf, String emailRecupercao, String logradouro, String cidade, String uf, List<Conta> contas,
			List<Telefone> telefones) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
		this.emailRecupercao = emailRecupercao;
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.uf = uf;
		this.contas = contas;
		this.telefones = telefones;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((contas == null) ? 0 : contas.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dataDeNascimento == null) ? 0 : dataDeNascimento.hashCode());
		result = prime * result + ((emailRecupercao == null) ? 0 : emailRecupercao.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + sexo;
		result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
		result = prime * result + ((telefones == null) ? 0 : telefones.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (contas == null) {
			if (other.contas != null)
				return false;
		} else if (!contas.equals(other.contas))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataDeNascimento == null) {
			if (other.dataDeNascimento != null)
				return false;
		} else if (!dataDeNascimento.equals(other.dataDeNascimento))
			return false;
		if (emailRecupercao == null) {
			if (other.emailRecupercao != null)
				return false;
		} else if (!emailRecupercao.equals(other.emailRecupercao))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sexo != other.sexo)
			return false;
		if (sobrenome == null) {
			if (other.sobrenome != null)
				return false;
		} else if (!sobrenome.equals(other.sobrenome))
			return false;
		if (telefones == null) {
			if (other.telefones != null)
				return false;
		} else if (!telefones.equals(other.telefones))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
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

	public String getEmailRecupercao() {
		return this.emailRecupercao;
	}

	public void setEmailRecupercao(String emailRecupercao) {
		this.emailRecupercao = emailRecupercao;
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

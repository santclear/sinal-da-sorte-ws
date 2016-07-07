package agentedasorte.negocio.volante.negocio.usuario;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "usuario", catalog = "bdagente")
public class Usuario implements java.io.Serializable {

	private Integer id;
	private String nome;
	private String sobrenome;
	private char sexo;
	private Date dataDeNascimento;
	private String cpf;
	private String emailRecupercao;
	private String logradouro;
	private String cidade;
	private String uf;
	private Set contas = new HashSet(0);
	private Set telefones = new HashSet(0);

	public Usuario() {
	}

	public Usuario(String nome, String sobrenome, char sexo, Date dataDeNascimento, String cpf,
			String emailRecupercao) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
		this.emailRecupercao = emailRecupercao;
	}

	public Usuario(String nome, String sobrenome, char sexo, Date dataDeNascimento, String cpf, String emailRecupercao,
			String logradouro, String cidade, String uf, Set contas, Set telefones) {
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

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nome", nullable = false, length = 30)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "sobrenome", nullable = false, length = 130)
	public String getSobrenome() {
		return this.sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	@Column(name = "sexo", nullable = false, length = 1)
	public char getSexo() {
		return this.sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_de_nascimento", nullable = false, length = 10)
	public Date getDataDeNascimento() {
		return this.dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	@Column(name = "cpf", nullable = false, length = 11)
	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "email_recupercao", nullable = false, length = 60)
	public String getEmailRecupercao() {
		return this.emailRecupercao;
	}

	public void setEmailRecupercao(String emailRecupercao) {
		this.emailRecupercao = emailRecupercao;
	}

	@Column(name = "logradouro", length = 200)
	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Column(name = "cidade", length = 60)
	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "uf", length = 2)
	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set getContas() {
		return this.contas;
	}

	public void setContas(Set contas) {
		this.contas = contas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set getTelefones() {
		return this.telefones;
	}

	public void setTelefones(Set telefones) {
		this.telefones = telefones;
	}

}

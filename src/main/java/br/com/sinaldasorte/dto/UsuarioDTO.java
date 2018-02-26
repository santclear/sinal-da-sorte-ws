package br.com.sinaldasorte.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UsuarioDTO {

    private Long id;
    @NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=130, message="O tamanho de ser entre 3 e 130 caracteres")
	private String nome;
    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=3, max=130, message="O tamanho de ser entre 3 e 130 caracteres")
	private String sobrenome;
    @NotEmpty(message="Preenchimento obrigatório")
	private Integer genero;
    @NotEmpty(message="Preenchimento obrigatório")
	private String dataDeNascimento;
    @NotEmpty(message="Preenchimento obrigatório")
	private String cpf;
    @NotEmpty(message="Preenchimento obrigatório")
	private String logradouro;
	private String complemento;
	@NotEmpty(message="Preenchimento obrigatório")
	private String cep;
	@NotEmpty(message="Preenchimento obrigatório")
	private String bairro;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private Long cidadeId;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=11, max=30, message="O tamanho de ser entre 11 e 30 caracteres")
	private String telefone1;
	@Length(min=11, max=30, message="O tamanho de ser entre 11 e 30 caracteres")
	private String telefone2;
	@Length(min=11, max=130, message="O tamanho de ser entre 11 e 30 caracteres")
	private String telefone3;
	
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
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public Long getCidadeId() {
		return cidadeId;
	}
	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}
	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public String getTelefone3() {
		return telefone3;
	}
	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}
}

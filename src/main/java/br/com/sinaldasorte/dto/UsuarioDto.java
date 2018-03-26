package br.com.sinaldasorte.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class UsuarioDto {

    private Long id;
    @NotEmpty(message="Preenchimento obrigatório")
	private String nome;
    
    @NotEmpty(message="Preenchimento obrigatório")
	private String sobrenome;
    
    @NotEmpty(message="Preenchimento obrigatório")
    private String cpf;
    
    @NotEmpty(message="Preenchimento obrigatório")
    private String dataDeNascimento;
    
    @NotEmpty(message="Preenchimento obrigatório")
	private String genero;
    
    private EnderecoDto endereco;

	@NotEmpty(message="Preenchimento obrigatório")
	private String telefone1;
	
	private String telefone2;
	
	private String telefone3;
	
	public UsuarioDto() {}

	public UsuarioDto(Long id, String nome, String sobrenome, String cpf, String dataDeNascimento, String genero,
			EnderecoDto endereco, String telefone1, String telefone2, String telefone3) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
		this.genero = genero;
		this.endereco = endereco;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.telefone3 = telefone3;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public EnderecoDto getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDto endereco) {
		this.endereco = endereco;
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

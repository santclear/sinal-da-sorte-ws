package br.com.sinaldasorte.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Bairro;
import br.com.sinaldasorte.domain.Cidade;
import br.com.sinaldasorte.domain.Logradouro;
import br.com.sinaldasorte.domain.UF;
import br.com.sinaldasorte.domain.Usuario;
import br.com.sinaldasorte.domain.enums.Generos;
import br.com.sinaldasorte.domain.enums.Perfil;
import br.com.sinaldasorte.dto.UsuarioDto;
import br.com.sinaldasorte.repository.UsuarioRepository;
import br.com.sinaldasorte.security.ContaAuth;
import br.com.sinaldasorte.service.exceptions.AutorizacaoException;
import br.com.sinaldasorte.service.exceptions.ObjetoNaoEncontradoException;
import br.com.sinaldasorte.service.exceptions.ProcessamentoInternoException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private UFService ufService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private BairroService bairroService;
	
	@Autowired
	private LogradouroService logradouroService;
	
	public Usuario procure(Long id) {
		ContaAuth conta = UserService.autenticado();
		// O usuário logado procurar por ele mesmo, a não ser que ele tenha perfil de ADMIN
		// Se o usuário não estiver logado, ou o perfil não for de ADMIN e não for o id 
		// passado por parâmetro não for dele mesmo então o acesso será negado
		if (conta == null || !conta.hasRole(Perfil.ADMIN) && !id.equals(conta.getId())) {
			throw new AutorizacaoException("Acesso negado");
		}
		
		Usuario obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjetoNaoEncontradoException("Objeto não encontrado Id: "+ id +", Tipo: "+ Usuario.class.getName());
		}
		return obj;
	}
	
	public List<Usuario> procureTodos() {
		return repo.findAll();
	}
	
	public Usuario encontrePorEmail(String email) {
		
		ContaAuth conta = UserService.autenticado();
		if (conta==null || !conta.hasRole(Perfil.ADMIN) && !email.equals(conta.getUsername())) {
			throw new AutorizacaoException("Acesso negado");
		}
		
		Usuario obj = repo.findOne(conta.getId());
		if (obj == null) {
			throw new ObjetoNaoEncontradoException("Objeto não encontrado! Id: "+ conta.getId() +", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}
	
	
	public Usuario atualize(Usuario obj) {
	Usuario newObj = procure(obj.getId());
		atualizeDados(newObj, obj);
		// O método save do Spring Data realiza operações de save e update. Se o id for nulo ele salva e se não for atualiza.
		return repo.save(obj);
	}
	
	public void atualizeDados(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setSobrenome(obj.getSobrenome());
		newObj.setCpf(obj.getCpf());
		newObj.setDataDeNascimento(obj.getDataDeNascimento());
		newObj.setGenero(Generos.toEnum(obj.getGenero()));
		newObj.setLogradouro(obj.getLogradouro());
		newObj.setNumero(obj.getNumero());
		newObj.setComplemento(obj.getComplemento());
		newObj.setTelefones(obj.getTelefones());
	}
	
	public Usuario dtoParaEntidade(UsuarioDto dto) {
		UF uf = this.ufService.procure(dto.getEndereco().getUf());
		if(uf == null) uf = this.ufService.insira(new UF(null, dto.getEndereco().getUf()));
		
		Cidade cidade = this.cidadeService.procure(dto.getEndereco().getCidade(), dto.getEndereco().getUf());
		if(cidade == null) cidade = this.cidadeService.insira(new Cidade(null, dto.getEndereco().getCidade(), uf));
		
		Bairro bairro = this.bairroService.procure(dto.getEndereco().getBairro(), dto.getEndereco().getCidade(), dto.getEndereco().getUf());
		if(bairro == null) bairro = this.bairroService.insira(new Bairro(null, dto.getEndereco().getBairro(), cidade));
		
		Logradouro logradouro = logradouroService.procure(dto.getEndereco().getCep());
		if(logradouro == null) logradouro = this.logradouroService.insira(new Logradouro(dto.getEndereco().getCep(), dto.getEndereco().getLogradouro(), bairro));
		
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
		Date dataNascimento = null;
		try {
			dataNascimento = formatador.parse(dto.getDataDeNascimento());
			System.out.println(dataNascimento);
		} catch (ParseException e) {
			throw new ProcessamentoInternoException("Ocorreu um erro ao tentar inserir a data de nascimento.");
		}
		
		Usuario usuario = new Usuario(
				dto.getId(), dto.getNome(), dto.getSobrenome(), 
				Generos.toEnum(dto.getGenero()), dataNascimento, dto.getCpf(), 
				logradouro, dto.getEndereco().getNumero(), dto.getEndereco().getComplemento());
		List<String> telefones = new LinkedList<>();
		telefones.add(Objects.isNull(dto.getTelefone1()) || "".equals(dto.getTelefone1())?null:dto.getTelefone1());
		telefones.add(Objects.isNull(dto.getTelefone2()) || "".equals(dto.getTelefone2())?null:dto.getTelefone2());
		telefones.add(Objects.isNull(dto.getTelefone3()) || "".equals(dto.getTelefone3())?null:dto.getTelefone3());
		usuario.setTelefones(telefones);
		
		return usuario;
	}
	
	  
}

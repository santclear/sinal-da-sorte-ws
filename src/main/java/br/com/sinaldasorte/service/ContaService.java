package br.com.sinaldasorte.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Bairro;
import br.com.sinaldasorte.domain.Cidade;
import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.domain.Logradouro;
import br.com.sinaldasorte.domain.UF;
import br.com.sinaldasorte.domain.Usuario;
import br.com.sinaldasorte.domain.enums.Generos;
import br.com.sinaldasorte.domain.enums.Perfil;
import br.com.sinaldasorte.domain.enums.Situacoes;
import br.com.sinaldasorte.dto.ContaDto;
import br.com.sinaldasorte.dto.ContaNewDto;
import br.com.sinaldasorte.dto.UsuarioDto;
import br.com.sinaldasorte.repository.ContaRepository;
import br.com.sinaldasorte.security.ContaAuth;
import br.com.sinaldasorte.service.exceptions.AutorizacaoException;
import br.com.sinaldasorte.service.exceptions.ObjetoNaoEncontradoException;
import br.com.sinaldasorte.util.Util;

@Service
public class ContaService {
	
	@Autowired
	private BCryptPasswordEncoder encriptadorDeSenha;
	
	@Autowired
	private ContaRepository repo;
	
	@Autowired
	private UFService ufService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private BairroService bairroService;
	
	@Autowired
	private LogradouroService logradouroService;
	
	@Autowired
	private EmailService emailService;
	
	public Conta procure(Long id) {
		ContaAuth conta = UserService.autenticado();
		// O usuário logado procurar por ele mesmo, a não ser que ele tenha perfil de ADMIN
		// Se o usuário não estiver logado, ou o perfil não for de ADMIN e não for o id 
		// passado por parâmetro não for dele mesmo então o acesso será negado
		if (conta == null || !conta.hasRole(Perfil.ADMIN) && !id.equals(conta.getId())) {
			throw new AutorizacaoException("Acesso negado");
		}
		
		Conta obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjetoNaoEncontradoException("Objeto não encontrado Id: "+ id +", Tipo: "+ Conta.class.getName());
		}
		return obj;
	}
	
	public Conta insira(Conta obj) {
		obj.setId(null);
		repo.save(obj);
		return obj;
	}
	
	public Conta atualize(Conta obj) {
		Conta newObj = procure(obj.getId());
		atualizeDados(newObj, obj);
		// O método save do Spring Data realiza operações de save e update. Se o id for nulo ele salva e se não for atualiza.
		return repo.save(newObj);
	}
	
	public Conta atualizePorHashConfirmacao(Conta obj) {
		Conta newObj = procurePorHashConfirmacao(obj.getHashConfirmacao());
		newObj.setHashConfirmacao("CONFIRMADO");
		atualizeDados(newObj, obj);
		// O método save do Spring Data realiza operações de save e update. Se o id for nulo ele salva e se não for atualiza.
		return repo.save(newObj);
	}
	
	//FIXME Essa operação deve atualizar a 'situacao' para 'INATIVO'
	public void delete(Long id) {
//		find(id);
//		try {
//			repo.delete(id);
//		} catch(DataIntegrityViolationException e) {
//			throw new DataIntegrityException("Alguma msg");
//		}
	}
	
	public List<Conta> procureTodos() {
		return repo.findAll();
	}
	
	public Conta procurePorEmail(String email) {
		
		ContaAuth conta = UserService.autenticado();
		if (conta==null || !conta.hasRole(Perfil.ADMIN) && !email.equals(conta.getUsername())) {
			throw new AutorizacaoException("Acesso negado");
		}
		
		Conta obj = repo.findOne(conta.getId());
		if (obj == null) {
			throw new ObjetoNaoEncontradoException("Objeto não encontrado! Id: "+ conta.getId() +", Tipo: " + Conta.class.getName());
		}
		return obj;
	}
	
	public Conta procurePorHashConfirmacao(String hash) {
		
		Conta obj = repo.findByHashConfirmacao(hash);
		if(obj == null) {
			throw new ObjetoNaoEncontradoException("Objeto não encontrado Hash: "+ hash +", Tipo: "+ Conta.class.getName());
		}
		
		return obj;
	}
	
	// Page encapsula paginação. A contagem de página inicia em zero. O atributo direction é o tipo de ordenação descentende ou ascendente
	public Page<Conta> procurePagina(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Conta dtoParaEntidade(ContaDto objDTO) {
		return new Conta(objDTO.getId(), objDTO.getEmail(), null, null);
	}
	
	public Conta dtoParaEntidade(ContaNewDto objDTO) throws ParseException {
		UsuarioDto usuariDto = objDTO.getUsuario();
		
		UF uf = this.ufService.procure(objDTO.getUsuario().getEndereco().getUf());
		if(uf == null) uf = this.ufService.insira(new UF(null, objDTO.getUsuario().getEndereco().getUf()));
		
		Cidade cidade = this.cidadeService.procure(objDTO.getUsuario().getEndereco().getCidade());
		if(cidade == null) cidade = this.cidadeService.insira(new Cidade(null, objDTO.getUsuario().getEndereco().getCidade(), uf));
		
		Bairro bairro = this.bairroService.procure(objDTO.getUsuario().getEndereco().getBairro());
		if(bairro == null) bairro = this.bairroService.insira(new Bairro(null, objDTO.getUsuario().getEndereco().getBairro(), cidade));
		
		Logradouro logradouro = logradouroService.procure(usuariDto.getEndereco().getCep());
		if(logradouro == null) logradouro = this.logradouroService.insira(new Logradouro(usuariDto.getEndereco().getCep(), usuariDto.getEndereco().getLogradouro(), bairro));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Usuario usuario = new Usuario(
				null,
				usuariDto.getNome(),
				usuariDto.getSobrenome(),
				Generos.toEnum(usuariDto.getGenero()),
				sdf.parse(usuariDto.getDataDeNascimento()),
				usuariDto.getCpf(),
				usuariDto.getEndereco().getComplemento(),
				logradouro);
		usuario.addTelefone(usuariDto.getTelefone1());
		usuario.addTelefone(usuariDto.getTelefone2());
		usuario.addTelefone(usuariDto.getTelefone3());
		Conta conta = new Conta(null, objDTO.getEmail(), usuario, encriptadorDeSenha.encode(objDTO.getSenha()));
		conta.setSituacao(Situacoes.INATIVO);
		conta.setHashConfirmacao(Util.novaHash());
		
		this.emailService.envieLinkConfirmacaoCadastroConta(conta);
		
		return conta;
	}
	
	private void atualizeDados(Conta newObj, Conta obj) {
		newObj.setEmail(obj.getEmail());
	}
}

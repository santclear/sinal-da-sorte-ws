package br.com.sinaldasorte.service;

import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.domain.Usuario;
import br.com.sinaldasorte.domain.enums.Perfil;
import br.com.sinaldasorte.domain.enums.Situacoes;
import br.com.sinaldasorte.dto.ContaDto;
import br.com.sinaldasorte.dto.ContaNewDto;
import br.com.sinaldasorte.repository.ContaRepository;
import br.com.sinaldasorte.security.ContaAuth;
import br.com.sinaldasorte.service.exceptions.AutorizacaoException;
import br.com.sinaldasorte.service.exceptions.ObjetoNaoEncontradoException;
import br.com.sinaldasorte.service.exceptions.enums.MensagensExceptions;
import br.com.sinaldasorte.service.interfaces.EmailService;
import br.com.sinaldasorte.util.Util;

@Service
public class ContaService {
	
	@Autowired
	private BCryptPasswordEncoder encriptadorDeSenha;
	
	@Autowired
	private ContaRepository repo;
	
	@Autowired
	private UsuarioService usuarioService;
	
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
		obj.setHashConfirmacao(Util.novoHash());
		obj.setSituacao(Situacoes.ATIVO);
		this.emailService.envieLinkConfirmacaoCadastroConta(obj);
		obj.setSituacao(Situacoes.INATIVO);
		obj.setId(null);
		repo.save(obj);
		return obj;
	}
	
	public Conta atualize(Conta obj) {		
		Conta newObj = procure(obj.getId());
		
		usuarioService.atualizeDados(newObj.getUsuario(), obj.getUsuario());		
		
		if(newObj.getEmail().equals(obj.getEmail())) {
			atualizeDados(newObj, obj);
			return repo.save(newObj);
		} else if(!newObj.getEmail().equals(obj.getEmail()) && !encriptadorDeSenha.matches(obj.getSenha(), newObj.getSenha())) {
			newObj.setHashConfirmacao(Util.novoHash());
			newObj.setEmailAtualizacao(obj.getEmail());
			newObj.setSenha(encriptadorDeSenha.encode(obj.getSenha()));
		} else {
			newObj.setHashConfirmacao(Util.novoHash());
			newObj.setEmailAtualizacao(obj.getEmail());
		}
		Conta conta = repo.save(newObj);
		newObj.setEmail(obj.getEmail());
		this.emailService.envieLinkConfirmarAtualizacaoEmail(newObj);
		return conta;
	}
	
	public Conta atualizePorHashConfirmacao(Conta newObj) {
		if(Objects.nonNull(newObj.getEmailAtualizacao())) {
			newObj.setEmail(newObj.getEmailAtualizacao());
			newObj.setEmailAtualizacao(null);
		}
		newObj.setSituacao(Situacoes.ATIVO);
		newObj.setHashConfirmacao(Util.novoHash() + Util.novoHash());
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
	
	public Conta encontrePorEmail(String email) {
		
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
			Logger.getInstance(ContaService.class).info("Objeto não encontrado Hash: "+ hash +", Tipo: "+ Conta.class.getName());
			throw new ObjetoNaoEncontradoException("Objeto não encontrado Hash: "+ hash);
		}
		
		return obj;
	}
	
	// Page encapsula paginação. A contagem de página inicia em zero. O atributo direction é o tipo de ordenação descentende ou ascendente
	public Page<Conta> procurePagina(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Conta dtoParaEntidade(ContaDto objDto) {
		ContaAuth conta = UserService.autenticado();
		if(!encriptadorDeSenha.matches(objDto.getSenha(), conta.getPassword())) {
			throw new AutorizacaoException(MensagensExceptions.SENHA_INVALIDA.getCod());
		}
		
		if(Objects.nonNull(objDto.getNovaSenha()) && !"".equals(objDto.getNovaSenha())) objDto.setSenha(objDto.getNovaSenha());
		
		Usuario usuario = this.usuarioService.dtoParaEntidade(objDto.getUsuario());
		return new Conta(objDto.getId(), objDto.getEmail(), usuario, objDto.getSenha());
	}
	
	public Conta dtoParaEntidade(ContaNewDto objDto) {
		Usuario usuario = this.usuarioService.dtoParaEntidade(objDto.getUsuario());
		return new Conta(null, objDto.getEmail(), usuario, encriptadorDeSenha.encode(objDto.getSenha()));
	}
	
	private void atualizeDados(Conta newObj, Conta obj) {
		newObj.setEmail(obj.getEmail());
		if(Objects.nonNull(obj.getSenha())) newObj.setSenha(encriptadorDeSenha.encode(obj.getSenha()));
	}
}

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

import br.com.sinaldasorte.domain.Cidade;
import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.domain.Usuario;
import br.com.sinaldasorte.domain.enums.Generos;
import br.com.sinaldasorte.domain.enums.Perfil;
import br.com.sinaldasorte.dto.ContaDto;
import br.com.sinaldasorte.dto.ContaNewDto;
import br.com.sinaldasorte.dto.UsuarioDto;
import br.com.sinaldasorte.repository.CidadeRepository;
import br.com.sinaldasorte.repository.ContaRepository;
import br.com.sinaldasorte.security.ContaAuth;
import br.com.sinaldasorte.service.exceptions.AuthorizationException;
import br.com.sinaldasorte.service.exceptions.ObjectNotFoundException;

@Service
public class ContaService {
	
	@Autowired
	private BCryptPasswordEncoder encriptadorDeSenha;
	
	@Autowired
	private ContaRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	public Conta find(Long id) {
		ContaAuth conta = UserService.authenticated();
		// O usuário logado procurar por ele mesmo, a não ser que ele tenha perfil de ADMIN
		// Se o usuário não estiver logado, ou o perfil não for de ADMIN e não for o id 
		// passado por parâmetro não for dele mesmo então o acesso será negado
		if (conta == null || !conta.hasRole(Perfil.ADMIN) && !id.equals(conta.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Conta obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado Id: "+ id +", Tipo: "+ Conta.class.getName());
		}
		return obj;
	}
	
	public Conta insert(Conta obj) {
		obj.setId(null);
		repo.save(obj);
		return obj;
	}
	
	public Conta update(Conta obj) {
		Conta newObj = find(obj.getId());
		updateData(newObj, obj);
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
	
	public List<Conta> findAll() {
		return repo.findAll();
	}
	
	public Conta findByEmail(String email) {
		
		ContaAuth conta = UserService.authenticated();
		if (conta==null || !conta.hasRole(Perfil.ADMIN) && !email.equals(conta.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Conta obj = repo.findOne(conta.getId());
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: "+ conta.getId() +", Tipo: " + Conta.class.getName());
		}
		return obj;
	}
	
	// Page encapsula paginação. A contagem de página inicia em zero. O atributo direction é o tipo de ordenação descentende ou ascendente
	public Page<Conta> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Conta fromDTO(ContaDto objDTO) {
		return new Conta(objDTO.getId(), objDTO.getEmail(), null, null);
	}
	
	public Conta fromDTO(ContaNewDto objDTO) throws ParseException {
		UsuarioDto usuariDto = objDTO.getUsuario();
		Cidade cidade = cidadeRepo.findOne(usuariDto.getCidadeId());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Usuario usuario = new Usuario(
				null,
				usuariDto.getNome(),
				usuariDto.getSobrenome(),
				Generos.toEnum(usuariDto.getGenero()),
				sdf.parse(usuariDto.getDataDeNascimento()),
				usuariDto.getCpf(),
				usuariDto.getLogradouro(),
				usuariDto.getComplemento(),
				usuariDto.getCep(),
				usuariDto.getBairro(),
				cidade);
//		new Conta(id, email, usuario, senha);
		Conta conta = new Conta(null, objDTO.getEmail(), usuario, encriptadorDeSenha.encode(objDTO.getSenha()));
		
		return conta;
	}
	
	private void updateData(Conta newObj, Conta obj) {
		newObj.setEmail(obj.getEmail());
	}
}

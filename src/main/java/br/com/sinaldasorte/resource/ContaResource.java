package br.com.sinaldasorte.resource;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.dto.ContaDto;
import br.com.sinaldasorte.dto.ContaNewDto;
import br.com.sinaldasorte.service.ContaService;

@RestController
@RequestMapping(value="/contas")
public class ContaResource {
	
	@Autowired
	private ContaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Conta> procure(@PathVariable Long id) {
		Conta obj = service.procure(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/encontrePorEmail", method=RequestMethod.GET)
	public ResponseEntity<Conta> encontre(@RequestParam(value="value") String email) {
		// Entidade Conta ao invés da ContaDTO para que seja carregado todos os dados da Conta
		Conta obj = service.encontrePorEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insira(@Valid @RequestBody ContaNewDto objDTO) {
		Conta obj = service.dtoParaEntidade(objDTO);
		obj = service.insira(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public  ResponseEntity<Void> atualize(@Valid @RequestBody ContaDto objDTO, @PathVariable Long id) {
		Conta obj = service.dtoParaEntidade(objDTO);
		obj = service.atualize(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/exclua/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> exclua(@Valid @RequestBody ContaDto objDTO, @PathVariable Long id) {
		Conta obj = service.dtoParaEntidade(objDTO);
		service.exclua(obj);
		return ResponseEntity.noContent().build();
	}
	
//	// Somente perfil ADMIN pode listar todas as contas
//	@PreAuthorize("hasAnyRole('ADMIN')")
//	@RequestMapping(method=RequestMethod.GET)
//	public ResponseEntity<List<ContaDto>> procureTodos() {
//		List<Conta> list = service.procureTodos();
//		List<ContaDto> listDTO = list.stream().map(obj -> new ContaDto(obj)).collect(Collectors.toList());
//		return ResponseEntity.ok().body(listDTO);
//	}
//	
//	// Somente perfil ADMIN pode paginar paginar as contas 
//	@PreAuthorize("hasAnyRole('ADMIN')")
//	@RequestMapping(value="/page", method=RequestMethod.GET)
//	public ResponseEntity<Page<ContaDto>> procurePagina(
//			@RequestParam(value="page", defaultValue="0") Integer page, 
//			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
//			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
//			@RequestParam(value="direction", defaultValue="DESC") String direction) {
//		Page<Conta> list = service.procurePagina(page, linesPerPage, orderBy, direction);
//		Page<ContaDto> listDTO = list.map(obj -> new ContaDto(obj));
//		return ResponseEntity.ok().body(listDTO);
//	}
}

package br.com.sinaldasorte.resource;

import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.domain.enums.Situacoes;
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
	
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<Conta> procure(@RequestParam(value="value") String email) {
		// Entidade Conta ao invés da ContaDTO para que seja carregado todos os dados da Conta
		Conta obj = service.procurePorEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insira(@Valid @RequestBody ContaNewDto objDTO) throws ParseException {
		Conta obj = service.dtoParaEntidade(objDTO);
		obj = service.insira(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public  ResponseEntity<Void> atualize(@Valid @RequestBody ContaDto objDTO, @PathVariable Long id) {
		Conta obj = service.dtoParaEntidade(objDTO);
		obj.setId(id);
		obj = service.atualize(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/cadastro/confirme", method=RequestMethod.GET)
	public  ResponseEntity<String> atualize(@RequestParam(value="value") String hash) {
		Conta obj = service.procurePorHashConfirmacao(hash);
		obj.setSituacao(Situacoes.ATIVO);
		obj = service.atualizePorHashConfirmacao(obj);
		
		StringBuilder documento = new StringBuilder();
		documento.append("<!DOCTYPE html>\n<html lang=\"pt-BR\">");
		documento.append("<head><title>Sinal da Sorte</title>");
		documento.append("<meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge,chrome=1\">");
		documento.append("<meta name=\"viewport\" content=\"maximum-scale=1.0,width=device-width,initial-scale=1.0,user-scalable=0\">");
		documento.append("</head>");
		documento.append("<body>");
		documento.append("<h1>E-mail confirmado com sucesso!</h1>");
		documento.append("Busque a sorte clicando no link: <a href=\"http://localhost:8100/\">Sinal da Sorte</a>");
		documento.append("</body>");
		documento.append("</html>");
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand().toUri();
		return ResponseEntity.created(uri).body(documento.toString());
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	// Somente perfil ADMIN pode listar todas as contas
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ContaDto>> procureTodos() {
		List<Conta> list = service.procureTodos();
		List<ContaDto> listDTO = list.stream().map(obj -> new ContaDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	// Somente perfil ADMIN pode paginar paginar as contas 
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ContaDto>> procurePagina(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<Conta> list = service.procurePagina(page, linesPerPage, orderBy, direction);
		Page<ContaDto> listDTO = list.map(obj -> new ContaDto(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}

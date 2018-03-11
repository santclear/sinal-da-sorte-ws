package br.com.sinaldasorte.resource;

import java.net.URI;
import java.rmi.RemoteException;
import java.util.List;

import javax.validation.Valid;
import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sinaldasorte.domain.Logradouro;
import br.com.sinaldasorte.dto.EnderecoCorreiosDto;
import br.com.sinaldasorte.dto.LogradouroDto;
import br.com.sinaldasorte.service.LogradouroService;

@RestController
@RequestMapping(value="/logradouros")
public class LogradouroResource {
	
	@Autowired
	private LogradouroService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Logradouro> procure(String cep) {	
		return ResponseEntity.ok().body(service.procure(cep));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> procure(@Valid @RequestBody LogradouroDto objDTO) {
		Logradouro obj = service.dtoParaEntidade(objDTO);
		obj = service.insira(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCep()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	public List<Logradouro> procureTodos() {
		return service.procureTodos();
	}
	
	@RequestMapping(value = "/cep/{cep}", method = RequestMethod.GET)
	public ResponseEntity<EnderecoCorreiosDto> procureCepCorreios(@PathVariable String cep) {
		return ResponseEntity.ok().body(service.procureCepCorreios(cep));
	}
}

package br.com.sinaldasorte.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sinaldasorte.domain.Usuario;
import br.com.sinaldasorte.dto.UsuarioDto;
import br.com.sinaldasorte.service.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Usuario> procure(@PathVariable Long id) {
		Usuario obj = service.procure(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/encontrePorEmail", method=RequestMethod.GET)
	public ResponseEntity<Usuario> encontre(@RequestParam(value="value") String email) {
		// Entidade Usuario ao invés da UsuarioDTO para que seja carregado todos os dados da Usuario
		Usuario obj = service.encontrePorEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public  ResponseEntity<Void> atualize(@Valid @RequestBody UsuarioDto objDTO, @PathVariable Long id) {
		Usuario obj = service.dtoParaEntidade(objDTO);
		obj.setId(id);
		obj = service.atualize(obj);
		return ResponseEntity.noContent().build();
	}
	 
}

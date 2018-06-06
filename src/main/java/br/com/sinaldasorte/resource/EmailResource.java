package br.com.sinaldasorte.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sinaldasorte.dto.ContatoDto;
import br.com.sinaldasorte.service.interfaces.EmailService;

@RestController
@RequestMapping(value = "/emails")
public class EmailResource {
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value="/envie", method=RequestMethod.POST)
	public ResponseEntity<Void> envie(@Valid @RequestBody ContatoDto dto) {
		this.emailService.envieContato(dto);
		return ResponseEntity.noContent().build();
	}

}

package br.com.sinaldasorte.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sinaldasorte.dto.UtilDto;

@RestController
@RequestMapping(value="/util")
public class UtilResource {

	@RequestMapping(value="/ping",method=RequestMethod.GET)
	public ResponseEntity<UtilDto> ping() {
		UtilDto obj = new UtilDto("pong");
		return ResponseEntity.ok().body(obj);
	}
}

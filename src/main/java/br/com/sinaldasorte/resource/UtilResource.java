package br.com.sinaldasorte.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sinaldasorte.dto.UtilDto;
import br.com.sinaldasorte.service.CaptchaService;
import br.com.sinaldasorte.service.UtilService;

@RestController
@RequestMapping(value="/util")
public class UtilResource {
	
	@Autowired
	private UtilService service;
	
	@Autowired
	private CaptchaService captchaService;

	@RequestMapping(value="/ping",method=RequestMethod.GET)
	public ResponseEntity<UtilDto> ping() {
		UtilDto obj = new UtilDto("pong");
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/reCaptchaProcessResponse/{response}",method=RequestMethod.POST)
	public ResponseEntity<Void> reCaptchaProcessResponse(final @PathVariable String response) {
		this.captchaService.processResponse(response);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/limpeCache",method=RequestMethod.GET)
	public ResponseEntity<Void> limpeCache() {
		this.service.reportCacheEvict();
		return ResponseEntity.noContent().build();
	}
}

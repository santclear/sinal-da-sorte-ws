package br.com.sinaldasorte.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sinaldasorte.domain.Cidade;
import br.com.sinaldasorte.domain.Estado;
import br.com.sinaldasorte.dto.CidadeDto;
import br.com.sinaldasorte.dto.EstadoDto;
import br.com.sinaldasorte.service.CidadeService;
import br.com.sinaldasorte.service.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDto>> findAll() {
		List<Estado> list = service.findAll();
		List<EstadoDto> listDto = list.stream().map(obj -> new EstadoDto(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{estadoId}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDto>> findCidades(@PathVariable Long estadoId) {
		List<Cidade> list = cidadeService.findByEstado(estadoId);
		List<CidadeDto> listDto = list.stream().map(obj -> new CidadeDto(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
}

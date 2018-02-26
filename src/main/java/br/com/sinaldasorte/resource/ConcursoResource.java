package br.com.sinaldasorte.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sinaldasorte.domain.Concurso;
import br.com.sinaldasorte.dto.EstatisticaDto;
import br.com.sinaldasorte.service.ConcursoService;

//URL http://localhost:8080/jsondoc-ui.html > http://localhost:8080/jsondoc
@RestController
@RequestMapping(value = "/concursos")
public class ConcursoResource {
	
	@Autowired
	private ConcursoService service;
	
	@RequestMapping(value = "procureConcursosComNumeroMaiorQue/numero={numero}&idLoteria={idLoteria}", method = RequestMethod.GET)
	public List<Concurso> procureConcursosComNumeroMaiorQue(@PathVariable Integer numero, @PathVariable Long idLoteria) {
		return this.service.procureConcursosComNumeroMaiorQue(numero, idLoteria);
	}
	
	@RequestMapping(value = "procureConcursosComNumeroMenorQue/numero={numero}&idLoteria={idLoteria}", method = RequestMethod.GET)
	public List<Concurso> procureConcursosComNumeroMenorQue(@PathVariable Integer numero, @PathVariable Long idLoteria) {
		return this.service.procureConcursosComNumeroMenorQue(numero, idLoteria);
	}
	
	@RequestMapping(value = "calculeFrequenciasTotaisDasDezenas/idLoteria={idLoteria}&numeroSorteio={numeroDoSorteio}", method = RequestMethod.GET)
	public List<EstatisticaDto> calculeFrequenciasTotaisDasDezenas(@PathVariable Long idLoteria, @PathVariable Integer numeroDoSorteio) {		
		return this.service.calculeFrequenciasTotaisDasDezenas(idLoteria, numeroDoSorteio);
	}
}

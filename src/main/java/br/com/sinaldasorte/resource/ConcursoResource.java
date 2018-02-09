package br.com.sinaldasorte.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sinaldasorte.domain.Concurso;
import br.com.sinaldasorte.dto.EstatisticaDTO;
import br.com.sinaldasorte.service.ConcursoService;

//URL http://localhost:8080/jsondoc-ui.html > http://localhost:8080/jsondoc
@RestController
@RequestMapping(value = "/concursos")
public class ConcursoResource {
	
	@Autowired
	private ConcursoService service;

	// URL http://localhost:8080/concursos/procure_por_loteria_id_igual_a_e_numero_maior_que/{loteriaId}&{numero}
//	@CrossOrigin(origins = "http://localhost:8100")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "procureConcursosComNumeroMaiorQue/numero={numero}&idLoteria={idLoteria}", method = RequestMethod.GET)
	public List<Concurso> procureConcursosComNumeroMaiorQue(@PathVariable Integer numero, @PathVariable Long idLoteria) {
		return this.service.procureConcursosComNumeroMaiorQue(numero, idLoteria);
	}
//	@CrossOrigin(origins = "http://localhost:8100")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "procureConcursosComNumeroMenorQue/numero={numero}&idLoteria={idLoteria}", method = RequestMethod.GET)
	public List<Concurso> procureConcursosComNumeroMenorQue(@PathVariable Integer numero, @PathVariable Long idLoteria) {
		return this.service.procureConcursosComNumeroMenorQue(numero, idLoteria);
	}
//	@CrossOrigin(origins = "http://localhost:8100")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "calculeFrequenciasTotaisDasDezenas/idLoteria={idLoteria}&numeroSorteio={numeroDoSorteio}", method = RequestMethod.GET)
	public List<EstatisticaDTO> calculeFrequenciasTotaisDasDezenas(@PathVariable Long idLoteria, @PathVariable Integer numeroDoSorteio) {		
		return this.service.calculeFrequenciasTotaisDasDezenas(idLoteria, numeroDoSorteio);
	}
	// URL http://localhost:8080/concursos/procure_por/
	// @CrossOrigin(origins = "http://localhost:8100")
	// @RequestMapping(value = "procure_por/{numeroSorteado}&{nomeLoteria}&{numeroConcursoInicial}&{numeroConcursoFinal}", method = RequestMethod.GET)
	// public List<Concurso> procurePorNumerosSorteadosComoELoteriaNomeENumeroMaiorqueENumeroMenorque(
	// @PathVariable String numeroSorteado,
	// @PathVariable String nomeLoteria,
	// @PathVariable Integer numeroConcursoInicial,
	// @PathVariable Integer numeroConcursoFinal) {
	// return this.concursoRepositorio.procurePorNumerosSorteadosComoELoteriaNomeENumeroMaiorqueENumeroMenorque("%"+ numeroSorteado +"%", nomeLoteria, numeroConcursoInicial, numeroConcursoFinal);
	// }

	// URL http://localhost:8080/concursos/affordable/300
	// @RequestMapping(value = "affordable/{price}", method = RequestMethod.GET)
	// public List<Concurso> getAffordable(@PathVariable double price) {
	// return this.bookingRepository.findByPricePerNightLessThan(price);
	// }

	// URL http://localhost:8080/concursos/create | Testar no Postman - Extensão do Chrome
	// @RequestMapping(value = "/create", method = RequestMethod.POST)
	// public List<Concurso> create(@RequestBody Concurso concurso) {
	// this.concursoRepositorio.save(concurso);
	//
	// return this.concursoRepositorio.findAll();
	// }

	// URL http://localhost:8080/concursos/delete/10
	// @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	// public List<Concurso> remove(@PathVariable long id) {
	// this.concursoRepositorio.delete(id);
	//
	// return this.concursoRepositorio.findAll();
	// }
}

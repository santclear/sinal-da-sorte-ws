package br.com.agentedasorte.negocio.concurso.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.agentedasorte.negocio.concurso.Concurso;
import br.com.agentedasorte.negocio.concurso.ConcursoRepositorio;
import br.com.agentedasorte.negocio.concurso.ConcursoServico;
import br.com.agentedasorte.negocio.dto.EstatisticaDTO;

//URL http://localhost:8080/jsondoc-ui.html > http://localhost:8080/jsondoc
@RestController
@RequestMapping(value = "/concursos")
public class ConcursoControle {
	private ConcursoRepositorio concursoRepositorio;
	// private ConcursoServico concursoServico;

	@Autowired
	public ConcursoControle(ConcursoRepositorio bookingRepository, ConcursoServico concursoEspecificacao) {
		this.concursoRepositorio = bookingRepository;
		// this.concursoServico = concursoEspecificacao;
	}

	// URL http://localhost:8080/concursos/procure_por_loteria_id_igual_a_e_numero_maior_que/{loteriaId}&{numero}
	@CrossOrigin(origins = "http://localhost:8100")
	@RequestMapping(value = "procure_por_loteria_id_igual_a_e_numero_maior_que_e_sorteio_numero_igual_a/{loteriaId}&{numero}", method = RequestMethod.GET)
	public List<Concurso> procurePorLoteriaIdIgualAENumeroMaiorQueESorteioNumeroIgualA(@PathVariable Long loteriaId, @PathVariable Integer numero) {
		return this.concursoRepositorio.procurePorLoteriaIdIgualAENumeroMaiorQueESorteioNumeroIgualA(loteriaId, numero);
	}
	
	@CrossOrigin(origins = "http://localhost:8100")
	@RequestMapping(value = "procure_por_loteria_id_igual_a_e_numero_menor_que_e_sorteio_numero_igual_a/{loteriaId}&{numero}", method = RequestMethod.GET)
	public List<Concurso> procurePorLoteriaIdIgualAENumeroMenorQueESorteioNumeroIgualA(@PathVariable Long loteriaId, @PathVariable Integer numero) {
		return this.concursoRepositorio.procurePorLoteriaIdIgualAENumeroMenorQueESorteioNumeroIgualA(loteriaId, numero);
	}
	
	@CrossOrigin(origins = "http://localhost:8100")
	@RequestMapping(value = "calcule_frequencias_totais_das_dezenas/{loteriaId}", method = RequestMethod.GET)
	public List<EstatisticaDTO> calculeFrequenciasTotaisDasDezenas(@PathVariable Long loteriaId) {		
		return this.concursoRepositorio.calculeFrequenciasTotaisDasDezenas(loteriaId);
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

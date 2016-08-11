package agentedasorte.negocio.concurso.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import agentedasorte.negocio.concurso.Concurso;
import agentedasorte.negocio.concurso.ConcursoRepositorio;
import agentedasorte.negocio.concurso.ConcursoServico;

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

	// URL http://localhost:8080/concursos/procure_por_id_maior_que/{id}
	@CrossOrigin(origins = "http://localhost:8100")
	@RequestMapping(value = "procure_por_numero_maior_que_e_loteria_nome_como/{numero}&{loteriaNome}", method = RequestMethod.GET)
	public List<Concurso> procurePorNumeroMaiorQueELoteriaNomeComo(@PathVariable Integer numero, @PathVariable String loteriaNome) {
		return this.concursoRepositorio.procurePorNumeroMaiorQueELoteriaNomeComo(numero, loteriaNome);
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

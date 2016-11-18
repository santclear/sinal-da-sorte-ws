package br.com.agentedasorte.negocio.volante.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.agentedasorte.negocio.volante.Volante;
import br.com.agentedasorte.negocio.volante.VolanteRepositorio;

//URL http://localhost:8080/jsondoc-ui.html > http://localhost:8080/jsondoc
@RestController
@RequestMapping(value = "/volantes")
public class VolanteControle {
	private VolanteRepositorio volanteRepositorio;
	
	@Autowired
	public VolanteControle(VolanteRepositorio bookingRepository) {
		this.volanteRepositorio = bookingRepository;
	}
	
	//URL http://localhost:8080/volantes/all
	@CrossOrigin(origins = "http://localhost:8100")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Volante> getAll() {
		return this.volanteRepositorio.findAll();
	}
	
	//URL http://localhost:8080/volantes/affordable/300
//	@RequestMapping(value = "affordable/{price}", method = RequestMethod.GET)
//	public List<Volante> getAffordable(@PathVariable double price) {
//		return this.bookingRepository.findByPricePerNightLessThan(price);
//	}
	
	//URL http://localhost:8080/volantes/create | Testar no Postman - Extensão do Chrome
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public List<Volante> create(@RequestBody Volante volante) {
		this.volanteRepositorio.save(volante);
		
		return this.volanteRepositorio.findAll();
	}
	
	//URL http://localhost:8080/volantes/delete/10
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public List<Volante> remove(@PathVariable long id) {
		this.volanteRepositorio.delete(id);
		
		return this.volanteRepositorio.findAll();
	}
}

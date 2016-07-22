package agentedasorte.negocio.loteria.controle;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import agentedasorte.negocio.loteria.Loteria;
import agentedasorte.negocio.loteria.LoteriaRepositorio;


//URL http://localhost:8080/jsondoc-ui.html > http://localhost:8080/jsondoc
@RestController
@RequestMapping(value = "/loterias")
public class LoteriaControle {
	private LoteriaRepositorio loteriaRepositorio;
	
	@Autowired
	public LoteriaControle(LoteriaRepositorio loteriaRepositorio) {
		this.loteriaRepositorio = loteriaRepositorio;
	}
	
	
	//URL http://localhost:8080/loterias/all
	@CrossOrigin(origins = "http://localhost:8100")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Loteria> getAll() {
		return this.loteriaRepositorio.findById(1);
	}

}

package br.com.sinaldasorte.resource;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sinaldasorte.domain.Loteria;
import br.com.sinaldasorte.service.LoteriaService;


//URL http://localhost:8080/jsondoc-ui.html > http://localhost:8080/jsondoc
@RestController
@RequestMapping(value = "/loterias")
public class LoteriaResource {
	
	@Autowired
	private LoteriaService service;
	
	//FIXME: refatorar parâmetro inutil_para_esse_caso, talvez criar outro método na classe EntidadeBDServico
	//URL http://localhost:8080/loterias/procure_por_id_maior_que
//	@CrossOrigin(origins = "http://localhost:8100")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "procure_por_id_maior_que/{inutil_para_esse_caso}&{id}", method = RequestMethod.GET)
	public List<Loteria> procurePorIdMaiorQue(@PathVariable Long id) {
		return this.service.procurePorIdMaiorQue(id);
	}

}

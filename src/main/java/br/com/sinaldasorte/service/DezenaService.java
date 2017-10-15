package br.com.sinaldasorte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Dezena;
import br.com.sinaldasorte.repository.DezenaRepository;
import br.com.sinaldasorte.service.exceptions.ObjectNotFoundException;

@Service
public class DezenaService {
	
	@Autowired
	private DezenaRepository repo;
	
	public Dezena find(Long id) {
		Dezena obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado Id: "+ id +", Tipo: "+ Dezena.class.getName());
		}
		return obj;
	}

	public Dezena insert(Dezena obj) {
		obj.setId(null);
		return repo.save(obj);
	}
}

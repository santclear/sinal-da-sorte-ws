package br.com.sinaldasorte.resource;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.correios.bsb.sigep.master.bean.cliente.AtendeCliente;
import br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteServiceLocator;
import br.com.correios.bsb.sigep.master.bean.cliente.EnderecoERP;
import br.com.sinaldasorte.dto.EnderecoDto;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

	@RequestMapping(value = "/cep/{cep}", method = RequestMethod.GET)
	public ResponseEntity<EnderecoDto> find(@PathVariable String cep) {

//		AtendeCliente a = new AtendeClienteProxy();
//		EnderecoERP e = a.consultaCEP("88085470");
		try {
			AtendeClienteServiceLocator locator = new AtendeClienteServiceLocator();
			AtendeCliente correios = locator.getAtendeClientePort();
			EnderecoERP endereco = correios.consultaCEP(cep);
			
			EnderecoDto dto = new EnderecoDto();
			dto.setLogradouro(endereco.getEnd());
			return ResponseEntity.ok().body(dto);
		} catch (ServiceException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}

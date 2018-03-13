package br.com.sinaldasorte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.domain.enums.Situacoes;
import br.com.sinaldasorte.service.ContaService;

@Controller
@RequestMapping(value="/ctrl/contas")
public class ContaController {

	@Autowired
	private ContaService service;
	
	@RequestMapping(value="/cadastro/confirme", method=RequestMethod.GET)
	public  String atualize(@RequestParam(value="value") String hash, Model modelo) {
		Conta obj = service.procurePorHashConfirmacao(hash);
		obj.setSituacao(Situacoes.ATIVO);
		obj = service.atualizePorHashConfirmacao(obj);
		
		modelo.addAttribute("nomeUsuario", obj.getUsuario().getNome());
		
		return "confirmacao-cadastro-conta";
		
	}
}

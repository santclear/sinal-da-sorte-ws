package br.com.sinaldasorte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.service.ContaService;

@Controller
@RequestMapping(value="/ctrl/contas")
public class ContaController {

	@Autowired
	private ContaService service;
	
	@Value("${sinaldasorte.front.url}")
	private String sinalDaSorteFrontUrl;
	
	@Value("${sinaldasorte.copyright}")
	private String copyright;
	
	@Value("${sinaldasorte.organizacao}")
	private String organizacao;
	
	@RequestMapping(value="/cadastro/confirme", method=RequestMethod.GET)
	public  String atualize(@RequestParam(value="value") String hash, Model modelo) {
		Conta obj = service.procurePorHashConfirmacao(hash);
		if(obj == null) {
			return "cadastro-conta-confirmado";
		}
		obj = service.atualizePorHashConfirmacao(obj);
		
		modelo.addAttribute("nomeUsuario", obj.getUsuario().getNome());
		modelo.addAttribute("sinalDaSorteFrontUrl", sinalDaSorteFrontUrl);
		modelo.addAttribute("copyright", copyright);
		modelo.addAttribute("organizacao", organizacao);
		
		return "confirmacao-cadastro-conta";
	}
	
	@RequestMapping(value="/exclusao/confirme", method=RequestMethod.GET)
	public  String exclua(@RequestParam(value="value") String hash, Model modelo) {
		Conta obj = service.procurePorHashConfirmacao(hash);
		if(obj == null) {
			return "exclusao-conta-confirmado";
		}
		obj = service.excluaPorHashConfirmacao(obj);
		
		modelo.addAttribute("nomeUsuario", obj.getUsuario().getNome());
		modelo.addAttribute("sinalDaSorteFrontUrl", sinalDaSorteFrontUrl);
		modelo.addAttribute("copyright", copyright);
		modelo.addAttribute("organizacao", organizacao);
		
		return "confirmacao-exclusao-conta";
	}
}

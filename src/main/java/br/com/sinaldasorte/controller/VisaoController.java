package br.com.sinaldasorte.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VisaoController {
	private String faseProduto;
	
	@Autowired
	public VisaoController(Environment environment) {
		this.faseProduto = environment.getProperty("fase-produto");
	}

	//URL http://localhost:8080
	@RequestMapping("/")
	public String index(Model modelo) {
		modelo.addAttribute("dataAtual", new Date());
		modelo.addAttribute("usuario", "Agente da Sorte");
		modelo.addAttribute("fase", this.faseProduto);
		return "index";
	}
}
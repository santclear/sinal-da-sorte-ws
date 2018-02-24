package br.com.sinaldasorte.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sinaldasorte.domain.Cidade;
import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.domain.Estado;
import br.com.sinaldasorte.domain.Usuario;
import br.com.sinaldasorte.domain.enums.Generos;
import br.com.sinaldasorte.domain.enums.Perfil;
import br.com.sinaldasorte.domain.enums.Situacoes;
import br.com.sinaldasorte.repository.CidadeRepository;
import br.com.sinaldasorte.repository.ContaRepository;
import br.com.sinaldasorte.repository.EstadoRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder encriptadorDeSenha;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ContaRepository contaRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Estado est1 = new Estado(null, "Santa Catarina");
		Estado est2 = new Estado(null, "Bahia");
		
		Cidade c1 = new Cidade(null, "Florianópolis", est1);
		Cidade c2 = new Cidade(null, "São José", est1);
		Cidade c3 = new Cidade(null, "Palhoça", est1);
		Cidade c4 = new Cidade(null, "Mata de São João", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1,c2,c3));
		est2.getCidades().addAll(Arrays.asList(c4));

		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3,c4));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Usuario usu1 = new Usuario(null,"Fake","Foo",Generos.OUTRO,sdf.parse("16/09/2001"),"86736046726","R. dos Ipsum","Lorem complem","11044357","BBaz",c3);
		usu1.getTelefones().addAll(Arrays.asList("48911001122", "483110033"));
		
		Usuario usu2 = new Usuario(null,"Sant'Clear","Costa",Generos.HOMEM,sdf.parse("13/01/1983"),"27716419250","Rod. Beira Mar Norte","Majestic Palace","33733000","Centro",c1);
		usu2.getTelefones().addAll(Arrays.asList("48988773311", "4833112211"));
		
		Usuario usu3 = new Usuario(null,"Bruna","Amante",Generos.MULHER,sdf.parse("24/09/1988"),"14366148803","Av. Madre Benvenuta","Terraço","77122951","Santa Mônica",c1);
		usu3.getTelefones().addAll(Arrays.asList("48911001122", "483110033"));
		
		Usuario usu4 = new Usuario(null,"Savinni","Costa",Generos.HOMEM,sdf.parse("24/05/1984"),"54464474098","R. Presidente Kennedy","Primavera","46179369","Campinas",c2);
		usu4.getTelefones().addAll(Arrays.asList("48988773311", "4833112211"));
		
		Usuario usu5 = new Usuario(null,"Yuri","Reis",Generos.HOMEM,sdf.parse("05/12/1986"),"46873369144","Av. Atílio Pedro Pagani","Apezão","88055753","Passa Vinte",c3);
		usu5.getTelefones().addAll(Arrays.asList("48985577111", "4837226644"));
		
		Usuario usu6 = new Usuario(null,"Jarvis","Alfred",Generos.HOMEM,sdf.parse("01/01/1970"),"46873369144","Rodovia BA-099","Grand Palladium","77377333","Imbassaí",c4);
		usu6.getTelefones().addAll(Arrays.asList("48983337777", "4837373377"));
		
		
		c1.getUsuarios().addAll(Arrays.asList(usu1,usu2,usu3,usu4,usu5,usu6));
		
		Conta conta1 = new Conta(null, "f", usu1, encriptadorDeSenha.encode("f"));
		conta1.addPerfil(Perfil.ADMIN);
		conta1.addPerfil(Perfil.ASSINANTE);
				
		Conta conta2 = new Conta(null, "santclear@gmail.com", usu2, encriptadorDeSenha.encode("123"));
		conta2.addPerfil(Perfil.ADMIN);
		conta2.addPerfil(Perfil.ASSINANTE);
		
		Conta conta3 = new Conta(null, "bruna@gmail.com", usu3, encriptadorDeSenha.encode("111"));
		conta3.addPerfil(Perfil.ASSINANTE);
		
		Conta conta4 = new Conta(null, "savinni@gmail.com", usu4, encriptadorDeSenha.encode("222"));
		conta4.addPerfil(Perfil.GRATUITO);
		
		Conta conta5 = new Conta(null, "yuri@gmail.com", usu5, encriptadorDeSenha.encode("333"));
		conta5.setSituacao(Situacoes.INATIVO);
		conta5.addPerfil(Perfil.GRATUITO);
		
		Conta conta6 = new Conta(null, "jarvis@iron.com", usu6, encriptadorDeSenha.encode("777"));
		conta6.setSituacao(Situacoes.INADIMPLENTE);
		conta6.addPerfil(Perfil.GRATUITO);
		
		contaRepository.save(Arrays.asList(conta1,conta2,conta3,conta4,conta5,conta6));
		
	}
}
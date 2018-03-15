package br.com.sinaldasorte.service.util;

import org.springframework.beans.factory.annotation.Value;

import br.com.sinaldasorte.domain.Conta;

public abstract class MensagemEmail {
	
	@Value("${sinaldasorte.front.url}")
	private String sinalDaSorteFrontUrl;
	
	@Value("${sinaldasorte.ws.url}")
	private String sinalDaSorteWsUrl;
	
	@Value("${sinaldasorte.www}")
	private String www;
	
	@Value("${sinaldasorte.email}")
	private String email;
	
	@Value("${sinaldasorte.adverbio}")
	private String adverbio;
	
	@Value("${sinaldasorte.responsavel}")
	private String responsavel;
	
	@Value("${sinaldasorte.copyright}")
	private String copyright;
	
	@Value("${sinaldasorte.organizacao}")
	private String organizacao;
	
	@Value("${sinaldasorte.slogan}")
	private String slogan;
	
	public String novaSenha(Conta conta, String newPass) {
		StringBuilder sb = new StringBuilder();
		sb.append(novaSenhaTexto(conta, newPass));
		sb.append(assinaturaTexto());
		sb.append(copyrightTexto());
		sb.append("<split>");
		sb.append(novaSenhaHtml(conta, newPass));
		sb.append(assinaturaHtml(conta));
		sb.append(copyrightHtml());
		return sb.toString();
	}
	
	public String linkConfirmacaoCadastroConta(Conta conta) {
		StringBuilder sb = new StringBuilder();
		sb.append(linkConfirmacaoCadastroContaTexto(conta));
		sb.append(assinaturaTexto());
		sb.append(copyrightTexto());
		sb.append("<split>");
		sb.append(linkConfirmacaoCadastroContaHtml(conta));
		sb.append(assinaturaHtml(conta));
		sb.append(copyrightHtml());
		return sb.toString();
	}
	
	private String novaSenhaTexto(Conta conta, String novaSenha) {
		StringBuilder sb = new StringBuilder();
		if(conta.getUsuario().getGenero() == 1) sb.append("Olá prezado "+ conta.getUsuario().getNome() +",\n");
		else if(conta.getUsuario().getGenero() == 2) sb.append("Olá prezada "+ conta.getUsuario().getNome() +",\n");
		else sb.append("Olá "+ conta.getUsuario().getNome() +",\n");
		sb.append("segue sua nova senha, conforme solicitado: "+ novaSenha);
		sb.append("\n\n");
		sb.append("Caso deseje trocar essa senha, siga os passos abaixo:");
		sb.append("1. Acesse o sistema Sinal da Sorte pelo aplicativo de celular ou pela URL "+ www);
		sb.append("2. Digite seu email e a nova senha");
		sb.append("3. No menu lateral escolha a opção: Minha conta");
		sb.append("4. Digite uma nova senha nos campos: Senha e Confirme a Senha");
		sb.append("5. Clique no botão: Submeter cadastro");
		return sb.toString();
	}
	
	private String linkConfirmacaoCadastroContaTexto(Conta conta) {
		StringBuilder sb = new StringBuilder();
		if(conta.getUsuario().getGenero() == 1) sb.append("Olá prezado "+ conta.getUsuario().getNome() +",\n");
		else if(conta.getUsuario().getGenero() == 2) sb.append("Olá prezada "+ conta.getUsuario().getNome() +",\n");
		else sb.append("Olá "+ conta.getUsuario().getNome() +",\n");
		sb.append("estamos felizes por estares conosco. ");
		sb.append("Para confirmar o seu cadastro execute no seu navegador esse link: "+ sinalDaSorteWsUrl +"ctrl/contas/cadastro/confirme?value="+ conta.getHashConfirmacao());
		sb.append("\n\n");
		sb.append("É o nosso desejo sincero que você alcance a sorte grande");
		sb.append(", e que todos os seus sonhos se realizem!");
		return sb.toString();
	}
	
	private String novaSenhaHtml(Conta conta, String novaSenha) {
		StringBuilder sb = new StringBuilder();
		sb.append("<ss-split-texto-html>");
		sb.append("<div style='margin: 0 auto; width: 500px; margin-bottom: 60px;'>\n");
		if(conta.getUsuario().getGenero() == 1) sb.append("		<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Olá prezado "+ conta.getUsuario().getNome() +",</p>\n");
		else if(conta.getUsuario().getGenero() == 2) sb.append("		<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Olá prezada "+ conta.getUsuario().getNome() +",</p>\n");
		else sb.append("		<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Olá "+ conta.getUsuario().getNome() +",</p>\n");
		sb.append("		<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>segue sua nova senha, conforme solicitado: <span style='font-size: 11pt;font-weight: bold'>"+ novaSenha +"</span></p>\n");
		sb.append("		<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Caso deseje trocar essa senha, siga os passos abaixo:</p>\n");
		sb.append("		<ol>\n");
		sb.append("			<li style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Acesse o sistema Sinal da Sorte pelo aplicativo de celular ou pela URL <span style='font-size: 11pt;font-weight: bold'><a style='color: #1b5e20;text-decoration: none;' href='"+ sinalDaSorteFrontUrl +"' title='"+ www +"'>"+ www +"</a></span></li>\n");
		sb.append("			<li style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Digite seu email e a nova senha</li>\n");
		sb.append("			<li style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>No menu lateral escolha a opção: Minha conta</li>\n");
		sb.append("			<li style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Digite uma nova senha no campos: Senha e Confirme a senha</li>\n");
		sb.append("			<li style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Clique no botão: Submeter cadastro</li>\n");
		sb.append("		</ol>\n");
		sb.append("	</div>");
		return sb.toString();
	}
	
	private String linkConfirmacaoCadastroContaHtml(Conta conta) {
		StringBuilder sb = new StringBuilder();
		sb.append("	<div style='margin: 0 auto; width: 500px; margin-bottom: 60px;'>\n");
		if(conta.getUsuario().getGenero() == 1) sb.append("		<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Olá prezado "+ conta.getUsuario().getNome() +",</p>\n");
		else if(conta.getUsuario().getGenero() == 2) sb.append("		<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Olá prezada "+ conta.getUsuario().getNome() +",</p>\n");
		else sb.append("		<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Olá "+ conta.getUsuario().getNome() +",</p>\n");
		sb.append("		<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>estamos felizes por estares conosco.</p>\n");
		sb.append("		<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Para confirmar o seu cadastro clique nesse link: <span style='font-size: 11pt;font-weight: bold'><a style='color: #1b5e20;text-decoration: none;' href='"+ sinalDaSorteWsUrl +"ctrl/contas/cadastro/confirme?value="+ conta.getHashConfirmacao() +"' title='Confirma cadastro de conta Sinal da Sorte'>[Sinal da Sorte Link]</a></span></p>\n");
		sb.append("		<p></p>\n");
		sb.append("		<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>É o nosso desejo sincero que você alcance a sorte grande, e que todos os seus sonhos se realizem!</p>\n");
		sb.append("	</div>");
		return sb.toString();
	}
	
	private String assinaturaTexto() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\n\n");
		sb.append("--\n");
		sb.append(adverbio +",\n");
		sb.append(responsavel +"\n");
		sb.append(email +"\n");
		sb.append(www +"\n");
		sb.append(slogan +"\n\n");
		return sb.toString();
	}
	
	private String assinaturaHtml(Conta conta) {
		StringBuilder sb = new StringBuilder();
		sb.append("	<div style='margin: 0 auto; width: 500px; margin-bottom: 20px;'>\n");
		sb.append("	<hr>\n");
		sb.append("	<p style='color: #424242; font-size: 11pt'><span style='font-size: 11pt;font-weight: bold'>"+ adverbio +"</span>,</p>\n");
		sb.append("	<p style='color: #424242; font-size: 11pt'><span style='font-size: 11pt;font-weight: bold'>"+ responsavel +"</span></p>\n");
		sb.append("	<p>\n");
		sb.append("		<span style='font-size: 11pt'>\n");
		sb.append("			<a style='color: #1b5e20;text-decoration: none;' href='mailto:"+ email +"?Subject=Contato via app&From="+ conta.getEmail() +"' target='_top' title='"+ email +"'>\n");
		sb.append("				"+ email +"\n");
		sb.append("			</a>\n");
		sb.append("		</span>\n");
		sb.append("	</p>\n");
		sb.append("	<p>\n");
		sb.append("		<span style='font-size: 11pt;'>\n");
		sb.append("			<a style='color: #1b5e20;text-decoration: none;' href='"+ sinalDaSorteFrontUrl +"' target=\"_blank\" title='"+ www +"'>\n");
		sb.append("				"+ www +"\n");
		sb.append("			</a>\n");
		sb.append("		</span>\n");
		sb.append("	</p>\n");
		sb.append("	<p style='font-size: 12pt;font-weight: bold; color: #1b5e20;'>\n");
		sb.append("			"+ slogan +"\n");
		sb.append("	</p>\n");
		sb.append("	<p><img src='http://www.sinaldasorte.com/img/logo.png' alt='Logo Sinal da Sorte' height='150' width='150'></img></p>\n");
		sb.append(" </div>");
		return sb.toString();
	}
	
	private String copyrightTexto() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\n");
		sb.append(copyright +" | de "+ organizacao);
		return sb.toString();
	}
	
	private String copyrightHtml() {
		StringBuilder sb = new StringBuilder();
		sb.append("	<div style='margin: 0 auto; width: 500px; background-color: #1b5e20;'>\n" + 
				"		<table style='width: 100%;'>\n" + 
				"			<tr>\n" + 
				"				<td>&nbsp;</td>\n" + 
				"				<td>&nbsp;</td>\n" + 
				"				<td>&nbsp;</td>\n" + 
				"			</tr>\n" + 
				"			<tr>\n" + 
				"				<td style='color: white;padding: 0 10px 0 10px;'>"+ copyright +"</td>\n" + 
				"				<td>&nbsp;</td>\n" + 
				"				<td style='color: white;text-align: right;padding: 0 10px 0 10px;'>de "+ organizacao +"</td>\n" + 
				"			</tr>\n" + 
				"			<tr>\n" + 
				"				<td>&nbsp;</td>\n" + 
				"				<td>&nbsp;</td>\n" + 
				"				<td>&nbsp;</td>\n" + 
				"			</tr>\n" + 
				"		</table>\n" + 
				"	</div>");
		return sb.toString();
	}
}

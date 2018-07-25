package br.com.sinaldasorte.service.util;

import org.springframework.beans.factory.annotation.Value;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.domain.enums.Situacoes;

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
	
	public String linkConfirmacaoCadastro(Conta conta) {
		StringBuilder sb = new StringBuilder();
		sb.append(linkConfirmacaoCadastroTexto(conta));
		sb.append(assinaturaTexto());
		sb.append(copyrightTexto());
		sb.append("<split>");
		sb.append(linkConfirmacaoCadastroHtml(conta));
		sb.append(assinaturaHtml(conta));
		sb.append(copyrightHtml());
		return sb.toString();
	}
	
	public String linkConfirmarAtualizacaoEmail(Conta conta) {
		StringBuilder sb = new StringBuilder();
		sb.append(linkConfirmarAtualizacaoEmailTexto(conta));
		sb.append(assinaturaTexto());
		sb.append(copyrightTexto());
		sb.append("<split>");
		sb.append(linkConfirmarAtualizacaoEmailHtml(conta));
		sb.append(assinaturaHtml(conta));
		sb.append(copyrightHtml());
		return sb.toString();
	}
	
	public String linkConfirmarExclusao(Conta conta) {
		StringBuilder sb = new StringBuilder();
		sb.append(linkConfirmarExclusaoTexto(conta));
		sb.append(assinaturaTexto());
		sb.append(copyrightTexto());
		sb.append("<split>");
		sb.append(linkConfirmarExclusaoHtml(conta));
		sb.append(assinaturaHtml(conta));
		sb.append(copyrightHtml());
		return sb.toString();
	}
	
	private String novaSenhaTexto(Conta conta, String novaSenha) {
		StringBuilder sb = new StringBuilder();
		sb.append("Olá "+ conta.getUsuario().getNome() +",\n");
		sb.append("segue sua nova senha, conforme solicitado: "+ novaSenha);
		sb.append("\n\n");
		sb.append("Caso deseje trocar essa senha, siga os passos abaixo:");
		sb.append("\n1. Acesse o sistema Sinal da Sorte pelo aplicativo de celular ou pela URL "+ www);
		sb.append("\n2. Digite seu email e a nova senha");
		sb.append("\n3. No menu lateral escolha a opção: Minha conta");
		sb.append("\n4. Digite uma nova senha nos campos: Senha e Confirme a Senha");
		sb.append("\n5. Clique no botão: Atualizar");
		return sb.toString();
	}
	
	private String linkConfirmacaoCadastroTexto(Conta conta) {
		StringBuilder sb = new StringBuilder();
		sb.append("Olá "+ conta.getUsuario().getNome() +",\n\n");
		if(conta.getSituacao() == Situacoes.ATIVO) {
			sb.append("para concluir o seu cadastro, cole esse link no seu navegador e tecle \"Enter\": \n"+ sinalDaSorteWsUrl +"ctrl/contas/cadastro/confirme?value="+ conta.getHashConfirmacao());
		} else if(conta.getSituacao() == Situacoes.INATIVO) {
			sb.append("você solicitou uma nova senha, mas sua conta está inativa, para ativá-la ");
			sb.append("cole esse link no seu navegador e tecle \"Enter\": \n"+ sinalDaSorteWsUrl +"ctrl/contas/cadastro/confirme?value="+ conta.getHashConfirmacao() +". ");
			sb.append("Após a confirmação de cadastro solicite uma nova senha clicando \"ESQUECI MINHA SENHA\" na tela inicial do Sinal da Sorte.");
		}
		
		sb.append("\n\n");
		sb.append("Estamos felizes por estares conosco. É o nosso desejo sincero que você alcance a sorte grande e que todos os seus sonhos se realizem!");
		return sb.toString();
	}
	
	private String linkConfirmarAtualizacaoEmailTexto(Conta conta) {
		StringBuilder sb = new StringBuilder();
		sb.append("Olá "+ conta.getUsuario().getNome() +",\n\n");

		sb.append("foi solicitada a atualização de e-mail da sua conta do Sinal da Sorte, se foi você quem solicitou cole esse link no seu navegador e tecle \"Enter\": \n"+ sinalDaSorteWsUrl +"ctrl/contas/cadastro/confirme?value="+ conta.getHashConfirmacao());
		sb.append("\n\nCaso não queira atualizar, descarte esse e-mail.");
		return sb.toString();
	}
	
	private String linkConfirmarExclusaoTexto(Conta conta) {
		StringBuilder sb = new StringBuilder();
		sb.append("Olá "+ conta.getUsuario().getNome() +",\n\n");

		sb.append("foi solicitada a exclusão da sua conta do Sinal da Sorte, se foi você quem solicitou cole esse link no seu navegador e tecle \"Enter\": \n"+ sinalDaSorteWsUrl +"ctrl/contas/exclusao/confirme?value="+ conta.getHashConfirmacao());
		sb.append("\n\nSentiremos sua falta, esteja certo dessa operação, pois ela é irreversível.");
		sb.append("\n\nCaso não queira excluir, descarte esse e-mail.");
		return sb.toString();
	}
	
	private String novaSenhaHtml(Conta conta, String novaSenha) {
		StringBuilder sb = new StringBuilder();
		sb.append("<ss-split-texto-html>");
		sb.append("<div style='margin: 0 auto; width: 500px; margin-bottom: 60px;'>");
		sb.append("<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Olá "+ conta.getUsuario().getNome() +",</p>");
		sb.append("		<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>segue sua nova senha, conforme solicitado: <span style='font-size: 11pt;font-weight: bold'>"+ novaSenha +"</span></p>");
		sb.append("		<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Caso deseje trocar essa senha, siga os passos abaixo:</p>");
		sb.append("		<ol>");
		sb.append("			<li style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Acesse o sistema Sinal da Sorte pelo aplicativo de celular ou pela URL <span style='font-size: 11pt;font-weight: bold'><a style='color: #003300;text-decoration: none;' href='"+ sinalDaSorteFrontUrl +"' title='"+ www +"'>"+ www +"</a></span></li>");
		sb.append("			<li style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Digite seu email e a nova senha</li>");
		sb.append("			<li style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>No menu lateral escolha a opção: Minha conta</li>");
		sb.append("			<li style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Digite uma nova senha no campos: Senha e Confirme a senha</li>");
		sb.append("			<li style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Clique no botão: Atualizar</li>");
		sb.append("		</ol>");
		sb.append("	</div>");
		return sb.toString();
	}
	
	private String linkConfirmacaoCadastroHtml(Conta conta) {
		StringBuilder sb = new StringBuilder();
		sb.append("	<div style='margin: 0 auto; width: 500px; margin-bottom: 60px;'>");
		sb.append("		<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Olá "+ conta.getUsuario().getNome() +",</p>");
		if(conta.getSituacao() == Situacoes.ATIVO) {
			sb.append("<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>para concluir o seu cadastro clique: <a style='color: #003300;text-decoration: none;font-weight: bold' href='"+ sinalDaSorteWsUrl +"ctrl/contas/cadastro/confirme?value="+ conta.getHashConfirmacao() +"' title='Confirmar cadastro de conta Sinal da Sorte'>[Confirmar]</a></p>");
		} else if(conta.getSituacao() == Situacoes.INATIVO) {
			sb.append("<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>você solicitou uma nova senha, mas sua conta está inativa, para ativá-la clique: <a style='color: #003300;text-decoration: none;font-weight: bold' href='"+ sinalDaSorteWsUrl +"ctrl/contas/cadastro/confirme?value="+ conta.getHashConfirmacao() +"' title='Confirmar cadastro de conta Sinal da Sorte'>[Confirmar]</a></p>");
			sb.append("<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Após a confirmação de cadastro tente solicitar novamente uma nova senha clicando \"ESQUECI MINHA SENHA\" na tela inicial do Sinal da Sorte.</p>");
		}
		sb.append("		<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Estamos felizes por estares conosco. É o nosso desejo sincero que você alcance a sorte grande e que todos os seus sonhos se realizem!</p>");
		sb.append("	</div>");
		return sb.toString();
	}
	
	private String linkConfirmarAtualizacaoEmailHtml(Conta conta) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div style='margin: 0 auto; width: 500px; margin-bottom: 60px;'>");
		sb.append("	<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Olá "+ conta.getUsuario().getNome() +",</p>");
		sb.append("	<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>foi solicitada a atualização de e-mail da sua conta do Sinal da Sorte, se foi você quem solicitou clique: <a style='color: #003300;text-decoration: none;font-weight: bold' href='"+ sinalDaSorteWsUrl +"ctrl/contas/cadastro/confirme?value="+ conta.getHashConfirmacao() +"' title='Confirmar cadastro de conta Sinal da Sorte'>[Confirmar]</a></p>");
		sb.append("	<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Caso não queira atualizar, descarte esse e-mail.</p>");
		sb.append("</div>");
		return sb.toString();
	}
	
	private String linkConfirmarExclusaoHtml(Conta conta) {
		StringBuilder sb = new StringBuilder();		
		sb.append("<div style='margin: 0 auto; width: 500px; margin-bottom: 60px;'>");
		sb.append("	<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Olá "+ conta.getUsuario().getNome() +",</p>");
		sb.append("	<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>foi solicitada a exclusão da sua conta do Sinal da Sorte, se foi você quem solicitou clique: <a style='color: #003300;text-decoration: none;font-weight: bold' href='"+ sinalDaSorteWsUrl +"ctrl/contas/exclusao/confirme?value="+ conta.getHashConfirmacao() +"' title='Confirmar cadastro de conta Sinal da Sorte'>[Confirmar]</a></p>");
		sb.append("	<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Sentiremos sua falta, esteja certo dessa operação, pois ela é irreversível.</p>");
		sb.append("	<p style='color: #424242; margin-bottom: 5px;font-size: 11pt;'>Caso não queira excluir, descarte esse e-mail.</p>");
		sb.append("</div>");
		return sb.toString();
	}
	
	private String assinaturaTexto() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\n");
		sb.append("--\n");
		sb.append(adverbio +",\n\n");
		sb.append(responsavel +"\n\n");
		sb.append(email +"\n");
		sb.append(www +"\n\n");
		sb.append("Você também pode ser um milionário! "+ slogan +"\n\n");
		return sb.toString();
	}
	
	private String assinaturaHtml(Conta conta) {
		StringBuilder sb = new StringBuilder();
		sb.append("	<div style='margin: 0 auto; width: 500px; margin-bottom: 20px;'>");
		sb.append("	<hr>");
		sb.append("<p>");
		sb.append("	<span style='color: #424242; font-size: 11pt;font-weight: bold'>"+ adverbio +"</span>,");
		sb.append("</p>");
		sb.append("<p>");
		sb.append("	<span style='color: #424242; font-size: 11pt;font-weight: bold'>"+ responsavel +"</span>");
		sb.append("</p>");
		sb.append("<p>");
		sb.append("	<span style='font-size: 11pt'>");
		sb.append("		<a style='color: #003300;text-decoration: none;' href='mailto:"+ email +"?Subject=Contato via app&From="+ conta.getEmail() +"' target='_top' title='"+ email +"'>");
		sb.append("			"+ email +"");
		sb.append("		</a>");
		sb.append("	</span><br/>");
		sb.append("	<span style='font-size: 11pt;'>");
		sb.append("		<a style='color: #003300;text-decoration: none;' href='"+ sinalDaSorteFrontUrl +"' target=\"_blank\" title='"+ www +"'>");
		sb.append("			"+ www +"");
		sb.append("		</a>");
		sb.append("	</span>");
		sb.append("</p>");
		sb.append("	<span style='font-size: 12pt;font-weight: bold; color: #003300;'>");
		sb.append("			"+ slogan +"");
		sb.append("	</span><br/><br/><br/>");
		sb.append("	<img src='"+www+"/assets/img/login-logo.png' alt='Logo Sinal da Sorte'></img>");
		sb.append(" </div><br/>");
		return sb.toString();
	}
	
	private String copyrightTexto() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append(organizacao +" | "+ copyright);
		return sb.toString();
	}
	
	private String copyrightHtml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<div style='margin:0 auto;width:500px'>" + 
				"		<div style='width:100%;background-color:#003300;height:68px;text-align:center;color:white'>" + 
				"			<p style='padding-top: 10px;font-size: 11pt;font-weight: bold;'>"+ organizacao +"</p>" +
				"			<p style='padding-bottom: 10px;'>"+ copyright +"</p>" +
				"		</div>" + 
				"	</div>");
		return sb.toString();
	}
}

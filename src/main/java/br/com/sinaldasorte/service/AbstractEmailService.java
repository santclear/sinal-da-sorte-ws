package br.com.sinaldasorte.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.sinaldasorte.domain.Conta;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendNewPasswordEmail(Conta conta, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(conta, newPass);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareNewPasswordEmail(Conta conta, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(conta.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha - Sinal da Sorte");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		StringBuilder sb = new StringBuilder();
		sb.append("Olá prezado "+ conta.getUsuario().getNome() +",\n");
		sb.append("segue sua nova senha, conforme solicitado: "+ newPass);
		sb.append("\n\n");
		sb.append("Caso deseje trocar essa senha, siga os passos abaixo:");
		sb.append("1. Acesse o sistema Sinal da Sorte pelo aplicativo de celular ou pela URL www.sinaldasorte.com");
		sb.append("2. Digite seu email e a nova senha");
		sb.append("3. No menu lateral escolha a opção: Minha conta");
		sb.append("4. Digite uma nova senha nos campos: Senha e Confirme a Senha");
		sb.append("5. Clique no botão: Submeter cadastro");
		sb.append("\n\n\n");
		sb.append("Atenciosamente,\n");
		sb.append("Sant'Clear Ali Costa\n");
		sb.append("santclearsolucoes@gmail.com");
		sb.append("www.sinaldasorte.com");
		sb.append("Você também pode ser um milionário! Aposte com estratégia.");
		sm.setText(sb.toString());
		return sm;
	}
	
	@Override
	public void envieLinkConfirmacaoCadastroConta(Conta conta) {
		SimpleMailMessage sm = prepareLinkConfirmacaoCadastroConta(conta);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareLinkConfirmacaoCadastroConta(Conta conta) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(conta.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Link para confirmação de cadastro - Sinal da Sorte");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		StringBuilder sb = new StringBuilder();
		sb.append("Olá prezado "+ conta.getUsuario().getNome() +",\n");
		sb.append("estamos felizes por tê-lo conosco.");
		sb.append("Para confirmar o seu cadastro clique no link: http://localhost:8080/ctrl/contas/cadastro/confirme?value="+ conta.getHashConfirmacao());
		sb.append("\n\n");
		sb.append("É o nosso desejo sincero que você alcance a sorte grande");
		sb.append(", e que todos os seus sonhos se realizem!");
		sb.append("\n\n\n");
		sb.append("Atenciosamente,\n");
		sb.append("Sant'Clear Ali Costa\n");
		sb.append("santclearsolucoes@gmail.com");
		sb.append("www.sinaldasorte.com");
		sb.append("Você também pode ser um milionário! Aposte com estratégia.");
		sm.setText(sb.toString());
		return sm;
	}
}

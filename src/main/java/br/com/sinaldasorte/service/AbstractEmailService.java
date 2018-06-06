package br.com.sinaldasorte.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.dto.ContatoDto;
import br.com.sinaldasorte.service.interfaces.EmailService;
import br.com.sinaldasorte.service.util.MensagemEmail;

public abstract class AbstractEmailService extends MensagemEmail implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Value("${sinaldasorte.front.url}")
	private String sinalDaSorteFrontUrl;
	
	@Value("${sinaldasorte.ws.url}")
	private String sinalDaSorteWsUrl;
	
	@Value("${organizacao.email}")
	private String organizacaoEmail;
	
	@Override
	public void sendNewPasswordEmail(Conta conta, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(conta, newPass);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareNewPasswordEmail(Conta conta, String novaSenha) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(conta.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Sua solicitação de recuperação de senha do Sinal da Sorte");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		
		sm.setText(novaSenha(conta, novaSenha));
		return sm;
	}
	
	@Override
	public void envieLinkConfirmacaoCadastro(Conta conta) {
		SimpleMailMessage sm = prepareLinkConfirmacaoCadastroConta(conta);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareLinkConfirmacaoCadastroConta(Conta conta) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(conta.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Sua confirmação de cadastro de conta do Sinal da Sorte");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(linkConfirmacaoCadastro(conta));
		return sm;
	}
	
	@Override
	public void envieLinkConfirmarAtualizacaoEmail(Conta conta) {
		SimpleMailMessage sm = prepareLinkConfirmarAtualizacaoEmail(conta);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareLinkConfirmarAtualizacaoEmail(Conta conta) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(conta.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Sua confirmação de atualização de e-mail do Sinal da Sorte");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(linkConfirmarAtualizacaoEmail(conta));
		return sm;
	}
	
	@Override
	public void envieLinkConfirmarExclusao(Conta conta) {
		SimpleMailMessage sm = prepareLinkConfirmarExclusao(conta);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareLinkConfirmarExclusao(Conta conta) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(conta.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Sua confirmação de exclusão de conta do Sinal da Sorte");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(linkConfirmarExclusao(conta));
		return sm;
	}
	
	public void envieContato(ContatoDto dto) {
		SimpleMailMessage sm = prepareEnvieContato(dto);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareEnvieContato(ContatoDto dto) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setFrom(sender);
		sm.setSentDate(new Date(System.currentTimeMillis()));
		StringBuilder texto = new StringBuilder();
		texto.append(dto.getMensagem());
		texto.append("\n\nContatos: "+dto.getContato());
		if(dto.getEmailLogin() != null) {
			texto.append("\nUsuário: "+ dto.getEmailLogin());
		} else {
			texto.append("\nUsuário: Anônimo");
		}
		sm.setText(texto.toString());
		if(sender.equals(dto.getPara())) {
			sm.setTo(sender);
			sm.setSubject("[Contato - Sinal da Sorte]: "+ dto.getAssunto());
		} else if(organizacaoEmail.equals(dto.getPara())) {
			sm.setTo(organizacaoEmail);
			sm.setSubject("[Contato Profissional - Sinal da Sorte]: "+ dto.getAssunto());
		} else {
			sm.setTo(organizacaoEmail);
			sm.setSubject("[Contato Hack - Sinal da Sorte]: "+ dto.getAssunto());
			texto = new StringBuilder();
			texto.append("ATENÇÃO! Nenhuma opção de Tipo de Contato foi selecionada na page de Contato");
			texto.append("\n\nMensagem do remetente:\n\n");
			texto.append(dto.getMensagem());
			texto.append("\n\nContato: "+ dto.getContato());
			if(dto.getEmailLogin() != null) {
				texto.append("\nUsuário: "+ dto.getEmailLogin());
			} else {
				texto.append("\nUsuário: Anônimo");
			}
			sm.setText(texto.toString());
		}
		return sm;
	}
}

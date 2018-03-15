package br.com.sinaldasorte.service;

import org.springframework.mail.SimpleMailMessage;

import br.com.sinaldasorte.domain.Conta;

public interface EmailService {

	void sendEmail(SimpleMailMessage mensagem);
	void sendNewPasswordEmail(Conta conta, String novaSenha);
	void envieLinkConfirmacaoCadastroConta(Conta conta);
}

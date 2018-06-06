package br.com.sinaldasorte.service.interfaces;

import org.springframework.mail.SimpleMailMessage;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.dto.ContatoDto;

public interface EmailService {

	void sendEmail(SimpleMailMessage mensagem);
	void sendNewPasswordEmail(Conta conta, String novaSenha);
	void envieLinkConfirmacaoCadastro(Conta conta);
	void envieLinkConfirmarAtualizacaoEmail(Conta conta);
	void envieLinkConfirmarExclusao(Conta conta);
	void envieContato(ContatoDto dto);
}

package br.com.sinaldasorte.service.interfaces;

import br.com.sinaldasorte.service.exceptions.ReCaptchaInvalidException;

public interface ICaptchaService {
    void processResponse(final String response) throws ReCaptchaInvalidException;

    String getReCaptchaSite();

    String getReCaptchaSecret();
}

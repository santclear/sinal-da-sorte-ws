package br.com.sinaldasorte.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SmtpEmailService extends AbstractEmailService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${sinaldasorte.google.tokenUrl}")
	private String tokenUrl;
	
	@Value("${sinaldasorte.google.oauthClientId}")
	private String oauthClientId;

	@Value("${sinaldasorte.google.oauthSecret}")
	private String oauthSecret;
	
	@Value("${sinaldasorte.google.refreshToken}")
	private String refreshToken;
	
	@Value("${sinaldasorte.google.accessToken}")
	private String accessToken;
	
	@Value("${sinaldasorte.google.tokenExpires}")
	private String tokenExpires;
	
	@Override
	public void sendEmail(SimpleMailMessage simpleMailMessage) {
		if(System.currentTimeMillis() > Long.parseLong(tokenExpires)) {
	        try {
	            String request = "client_id="+URLEncoder.encode(oauthClientId, "UTF-8")
	                    +"&client_secret="+URLEncoder.encode(oauthSecret, "UTF-8")
	                    +"&refresh_token="+URLEncoder.encode(refreshToken, "UTF-8")
	                    +"&grant_type=refresh_token";
	            HttpURLConnection conn = (HttpURLConnection) new URL(tokenUrl).openConnection();
	            conn.setDoOutput(true);
	            conn.setRequestMethod("POST");
	            PrintWriter out = new PrintWriter(conn.getOutputStream());
	            out.print(request); // note: println causes error
	            out.flush();
	            out.close();
	            conn.connect();
	            try {
	                HashMap<String,Object> result;
	                result = new ObjectMapper().readValue(conn.getInputStream(), new TypeReference<HashMap<String,Object>>() {});
	                accessToken = (String) result.get("access_token");
	                tokenExpires = String.valueOf(System.currentTimeMillis()+(((Number)result.get("expires_in")).intValue()*1000));
	            } catch (IOException e) {
	                String line;
	                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	                while((line = in.readLine()) != null) {
	                	LOGGER.error(line);
	                }
	                System.out.flush();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		
	    ((JavaMailSenderImpl)this.javaMailSender).setPassword(accessToken);
		
		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(simpleMailMessage.getTo());
			helper.setSubject(simpleMailMessage.getSubject());
			
			String textos[] = simpleMailMessage.getText().split("<split>");
			String texto = textos[0];
			String html = null;
			if(textos.length < 2) {
				helper.setText(String.format(texto));
			} else {
				html = textos[1];
				helper.setText(String.format(texto), html);
			}
			
			
			LOGGER.debug("Enviando email...");
			((JavaMailSenderImpl)this.javaMailSender).send(message);
			LOGGER.debug(texto);
			LOGGER.debug(html);
			LOGGER.debug("Email enviado");
		} catch (MessagingException e) {
			throw new MailParseException("Ocorreu um erro ao tentar preparar o e-mail para envio: "+ e.getMessage());
		}
	}
}

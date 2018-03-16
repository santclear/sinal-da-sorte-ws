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
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	private static String TOKEN_URL = "https://www.googleapis.com/oauth2/v4/token";
	private String oauthClientId = "700454485127-5k23bofg3jlk10318ka7l8qvlib3elvl.apps.googleusercontent.com";
	private String oauthSecret = "PRCwnSdzq4pA0zmN9VTRsT0Q";
	private String refreshToken = "1/_63oUW-L4wmVnetDpota6coVpW7yJfGDVpKbTjg_MH4";
	private String accessToken = "ya29.GluABdUOc-j5BsivWqpfetuDrvOjhNJ-ONXC_T9KecI3XmeLQKgPUD5c7eYgDZuv-puSo4kEWEkGhwHyE-szrsvTbHll0Qxek5ZaQDYXNtmVgqfV1yi00ovV0Ado";
	private long tokenExpires = 3600L;
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage simpleMailMessage) {
		if(System.currentTimeMillis() > tokenExpires) {
	        try {
	            String request = "client_id="+URLEncoder.encode(oauthClientId, "UTF-8")
	                    +"&client_secret="+URLEncoder.encode(oauthSecret, "UTF-8")
	                    +"&refresh_token="+URLEncoder.encode(refreshToken, "UTF-8")
	                    +"&grant_type=refresh_token";
	            HttpURLConnection conn = (HttpURLConnection) new URL(TOKEN_URL).openConnection();
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
	                tokenExpires = System.currentTimeMillis()+(((Number)result.get("expires_in")).intValue()*1000);
	            } catch (IOException e) {
	                String line;
	                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	                while((line = in.readLine()) != null) {
	                    System.out.println(line);
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
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(simpleMailMessage.getTo());
			helper.setSubject(simpleMailMessage.getSubject());
			
			String textos[] = simpleMailMessage.getText().split("<split>");
			String texto = textos[0];
			String html = textos[1];
			
			helper.setText(String.format(texto), html);
			
			LOG.info("Enviando email...");
			((JavaMailSenderImpl)this.javaMailSender).send(message);
			LOG.info(texto);
			LOG.info(html);
			LOG.info("Email enviado");
		} catch (MessagingException e) {
			throw new MailParseException("Ocorreu um erro ao tentar preparar o e-mail para envio: "+ e.getMessage());
		}
	}
}

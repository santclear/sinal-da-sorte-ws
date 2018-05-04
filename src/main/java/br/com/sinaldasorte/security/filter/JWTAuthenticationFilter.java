package br.com.sinaldasorte.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sinaldasorte.dto.CredenciaisDto;
import br.com.sinaldasorte.security.ContaAuth;
import br.com.sinaldasorte.security.JWTUtil;

// Interceptador das requisições de login
// /login é um endpoint reservado pelo Spring Security
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
    
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
    	setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
	
    // Tenta autenticar
	@Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {

		try {
			// getInputStream() pega os dados que estão na requisição (no body do POST http) e converte para CredenciaisDTO
			CredenciaisDto creds = new ObjectMapper().readValue(req.getInputStream(), CredenciaisDto.class);
	
	        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getSenha(), new ArrayList<>());
	        
	        // Verifica se os dados passados de para autenticação são válidos
	        Authentication auth = authenticationManager.authenticate(authToken);
	        
	        return auth;// Informa ao Spring Security se autenticação ocorreu com sucesso
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Se a autenticação ocorrer com sucesso
	@Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
		// Pega o username (email) do usuário autenticado
		String username = ((ContaAuth) auth.getPrincipal()).getUsername();
		// Gera o token
        String token = jwtUtil.generateToken(username);
        // Responde, através do cabeçalho da requisição, que a autenticação ocorreu com sucesso
        res.addHeader("Authorization", "Bearer " + token);
        // Por padrão, o mecanismo de cors não expõe cabeçalhos personalizados (Exemplo: Authorization),
        // desse modo é necessário expor
        res.addHeader("access-control-expose-headers", "Authorization");
	}
	
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
		 
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json"); 
            response.getWriter().append(json());
        }
        
        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\": \"/login\"}";
        }
    }
}

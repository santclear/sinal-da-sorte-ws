package br.com.sinaldasorte.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sinaldasorte.dto.CredenciaisDto;
import br.com.sinaldasorte.security.ContaAuth;
import br.com.sinaldasorte.security.JWTUtil;
import br.com.sinaldasorte.service.AuthService;
import br.com.sinaldasorte.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	// Endpoint utilizado para reaver um novo token, caso o usuário esteja usando o sistema
	// num momento em que o token está perto de expirar
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		ContaAuth conta = UserService.autenticado();
		String token = jwtUtil.generateToken(conta.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
        // Por padrão, o mecanismo de cors não expõe cabeçalhos personalizados (Exemplo: Authorization),
        // desse modo é necessário expor
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody CredenciaisDto objDto) {
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
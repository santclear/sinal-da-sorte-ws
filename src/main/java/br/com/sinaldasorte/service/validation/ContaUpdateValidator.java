package br.com.sinaldasorte.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.dto.ContaDto;
import br.com.sinaldasorte.repository.ContaRepository;
import br.com.sinaldasorte.resource.exception.FieldMessage;

public class ContaUpdateValidator implements ConstraintValidator<ContaUpdate, ContaDto> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ContaRepository repo;
	
	@Override
	public void initialize(ContaUpdate ann) {}

	@Override
	public boolean isValid(ContaDto objDTO, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id")); // Obtém o Id a partir da URL
		
		List<FieldMessage> list = new ArrayList<>();
		
		
		Conta aux = repo.findByEmail(objDTO.getEmail());
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email inválido."));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}

package br.com.sinaldasorte.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sinaldasorte.domain.Conta;
import br.com.sinaldasorte.dto.ContaNewDto;
import br.com.sinaldasorte.repository.ContaRepository;
import br.com.sinaldasorte.resource.exception.FieldMessage;

public class ContaInsertValidator implements ConstraintValidator<ContaInsert, ContaNewDto> {
	
	@Autowired
	private ContaRepository repo;
	
	@Override
	public void initialize(ContaInsert ann) {}

	@Override
	public boolean isValid(ContaNewDto objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		Conta aux = repo.findByEmail(objDTO.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email inválido."));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}

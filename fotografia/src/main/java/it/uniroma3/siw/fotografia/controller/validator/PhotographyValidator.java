package it.uniroma3.siw.fotografia.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.fotografia.model.Photography;
import it.uniroma3.siw.fotografia.service.PhotographyService;

@Component
public class PhotographyValidator implements Validator {
	
	@Autowired
	private PhotographyService photographyService;
	
	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "place", "required");
		
		
		if(!errors.hasErrors()) {
			if(this.photographyService.alreadyExists((Photography)o)) {
				errors.reject("duplicato");
			}
		}	
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Photography.class.equals(aClass);
	}

}

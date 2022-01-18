package it.uniroma3.siw.fotografia.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.fotografia.service.PhotographerService;
import it.uniroma3.siw.fotografia.model.Photographer;

@Component
public class PhotographerValidator implements Validator {

	@Autowired
	private PhotographerService photographerService;
	
	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthPlace", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nationality", "required");


		if (!errors.hasErrors()) {
			if (this.photographerService.alreadyExists((Photographer)o)) {
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Photographer.class.equals(aClass);
	}
}

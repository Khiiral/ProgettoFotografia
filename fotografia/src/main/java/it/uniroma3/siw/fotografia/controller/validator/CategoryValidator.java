package it.uniroma3.siw.fotografia.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.fotografia.model.Category;
import it.uniroma3.siw.fotografia.service.CategoryService;


@Component
public class CategoryValidator implements Validator {

	@Autowired
	private CategoryService categoryService;
	
	
	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		
		if (!errors.hasErrors()) {
			if (this.categoryService.alreadyExists((Category)o)) {
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Category.class.equals(aClass);
	}
}

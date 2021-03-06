package it.uniroma3.siw.fotografia.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.fotografia.model.User;

@Component
public class UserValidator implements Validator {

    final Integer MAX_NAME_LENGTH = 100;
    final Integer MIN_NAME_LENGTH = 2;

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        String name = user.getName().trim();
        String surname = user.getSurname().trim();

        if (name.isEmpty())
            errors.rejectValue("name", "required");
        else if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH)
            errors.rejectValue("name", "size");

        if (surname.isEmpty())
            errors.rejectValue("surname", "required");
        else if (surname.length() < MIN_NAME_LENGTH || surname.length() > MAX_NAME_LENGTH)
            errors.rejectValue("surname", "size");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

}

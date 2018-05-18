package com.example.project.view.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import com.example.project.service.UserService;

@FacesValidator(value="project.UniqueEmailValidator", managed=true)
public class UniqueEmailValidator implements Validator<String> {

	@Inject
	private UserService userService;

	@Override
	public void validate(FacesContext context, UIComponent component, String email) throws ValidatorException {
		if (email == null || email.isEmpty()) {
			return;
		}

        String oldEmail = (String) ((UIOutput) component).getValue();

        if (!email.equals(oldEmail) && userService.findByEmail(email).isPresent()) {
			throw new ValidatorException(new FacesMessage("Email already in use"));
		}
	}
}

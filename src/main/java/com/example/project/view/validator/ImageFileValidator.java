package com.example.project.view.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

@FacesValidator("project.ImageFileValidator")
public class ImageFileValidator implements Validator<Part> {

	@Override
	public void validate(FacesContext context, UIComponent component, Part value) throws ValidatorException {
		if (value == null) {
			return; // Let @NotNull or required="true" handle.
		}

		try {
			ImageIO.read(value.getInputStream()).toString();
		}
		catch (Exception e) {
			String message = "Not an image file";
			throw new ValidatorException(new FacesMessage(message), e);
		}
	}
}

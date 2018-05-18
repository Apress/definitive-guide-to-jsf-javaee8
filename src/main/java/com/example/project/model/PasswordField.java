package com.example.project.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(PasswordField.TYPE)
public class PasswordField extends Field {

	private static final long serialVersionUID = 1L;

	public static final String TYPE = "PASSWORD";

	@Override
	public void populate(DynamicForm form) {
		form.createOutputLabel(this);
		form.createInputSecret(this);
		form.createMessage(this);
	}

}

package com.example.project.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(ButtonField.TYPE)
public class ButtonField extends Field {

	private static final long serialVersionUID = 1L;

	public static final String TYPE = "BUTTON";

	@Override
	public void populate(DynamicForm form) {
		form.createCommandButton(this);
		form.createMessage(this);
	}

}

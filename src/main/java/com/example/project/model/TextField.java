package com.example.project.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(TextField.TYPE)
public class TextField extends Field {

	private static final long serialVersionUID = 1L;

	public static final String TYPE = "TEXT";

	@Override
	public void populate(DynamicForm form) {
		form.createOutputLabel(this);
		form.createInputText(this);
		form.createMessage(this);
	}

}

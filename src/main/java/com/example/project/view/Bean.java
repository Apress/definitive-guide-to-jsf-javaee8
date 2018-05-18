package com.example.project.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named
@RequestScoped
public class Bean {

	private String optionalField;
	private @NotNull String requiredField;

	public String getOptionalField() {
		return optionalField;
	}

	public void setOptionalField(String optionalField) {
		this.optionalField = optionalField;
	}

	public String getRequiredField() {
		return requiredField;
	}

	public void setRequiredField(String requiredField) {
		this.requiredField = requiredField;
	}

}

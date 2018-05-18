package com.example.project.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=String.class)
public class TrimConverter implements Converter<String> {

	@Override
	public String getAsString(FacesContext context, UIComponent component, String modelValue) {
		return modelValue == null ? "" : modelValue;
	}

	@Override
	public String getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		String trimmed = submittedValue.trim();
		return trimmed.isEmpty() ? null : trimmed;
	}

}
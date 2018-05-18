package com.example.project.model.converter;

import java.util.Locale;

import javax.persistence.AttributeConverter;

public class LocaleConverter implements AttributeConverter<Locale, String> {

	@Override
	public String convertToDatabaseColumn(Locale locale) {
		return locale.toLanguageTag();
	}

	@Override
	public Locale convertToEntityAttribute(String languageTag) {
		return Locale.forLanguageTag(languageTag);
	}

}
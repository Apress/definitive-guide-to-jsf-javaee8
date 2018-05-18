package com.example.project.view.i18n;

import java.io.IOException;
import java.util.Enumeration;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.inject.spi.CDI;
import javax.faces.context.FacesContext;

import com.example.project.service.TranslationService;

public class DatabaseResourceBundle extends ResourceBundle {

	private static final Control CONTROL = new DatabaseControl();

	@Override
	public Object handleGetObject(String key) {
		return getCurrentInstance().getObject(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		return getCurrentInstance().getKeys();
	}

	private ResourceBundle getCurrentInstance() {
		FacesContext context = FacesContext.getCurrentInstance();
		String key = CONTROL.getClass().getName();

		return (ResourceBundle) context.getAttributes().computeIfAbsent(key, k -> {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Locale locale = context.getViewRoot().getLocale();
			return ResourceBundle.getBundle(key, locale, classLoader, CONTROL);
		});
	}

	private static class DatabaseControl extends Control {
		@Override
		public ResourceBundle newBundle
			(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
				throws IllegalAccessException, InstantiationException, IOException
		{
			FacesContext context = FacesContext.getCurrentInstance();
			TranslationService translationService = CDI.current().select(TranslationService.class).get();
			final Object[][] contents = translationService.getContent(locale, context.getApplication().getDefaultLocale());

			return new ListResourceBundle() {
				@Override
				protected Object[][] getContents() {
					return contents;
				}
			};
		}
	}
}
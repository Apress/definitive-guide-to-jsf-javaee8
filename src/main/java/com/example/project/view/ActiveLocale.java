package com.example.project.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class ActiveLocale implements Serializable {

	private static final long serialVersionUID = 1L;

	private Locale current;
	private List<Locale> available;

	// TODO: Outcommented for now because Payara 5 Beta 2 falls over this with NPE in JCDIServiceImpl.createManagedObject.
	// @Inject
	// private FacesContext context;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application app = context.getApplication();
		current = app.getViewHandler().calculateLocale(context);
		available = new ArrayList<>();
		available.add(app.getDefaultLocale());
		app.getSupportedLocales().forEachRemaining(available::add);
	}

	public void reload() {
		FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add("location.replace(location)");
	}

	public Locale getCurrent() {
		return current;
	}

	public String getLanguageTag() {
		return current.toLanguageTag();
	}

	public void setLanguageTag(String languageTag) {
		current = Locale.forLanguageTag(languageTag);
	}

	public List<Locale> getAvailable() {
		return available;
	}
}
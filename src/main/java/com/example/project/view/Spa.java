package com.example.project.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class Spa implements Serializable {

	private static final long serialVersionUID = 1L;

	// TODO: Outcommented for now because Payara 5 Beta 2 falls over this with NPE in JCDIServiceImpl.createManagedObject. See work around below.
	// @Inject @ManagedProperty("#{param.page}")
	private String page;

	@PostConstruct
	public void init() {
		page = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("page"); // This is the work around.
		
		if (page == null || page.isEmpty()) {
			page = "page1";
		}
	}

	public void set(String page) {
		this.page = page;
	}

	public String getPage() {
		return page;
	}

}
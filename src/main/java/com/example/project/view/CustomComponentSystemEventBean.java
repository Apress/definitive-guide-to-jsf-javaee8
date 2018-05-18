package com.example.project.view;

import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class CustomComponentSystemEventBean {

	public void prepareInvokeApplication() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("PreInvokeApplicationEvent invoked at " + LocalDateTime.now()));
	}

	public void submit() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Submit invoked at " + LocalDateTime.now()));
	}

}

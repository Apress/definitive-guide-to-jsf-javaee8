package com.example.project.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named @RequestScoped
public class Logout {

	@Inject
	private HttpServletRequest request;

    public String submit() throws ServletException {
    	request.logout();
    	request.getSession().invalidate();
    	return "/login?faces-redirect=true";
    }

}
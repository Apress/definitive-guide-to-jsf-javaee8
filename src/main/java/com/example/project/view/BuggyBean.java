package com.example.project.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.project.service.UserService;

@Named
@RequestScoped
public class BuggyBean {

	@Inject
	private UserService userService;

	public void throwRuntimeException() {
		((Object) null).toString();
	}

	public void throwBusinessException() {
		userService.getById(0L);
	}

}

package com.example.project.config.authentication;

import javax.security.enterprise.CallerPrincipal;

import com.example.project.model.User;

public class UserPrincipal extends CallerPrincipal {

	private final User user;

	public UserPrincipal(User user) {
		super(user.getEmail());
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}

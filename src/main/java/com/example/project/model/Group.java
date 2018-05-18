package com.example.project.model;

import static java.util.Arrays.asList;

import java.util.List;

public enum Group {

	USER(
		Role.VIEW_USER_PAGES,
		Role.EDIT_OWN_PROFILE,
		Role.ACCESS_API
	),

	ADMIN(
		Role.VIEW_ADMIN_PAGES,
		Role.EDIT_PROFILES
	);

	private final List<Role> roles;

	private Group(Role... roles) {
		this.roles = asList(roles);
	}

	public List<Role> getRoles() {
		return roles;
	}

}
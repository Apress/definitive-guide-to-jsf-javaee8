package com.example.project.model;

import static java.util.stream.Collectors.toSet;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Table(name="\"User\"")
@Entity
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public static final int EMAIL_MAXLENGTH = 254;

	@Column(length = EMAIL_MAXLENGTH, nullable = false, unique = true)
	private @NotNull String email;

	@OneToOne(fetch = LAZY, cascade = ALL)
	private Credentials credentials;

	@ElementCollection(fetch = EAGER)
	private @Enumerated(STRING) Set<Group> groups = new HashSet<>();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	@Transient
	public Set<Role> getRoles() {
		return groups.stream().flatMap(g -> g.getRoles().stream()).collect(toSet());
	}

	@Transient
	public Set<String> getRolesAsStrings() {
		return getRoles().stream().map(Role::name).collect(toSet());
	}

}

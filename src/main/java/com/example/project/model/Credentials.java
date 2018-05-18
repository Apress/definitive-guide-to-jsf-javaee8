package com.example.project.model;

import static com.example.project.util.MessageDigests.digest;
import static com.example.project.util.MessageDigests.Algorithm.SHA_256;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Credentials extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private static final int HASH_LENGTH = 32;
	private static final int SALT_LENGTH = 40;

	@ManyToOne(optional = false)
	private @NotNull User user;

	@Column(length = HASH_LENGTH, nullable = false)
	private @NotNull byte[] passwordHash;

	@Column(length = SALT_LENGTH, nullable = false)
	private @NotNull byte[] salt = new byte[SALT_LENGTH];

	public void setUser(User user) {
		user.setCredentials(this);
		this.user = user;
	}

	public void setPassword(String password) {
		ThreadLocalRandom.current().nextBytes(salt);
		this.passwordHash = hash(password);
	}

	public boolean isValid(String password) {
		return Arrays.equals(passwordHash, hash(password));
	}

	private byte[] hash(String password) {
		return digest(password, salt, SHA_256);
	}

}
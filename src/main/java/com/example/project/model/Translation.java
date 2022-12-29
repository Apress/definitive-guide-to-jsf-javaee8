package com.example.project.model;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "locale", "key" }) })
public class Translation extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public static final int LOCALE_MAXLENGTH = 5;
	public static final int KEY_MAXLENGTH = 255;

	@Column(length = LOCALE_MAXLENGTH, nullable = false)
	private @NotNull Locale locale;

	@Column(length = KEY_MAXLENGTH, nullable = false, name="\"key\"")
	private @NotNull String key;

	@Lob
	@Column(nullable = false)
	private @NotNull String value;

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
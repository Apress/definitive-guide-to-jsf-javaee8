package com.example.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Product extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public static final int NAME_MAXLENGTH = 32;
	public static final int DESCRIPTION_MAXLENGTH = 255;

	@Column(length = NAME_MAXLENGTH, nullable = false)
	private @NotNull String name;

	@Column(length = DESCRIPTION_MAXLENGTH, nullable = false)
	private @NotNull String description;

	public static Product create(String name, String description) {
		Product product = new Product();
		product.name = name;
		product.description = description;
		return product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

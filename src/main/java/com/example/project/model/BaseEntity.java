package com.example.project.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	@Override
	public boolean equals(Object other) {
		if (getId() != null && getClass().isInstance(other) && other.getClass().isInstance(this)) {
			return getId().equals(((BaseEntity) other).getId());
		}
		else {
			return (other == this);
		}
	}

	@Override
	public int hashCode() {
		if (getId() != null) {
			return Objects.hash(getId());
		}
		else {
			return super.hashCode();
		}
	}

}

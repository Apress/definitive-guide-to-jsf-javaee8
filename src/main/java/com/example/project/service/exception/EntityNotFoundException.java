package com.example.project.service.exception;

public class EntityNotFoundException extends QueryException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(Exception cause) {
		super(cause);
	}

}
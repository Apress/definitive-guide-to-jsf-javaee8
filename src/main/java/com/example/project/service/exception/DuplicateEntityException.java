package com.example.project.service.exception;

public class DuplicateEntityException extends QueryException {

	private static final long serialVersionUID = 1L;

	public DuplicateEntityException() {
		super();
	}

	public DuplicateEntityException(Exception cause) {
		super(cause);
	}

}
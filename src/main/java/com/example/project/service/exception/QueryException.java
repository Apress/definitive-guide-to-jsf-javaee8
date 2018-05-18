package com.example.project.service.exception;

public class QueryException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public QueryException() {
		super();
	}

	public QueryException(Exception cause) {
		super(cause);
	}

}
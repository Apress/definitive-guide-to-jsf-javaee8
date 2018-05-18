package com.example.project.service.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public abstract class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessException() {
		super();
	}

	public BusinessException(Exception cause) {
		super(cause);
	}

}
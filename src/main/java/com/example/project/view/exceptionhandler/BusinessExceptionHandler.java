package com.example.project.view.exceptionhandler;

import java.util.Iterator;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;

import com.example.project.service.exception.BusinessException;

public class BusinessExceptionHandler extends ExceptionHandlerWrapper {

	public BusinessExceptionHandler(ExceptionHandler wrapped) {
		super(wrapped);
	}

	@Override
	public void handle() {
		handleBusinessException(FacesContext.getCurrentInstance());
		getWrapped().handle();
	}

	protected void handleBusinessException(FacesContext context) {
		Iterator<ExceptionQueuedEvent> unhandledExceptionQueuedEvents = getUnhandledExceptionQueuedEvents().iterator();

		if (context == null || !unhandledExceptionQueuedEvents.hasNext()) {
			return;
		}

		Throwable exception = unhandledExceptionQueuedEvents.next().getContext().getException();

		while (exception.getCause() != null && (exception instanceof FacesException || exception instanceof ELException)) {
			exception = exception.getCause();
		}

		if (!(exception instanceof BusinessException)) {
			return;
		}

		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, exception.toString(), exception.getMessage()));
		context.validationFailed();
		context.getPartialViewContext().getRenderIds().add("globalMessages");

		unhandledExceptionQueuedEvents.remove();

		while (unhandledExceptionQueuedEvents.hasNext()) {
			unhandledExceptionQueuedEvents.next();
			unhandledExceptionQueuedEvents.remove();
		}
	}

	public static class Factory extends ExceptionHandlerFactory {

		public Factory(ExceptionHandlerFactory wrapped) {
			super(wrapped);
		}

		@Override
		public ExceptionHandler getExceptionHandler() {
			return new BusinessExceptionHandler(getWrapped().getExceptionHandler());
		}
	}
}

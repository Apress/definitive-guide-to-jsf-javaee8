package com.example.project.view.listener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.example.project.view.event.PreInvokeApplicationEvent;

public class PreInvokeApplicationListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.INVOKE_APPLICATION;
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getApplication().publishEvent(context, PreInvokeApplicationEvent.class, context.getViewRoot());
	}

	@Override
	public void afterPhase(PhaseEvent event) {
		// NOOP.
	}

}

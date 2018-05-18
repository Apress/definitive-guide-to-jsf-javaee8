package com.example.project.view.event;

import javax.faces.component.UIComponent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.NamedEvent;

@NamedEvent(shortName = "preInvokeApplicationEvent")
public class PreInvokeApplicationEvent extends ComponentSystemEvent {

	private static final long serialVersionUID = 1L;

	public PreInvokeApplicationEvent(UIComponent component) {
		super(component);
	}

}

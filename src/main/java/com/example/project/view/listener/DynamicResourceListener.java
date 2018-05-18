package com.example.project.view.listener;

import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

public class DynamicResourceListener implements SystemEventListener {

	private static final String LIBRARY = "foo";

	@Override
	public boolean isListenerForSource(Object source) {
		UIOutput output = (UIOutput) source;
		return "javax.faces.Head".equals(output.getRendererType());
	}

	@Override
	public void processEvent(SystemEvent event) {
		FacesContext context = event.getFacesContext();

		String scriptName = "foo.js"; // Can be dynamic.
		addResource(context, scriptName);

		String stylesheetName = "foo.css"; // Can be dynamic.
		addResource(context, stylesheetName);
	}

	private void addResource(FacesContext context, String name) {
		UIComponent resource = new UIOutput();
		resource.getAttributes().put("library", LIBRARY);
		resource.getAttributes().put("name", name);
		resource.setRendererType(context.getApplication().getResourceHandler().getRendererTypeForResourceName(name));
		context.getViewRoot().addComponentResource(context, resource, "head");
	}

}

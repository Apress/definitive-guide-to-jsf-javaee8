package com.example.project.view.component;

import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;

import com.example.project.model.Message;

@FacesComponent("messageComposite")
public class MessageComposite extends UINamingContainer {

	private Message message;

	@Override
	public void setValueExpression(String name, ValueExpression expression) {
		if ("value".equals(name)) {
			message = (Message) expression.getValue(getFacesContext().getELContext());
		}
		else {
			super.setValueExpression(name, expression);
		}
	}

	public Message getMessage() {
		return message;
	}
}

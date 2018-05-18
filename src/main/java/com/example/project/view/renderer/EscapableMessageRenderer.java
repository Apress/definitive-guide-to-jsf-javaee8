package com.example.project.view.renderer;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.context.ResponseWriterWrapper;

import com.sun.faces.renderkit.html_basic.MessageRenderer;

public class EscapableMessageRenderer extends MessageRenderer {

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		try {
			context.setResponseWriter(new ResponseWriterWrapper(writer) {
				@Override
				public void writeText(Object text, UIComponent component, String property) throws IOException {
					String string = text.toString();
					Object escape = component.getAttributes().get("escape");

					if (escape == null || Boolean.parseBoolean(escape.toString())) {
						super.writeText(string, component, property);
					}
					else {
						super.write(string);
					}
				}
			});

			super.encodeEnd(context, component);
		}
		finally {
			context.setResponseWriter(writer);
		}
	}
}
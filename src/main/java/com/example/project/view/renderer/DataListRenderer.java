package com.example.project.view.renderer;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

@FacesRenderer(componentFamily=UIData.COMPONENT_FAMILY, rendererType=DataListRenderer.RENDERER_TYPE)
public class DataListRenderer extends Renderer {

	public static final String RENDERER_TYPE = "project.List";

	@Override
	public boolean getRendersChildren() {
		return true;
	}

	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		UIData data = (UIData) component;

		if (data.getRowCount() > 0) {
			writer.startElement("ul", component);
		}
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		UIData data = (UIData) component;

		for (int i = 0; i < data.getRowCount(); i++) {
			data.setRowIndex(i);
			writer.startElement("li", component);

			if (component.getChildCount() > 0) {
				for (UIComponent child : component.getChildren()) {
					child.encodeAll(context);
				}
			}

			writer.endElement("li");
		}

		data.setRowIndex(-1);
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		UIData data = (UIData) component;

		if (data.getRowCount() > 0) {
			writer.endElement("ul");
		}
	}

}

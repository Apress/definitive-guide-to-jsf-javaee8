package com.example.project.view.component;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIData;

import com.example.project.view.renderer.DataListRenderer;

@FacesComponent(DataList.COMPONENT_TYPE)
public class DataList extends UIData {

	public static final String COMPONENT_TYPE = "project.DataList";

	public DataList() {
		setRendererType(DataListRenderer.RENDERER_TYPE);
	}

}
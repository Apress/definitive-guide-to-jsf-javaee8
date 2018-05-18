package com.example.project.view.component;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlInputFile;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

@FacesComponent(InputFile.COMPONENT_TYPE)
public class InputFile extends HtmlInputFile {

	public static final String COMPONENT_TYPE = "project.InputFile";

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		String accept = getAccept();

		if (accept != null) {
			getPassThroughAttributes().put("accept", accept);
		}

		super.encodeBegin(context);
	}

	@Override
	protected void validateValue(FacesContext context, Object newValue) {
		String accept = getAccept();

		if (accept != null && newValue instanceof Part) {
			Part part = (Part) newValue;
			String contentType = context.getExternalContext().getMimeType(part.getSubmittedFileName());
			String acceptPattern = accept.trim().replace("*", ".*").replaceAll("\\s*,\\s*", "|");

			if (contentType == null || !contentType.matches(acceptPattern)) {
				String message = "Unacceptable file type";
				context.addMessage(getClientId(context), new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
				setValid(false);
			}
		}

		if (isValid()) {
			super.validateValue(context, newValue);
		}
	}

	public String getAccept() {
		return (String) getStateHelper().eval("accept");
	}

	public void setAccept(String accept) {
		getStateHelper().put("accept", accept);
	}

}

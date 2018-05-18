package com.example.project.view.taghandler;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ComponentSystemEventListener;
import javax.faces.event.PostValidateEvent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

public class ViewParamValidationFailed extends TagHandler implements ComponentSystemEventListener {

	private String redirect;

    public ViewParamValidationFailed(TagConfig config) {
        super(config);
        redirect = getRequiredAttribute("redirect").getValue();
    }

    @Override
    public void apply(FaceletContext context, UIComponent parent) throws IOException {
        if (parent instanceof UIViewParameter && !context.getFacesContext().isPostback()) {
            parent.subscribeToEvent(PostValidateEvent.class, this);
        }
    }

    @Override
    public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
    	event.getComponent().unsubscribeFromEvent(PostValidateEvent.class, this);
        FacesContext context = event.getFacesContext();

        if (context.isValidationFailed()) {
            try {
                ExternalContext externalContext = context.getExternalContext();
                externalContext.redirect(externalContext.getRequestContextPath() + redirect);
            }
            catch (IOException e) {
                throw new AbortProcessingException(e);
            }
        }
    }

}
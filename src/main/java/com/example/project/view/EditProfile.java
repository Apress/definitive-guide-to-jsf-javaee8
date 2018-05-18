package com.example.project.view;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named @RequestScoped
public class EditProfile {

    private Part photo;

    public void upload() {
        String fileName = photo.getSubmittedFileName();
        String fileType = photo.getContentType();
        long fileSize = photo.getSize();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(fileType + " with name " + fileName + " of " + fileSize + " bytes successfully uploaded"));
    }

	public Part getPhoto() {
		return photo;
	}

	public void setPhoto(Part photo) {
		this.photo = photo;
	}

}

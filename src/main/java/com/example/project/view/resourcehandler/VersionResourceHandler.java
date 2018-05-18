package com.example.project.view.resourcehandler;

import java.io.IOException;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.application.ResourceHandlerWrapper;
import javax.faces.application.ResourceWrapper;

public class VersionResourceHandler extends ResourceHandlerWrapper {

	public VersionResourceHandler(ResourceHandler wrapped) {
		super(wrapped);
	}

	@Override
	public Resource createResource(String name, String library) {
		Resource resource = super.createResource(name, library);

		if (resource == null || library != null) {
			return resource;
		}

		return new ResourceWrapper(resource) {
			@Override
			public String getRequestPath() {
				String url = super.getRequestPath();
				return url + (url.contains("?") ? "&" : "?") + "v=" + getLastModified();
			}

			private long getLastModified() {
				try {
					return getWrapped().getURL().openConnection().getLastModified();
				}
				catch (IOException ignore) {
					return 0;
				}
			}
		};
	}
}

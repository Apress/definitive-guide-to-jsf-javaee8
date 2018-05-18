package com.example.project.view;

import javax.enterprise.context.RequestScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.project.service.LongRunningProcessService;

@Named
@RequestScoped
public class LongRunningProcess {

	@Inject
	private LongRunningProcessService service;

	@Inject @Push
	private PushContext push;

	public void submit() {
		service.asyncSubmit(result -> push.send(result));
	}
}

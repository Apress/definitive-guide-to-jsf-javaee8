package com.example.project.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.project.model.Result;
import com.example.project.service.LongRunningSearchService;

@Named
@ViewScoped
public class LongRunningSearch implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Result> results;

	@Inject
	private LongRunningSearchService service;

	@Inject @Push
	private PushContext push;

	@PostConstruct
	public void init() {
		service.asyncLoadResults(
				results -> {
					this.results = results;
					push.send("loaded");
				}
		);
	}

	public List<Result> getResults() {
		return results;
	}
}

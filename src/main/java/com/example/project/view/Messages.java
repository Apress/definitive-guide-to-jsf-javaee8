package com.example.project.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.project.model.Message;
import com.example.project.service.MessageService;

@Named
@ViewScoped
public class Messages implements Serializable {

	private static final long serialVersionUID = 1L;

	private Message message = new Message();
	private List<Message> list;
	private List<Message> tree;

	@Inject
	private MessageService messageService;

	@PostConstruct
	public void init() {
		list = messageService.list();
		tree = messageService.tree();
	}

	public void save() {
		messageService.create(message);
		list.add(message);
		message = new Message();
	}

	public Message getMessage() {
		return message;
	}

	public List<Message> getList() {
		return list;
	}

	public List<Message> getTree() {
		return tree;
	}

}

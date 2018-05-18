package com.example.project.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.project.model.Message;
import com.example.project.service.MessageService;

@Named
@RequestScoped
public class HelloWorldWithJPA {

	private Message message = new Message();
	private List<Message> messages;

	@Inject
	private MessageService messageService;

	@PostConstruct
	public void init() {
		messages = messageService.list();
	}

	public void submit() {
		messageService.create(message);
		messages.add(0, message);
		message = new Message();
	}

	public Message getMessage() {
		return message;
	}

	public List<Message> getMessages() {
		return messages;
	}

}

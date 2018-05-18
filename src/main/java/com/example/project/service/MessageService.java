package com.example.project.service;

import static java.util.stream.Collectors.toList;
import static javax.ejb.TransactionAttributeType.REQUIRED;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.project.model.Message;

@Stateless
public class MessageService {

	@PersistenceContext
	private EntityManager entityManager;

	@TransactionAttribute(REQUIRED)
	public Long create(Message message) {
		entityManager.persist(message);
		return message.getId();
	}

	@TransactionAttribute(REQUIRED) // Transaction is required in order to get JPA to auto-flush the by MarkdownListener auto-updated entities when Markdown version has changed.
	public List<Message> list() {
		return entityManager.createQuery("FROM Message m ORDER BY m.id DESC", Message.class).getResultList();
	}

	@TransactionAttribute(REQUIRED) // Transaction is required in order to get JPA to auto-flush the by MarkdownListener auto-updated entities when Markdown version has changed.
	public List<Message> tree() {
		return entityManager.createQuery("SELECT DISTINCT m FROM Message m LEFT JOIN FETCH m.replies r ORDER BY m.id ASC", Message.class)
			.getResultList().stream().filter(m -> m.getReplyTo() == null).collect(toList());
	}

}

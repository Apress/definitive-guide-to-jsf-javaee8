package com.example.project.service;

import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.project.model.Field;

@Stateless
public class FieldService {

	@PersistenceContext
	private EntityManager entityManager;

	@TransactionAttribute(REQUIRED)
	public Long create(Field field) {
		entityManager.persist(field);
		return field.getId();
	}

	@TransactionAttribute(SUPPORTS)
	public List<Field> list(String formId) {
		return entityManager.createQuery("FROM Field f WHERE f.formId = :formId ORDER BY f.ordering", Field.class).setParameter("formId", formId).getResultList();
	}

	@TransactionAttribute(REQUIRED)
	public String submit(String formId, String commandId, Map<String, Object> values, Runnable callback) {
		System.out.println("Form with ID '" + formId + "' was submitted via command with ID '" + commandId + "' with input values " + values);
		callback.run();
		return null;
	}

}

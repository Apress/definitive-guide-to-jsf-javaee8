package com.example.project.service;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.project.model.BaseEntity;

@Stateless
public class BaseEntityService {

	@PersistenceContext
	private EntityManager entityManager;

	@TransactionAttribute(SUPPORTS)
	public <E extends BaseEntity> E getById(Class<E> type, Long id) {
		return entityManager.find(type, id);
	}

}

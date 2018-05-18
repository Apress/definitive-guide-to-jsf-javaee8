package com.example.project.service;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TranslationService {

	@PersistenceContext
	private EntityManager entityManager;

	@TransactionAttribute(REQUIRES_NEW)
	@SuppressWarnings("unchecked")
	public Object[][] getContent(Locale locale, Locale fallback) {
		List<Object[]> resultList = entityManager.createQuery(
			"SELECT t1.key, COALESCE(t2.value, t1.value)"
				+ " FROM Translation t1"
				+ " LEFT OUTER JOIN Translation t2"
					+ " ON t2.key = t1.key"
					+ " AND t2.locale = :locale"
				+ " WHERE t1.locale = :fallback")
			.setParameter("locale", locale)
			.setParameter("fallback", fallback)
			.getResultList();
		return resultList.toArray(new Object[resultList.size()][]);
	}
}
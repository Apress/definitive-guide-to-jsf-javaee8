package com.example.project.service;

import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.project.model.Product;

@Stateless
public class ProductService {

	@PersistenceContext
	private EntityManager entityManager;

	@TransactionAttribute(SUPPORTS)
	public Product getById(Long id) {
		return entityManager.find(Product.class, id);
	}

	@TransactionAttribute(SUPPORTS)
	public List<Product> list() {
		return entityManager.createQuery("FROM Product p ORDER BY p.id DESC", Product.class).getResultList();
	}

	@TransactionAttribute(REQUIRED)
	public Long create(Product product) {
		entityManager.persist(product);
		return product.getId();
	}

	@TransactionAttribute(REQUIRED)
	public Product update(Product product) {
		return entityManager.merge(product);
	}

	@TransactionAttribute(REQUIRED)
	public void update(Iterable<Product> products) {
		products.forEach(this::update);
	}

	@TransactionAttribute(REQUIRED)
	public void delete(Product product) {
		if (entityManager.contains(product)) {
			entityManager.remove(product);
		}
		else {
			Product managedProduct = getById(product.getId());

			if (managedProduct != null) {
				entityManager.remove(managedProduct);
			}
		}
	}

	@TransactionAttribute(REQUIRED)
	public void delete(Iterable<Product> products) {
		products.forEach(this::delete);
	}

}

package com.example.project.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.project.model.Product;
import com.example.project.service.ProductService;

@Named
@RequestScoped
public class ListProducts {

	private List<Product> products;

	@Inject
	private ProductService productService;

	@PostConstruct
	public void init() {
		products = productService.list();
	}

	public List<Product> getProducts() {
		return products;
	}

}

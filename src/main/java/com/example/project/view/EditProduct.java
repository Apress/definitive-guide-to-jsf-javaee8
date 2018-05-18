package com.example.project.view;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.project.model.Product;
import com.example.project.service.ProductService;

@Named
@ViewScoped
public class EditProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	private Product product;

	@Inject
	private ProductService productService;

	public void save() {
		productService.update(product);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}

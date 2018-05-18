package com.example.project.view.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;

import com.example.project.model.Product;
import com.example.project.service.ProductService;

// @FacesConverter(forClass=Product.class, managed=true)
public class ProductConverter implements Converter<Product> {

	@Inject
	private ProductService productService;

	@Override
	public String getAsString(FacesContext context, UIComponent component, Product product) {
		if (product == null) {
			return "";
		}

		Long id = product.getId();
		return (id != null) ? id.toString() : null;
	}

	@Override
	public Product getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null || id.isEmpty()) {
			return null;
		}

		try {
			return productService.getById(Long.valueOf(id));
		}
		catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(String.format("%s is not a valid product ID", id)), e);
		}
	}

}

package com.example.project.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.project.model.Product;
import com.example.project.service.ProductService;

@Named
@RequestScoped
public class Products {

	private List<Product> list;
	private Product product = new Product();
	private Product selected;
	private Map<Product, Boolean> selection = new HashMap<>();

	@Inject
	private ProductService productService;

	@PostConstruct
	public void init() {
		list = productService.list();
	}

	public void save() {
		productService.update(list);
	}

	public void add() {
		productService.create(product);
		list.add(0, product);
		product = new Product();
	}

	public void deleteSelected() {
		productService.delete(selected);
		list.remove(selected);
	}

	public void deleteSelection() {
		List<Product> selected = selection.entrySet().stream().filter(Entry::getValue).map(Entry::getKey).collect(Collectors.toList());
		productService.delete(selected);
		selected.forEach(list::remove);
		selection.clear();
	}


	private Long id;
	public void delete(Product product) {
		productService.delete(product);
		list.remove(product);

		list.removeIf(p -> p.getId().equals(id));
	}

	public List<Product> getList() {
		return list;
	}

	public Product getProduct() {
		return product;
	}

	public Product getSelected() {
		return selected;
	}

	public void setSelected(Product selected) {
		this.selected = selected;
	}

	public Map<Product, Boolean> getSelection() {
		return selection;
	}

}

package com.example.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.api.model.Product;

public class BaseTest {

	protected List<Product> allProducts = this.retrieveProductList();
	protected Product product1 = this.createProduct(1);
	protected static final Long ID1 = new Long(1);

	protected BaseTest() {
		super();
	}

	/**
	 * Retrieves the list of {@link Product}s.
	 */
	private List<Product> retrieveProductList() {
		List<Product> products = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			products.add(this.createProduct(i));
		}
		return products;
	}

	/**
	 * Creates {@link Product}s for the tests.
	 */
	protected Product createProduct(int id) {
		Product product = new Product();
		product.setName("name" + id);
		product.setAvailable(true);
		product.setPrice(Float.valueOf(id));
		product.setDescription("description" + id);
		return product;
	}

}
package com.example.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.model.Product;
import com.example.api.repository.ProductRepository;
import com.example.api.service.ProductService;

/**
 * Implementation of {@link ProductService}.
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> list() {
		return productRepository.findAll();
	}

	public Product get(Long id) {
		if (org.springframework.util.StringUtils.isEmpty(id)) {
			throw new IllegalArgumentException("Id cannot be null or empty");
		}
		return productRepository.findOne(id);
	}

	public Product save(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("Product cannot be null");
		}
		return productRepository.save(product);
	}

	public void delete(Long id) {
		if (org.springframework.util.StringUtils.isEmpty(id)) {
			throw new IllegalArgumentException("Id cannot be null or empty");
		}
		productRepository.delete(id);
	}

}

package com.example.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Product;
import com.example.api.service.ProductService;

/**
 * Rest controller to manage requests related to products.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping
	public @ResponseBody List<Product> list() {
		logger.debug("Debugging list method");
		return productService.list();
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping(value = "/{id}")
	public Product get(@PathVariable String id) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Debugging get method with parameter: id = %s", id));
		}
		Long idValue = Long.valueOf(id);
		return productService.get(idValue);
	}

	@Secured("ROLE_ADMIN")
	@PostMapping
	public Product add(@RequestBody Product product) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Debugging add method with parameter: product = %s", product));
		}
		return productService.save(product);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping
	public Product update(@RequestBody Product product) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Debugging update method with parameter: product = %s", product));
		}
		return productService.save(product);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable String id) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Debugging delete method with parameter: id = %s", id));
		}
		Long idValue = Long.valueOf(id);
		productService.delete(idValue);
	}

}

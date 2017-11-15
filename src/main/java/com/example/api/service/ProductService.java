package com.example.api.service;

import java.util.List;

import com.example.api.model.Product;

/**
 * Service interface which defines CRUD operations for products.
 */
public interface ProductService {

	/**
	 * Retrieves the list of {@link Product}s in the repository.
	 * 
	 * @return List of {@link Product}s
	 */
	public List<Product> list();

	/**
	 * Retrieves the {@link Product} specified by parameter if exists in the
	 * repository.
	 * 
	 * @param id
	 *            the id of the {@link Product}
	 * @return the {@link Product} searched or {@code null} if not exists
	 */
	public Product get(Long id);

	/**
	 * Inserts or updates a {@link Product} in the repository.
	 * 
	 * @param product
	 *            the {@link Product} to be added or updated
	 * @return the {@link Product} added or updated
	 */
	public Product save(Product product);

	/**
	 * Deletes the {@link Product} specified by parameter from the repository.
	 * 
	 * @param id
	 *            the id of the {@link Product} to delete
	 */
	public void delete(Long id);

}

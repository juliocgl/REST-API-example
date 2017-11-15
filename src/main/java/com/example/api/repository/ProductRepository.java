package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.Product;

/**
 * Interface for CRUD operations on a repository for product model. 
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}

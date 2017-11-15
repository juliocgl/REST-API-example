package com.example.api.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.api.model.Product;
import com.example.api.service.ProductService;
import com.example.api.service.impl.BaseTest;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest extends BaseTest {

	@InjectMocks
	ProductController productController;

	@Mock
	private ProductService productService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		when(productService.list()).thenReturn(allProducts);
	}

	@Test
	@WithMockUser(value = "pepe", roles = "ADMIN")
	public void list_all() throws Exception {
		List<Product> products = productController.list();
		verify(productService).list();
		assertNotNull(products);
		assertEquals(3, products.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void get_whenIdIsNullThenGetException() {
		productController.get(null);
	}

	@Test
	public void get_whenIdNotFoundInDatabaseThenReturnNull() {
		Product product = productController.get("id2");
		assertNull(product);
	}

	@Test
	public void get_whenIdFoundInDatabaseThenReturnProduct() {
		Product product = productController.get(ID1.toString());
		assertNotNull(product);
		assertEquals(ID1, product.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void save_whenProductIsNullThenAddThrowsException() {
		productService.save(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void save_whenProductIsEmptyThenAddThrowsException() {
		productService.save(new Product());
	}

	@Test
	public void save_whenProductIsValidThenAdd() {
		Product product = productService.get(new Long(3));
		assertNull(product);
		product = createProduct(3);
		productService.save(product);
		verify(productService).save(product);
	}

}

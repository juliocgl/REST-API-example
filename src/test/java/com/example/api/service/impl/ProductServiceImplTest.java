package com.example.api.service.impl;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.api.model.Product;
import com.example.api.repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceImplTest extends BaseTest {

	@InjectMocks
	protected ProductServiceImpl productService;

	@Mock
	protected ProductRepository productRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(productRepository.findAll()).thenReturn(allProducts);
		when(productRepository.findOne(ID1)).thenReturn(product1);
	}

	@Test
	public void list_whenListEmptyReturnProductList() {
		List<Product> allProducts = productService.list();
		assertNotNull(allProducts);
		assertEquals(3, allProducts.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void get_whenIdIsNullThenGetException() {
		productService.get(null);
	}

	@Test
	public void get_whenIdNotFoundInDatabaseThenReturnNull() {
		Product product = productService.get(new Long(2));
		assertNull(product);
	}

	@Test
	public void get_whenIdFoundInDatabaseThenReturnProduct() {
		Product product = productService.get(ID1);
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

		verify(productRepository).save(product);
	}

}

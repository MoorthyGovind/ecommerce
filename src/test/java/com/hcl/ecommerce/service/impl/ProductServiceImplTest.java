package com.hcl.ecommerce.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.ecommerce.common.AppConstants;
import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.dto.SearchProductDto;
import com.hcl.ecommerce.dto.ViewProductDto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.entity.StoreProduct;
import com.hcl.ecommerce.entity.StoreProductRating;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.response.ApiResponse;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceImplTest {

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	ProductRepository productRepository;

	Product product = new Product();
	ProductDto productDto = new ProductDto();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		productDto.setCode("I-0001");
		productDto.setDescription("Cotton Shirts");
		productDto.setSize("38");
		productDto.setGrade("Full");
		productDto.setRevision("BLACK");
		productDto.setSpecification("Cotton Shirts with full hand and conformtable");

		product.setId(1L);
		product.setCode("I-0001");
		product.setSize("38");
		product.setGrade("BLACK");
		product.setRevision("Full");
		product.setDescription("Full Shirts");
		product.setCreatedBy("Admin");
		product.setCreatedDate(new Date());
	}

	@Test
	public void testCreateProduct() {
		when(productRepository.findByCode("I-0001")).thenReturn(null);
		when(productRepository.save(product)).thenReturn(product);

		ApiResponse apiResponse = productServiceImpl.createProduct(productDto);
		assertEquals(AppConstants.SUCCESS, apiResponse.getStatus());
	}

	@Test
	public void testCreateProductForRecordExists() {
		when(productRepository.findByCode("I-0001")).thenReturn(product);

		ApiResponse apiResponse = productServiceImpl.createProduct(productDto);
		assertEquals(AppConstants.FAILURE, apiResponse.getStatus());
	}

	@Test
	public void testFindProductsBySearch() {
		SearchProductDto searchProductDto = new SearchProductDto();
		searchProductDto.setSearchValue("I-0001");
		List<ProductDto> productDtos = new ArrayList<>();

		ProductDto productDto = new ProductDto();
		productDto.setCode("I-0001");

		productDtos.add(productDto);

		List<Product> products = new ArrayList<>();
		products.add(product);

		when(productRepository.findProductsBySearchValue("%" + searchProductDto.getSearchValue() + "%"))
				.thenReturn(products);

		productDtos = productServiceImpl.findProductsBySearch(searchProductDto);
		assertThat(productDtos).hasSize(1);
	}

	@Test
	public void testGetProductByCode() {

		Set<StoreProduct> storeProducts = new HashSet<>();
		StoreProduct storeProduct = new StoreProduct();

		Store store = new Store();
		store.setName("Ram Traders");
		store.setAddress("BLR");
		store.setLocation("Bangalore");

		User user = new User();
		user.setId(1L);
		user.setStoreProductRating(null);

		Set<StoreProductRating> storeProductRatings = new HashSet<>();
		StoreProductRating storeProductRating = new StoreProductRating();
		storeProductRating.setId(1L);
		storeProductRating.setUserId(user);
		storeProductRating.setStoreProductId(storeProduct);
		storeProductRating.setRatingValue(5);
		storeProductRatings.add(storeProductRating);

		storeProduct.setId(1L);
		storeProduct.setPriceAmount(200.00);
		storeProduct.setStoreId(store);
		storeProduct.setStoreProductRating(storeProductRatings);

		storeProducts.add(storeProduct);

		product.setStoreProduct(storeProducts);

		when(productRepository.findByCode("I-0001")).thenReturn(product);
		ViewProductDto viewProductDto = productServiceImpl.getProductByCode("I-0001");
		assertThat(viewProductDto).isNotNull();

		assertThat(viewProductDto.getProductCode()).isNotNull();
		assertThat(viewProductDto.getProductSize()).isNotNull();
		assertThat(viewProductDto.getProductGrade()).isNotNull();
		assertThat(viewProductDto.getProductRevision()).isNotNull();
		assertThat(viewProductDto.getProductDescription()).isNotNull();

		viewProductDto.getStoreProduct().forEach(product -> {
			assertEquals(200.00, product.getPriceAmount());
			assertEquals(5, product.getRating());
			assertEquals("BLR", product.getAddress());
			assertEquals("Ram Traders", product.getStore());
			assertEquals("Bangalore", product.getLocation());
		});
	}
}

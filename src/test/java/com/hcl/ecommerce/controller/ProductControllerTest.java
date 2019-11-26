package com.hcl.ecommerce.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.hcl.ecommerce.common.AppConstants;
import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.dto.SearchProductDto;
import com.hcl.ecommerce.dto.ViewProductDto;
import com.hcl.ecommerce.response.ApiResponse;
import com.hcl.ecommerce.service.ProductService;

public class ProductControllerTest {

	@InjectMocks
	ProductController productController;

	@Mock
	ProductService productService;

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
	}

	@Test
	public void testCreateProduct() {

		ApiResponse apiResponse = new ApiResponse(AppConstants.SUCCESS, 0, AppConstants.PRODUCT_SUCCESS_MESSAGE);

		when(productService.createProduct(productDto)).thenReturn(apiResponse);

		ResponseEntity<ApiResponse> result = productController.createProduct(productDto);
		assertEquals(201, result.getBody().getStatusCode());
	}

	@Test
	public void testCreateProductForNotFound() {
		ProductDto productDto = new ProductDto();
		productDto.setCode("I-100011");

		ApiResponse apiResponse = new ApiResponse(null, 0, null);
		when(productService.createProduct(productDto)).thenReturn(apiResponse);
		ResponseEntity<ApiResponse> result = productController.createProduct(productDto);
		assertEquals(404, result.getBody().getStatusCode());
	}

	@Test
	public void testCreateProductForRecordAlreadyExists() {
		ProductDto productDto = new ProductDto();
		productDto.setCode("I-0001");

		ApiResponse apiResponse = new ApiResponse(AppConstants.FAILURE, 0, AppConstants.STORE_ALREADY_EXISTS);
		when(productService.createProduct(productDto)).thenReturn(apiResponse);

		ResponseEntity<ApiResponse> result = productController.createProduct(productDto);
		assertEquals(409, result.getBody().getStatusCode());
	}

	@Test
	public void testFindProductsBySearch() {
		SearchProductDto searchProductDto = new SearchProductDto();
		searchProductDto.setSearchValue("I-0001");

		List<ProductDto> productDtos = new ArrayList<>();
		productDtos.add(productDto);

		when(productService.findProductsBySearch(searchProductDto)).thenReturn(productDtos);

		ResponseEntity<List<ProductDto>> result = productController.findProductsBySearch(searchProductDto);
		assertThat(result.getBody()).hasSize(1);
	}

	@Test
	public void testGetProductByCode() {
		ViewProductDto viewProductDto = new ViewProductDto();
		viewProductDto.setProductCode("I-0001");

		when(productService.getProductByCode("I-0001")).thenReturn(viewProductDto);

		ResponseEntity<?> result = productController.getProductByCode("I-0001");
		assertThat(result.getBody()).isNotNull();
	}

	@Test
	public void testGetProductByCodeForNotFound() {

		when(productService.getProductByCode("I-0001")).thenReturn(new ViewProductDto());

		ResponseEntity<?> result = productController.getProductByCode("I-0001");
		ApiResponse response = (ApiResponse) result.getBody();
		assertEquals(404, response.getStatusCode());
	}
}

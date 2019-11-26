package com.hcl.ecommerce.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hcl.ecommerce.common.AppConstants;
import com.hcl.ecommerce.dto.StoreProductDto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.entity.StoreProduct;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.repository.StoreProductRepository;
import com.hcl.ecommerce.repository.StoreRepository;
import com.hcl.ecommerce.response.ApiResponse;

public class StoreProductServiceImplTest {

	@InjectMocks
	StoreProductServiceImpl storeProductServiceImpl;

	@Mock
	StoreRepository storeRepository;

	@Mock
	ProductRepository productRepository;

	@Mock
	StoreProductRepository storeProductRepository;

	Store store = new Store();
	Product product = new Product();
	StoreProductDto storeProductDto = new StoreProductDto();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		store.setId(1L);
		store.setMobileNo("8675958381");

		product.setId(1L);
		product.setCode("I-0001");

		storeProductDto.setMobileNo("8675958381");
		storeProductDto.setProductCode("I-0001");
		storeProductDto.setPriceAmount(200.00);
	}

	@Test
	public void testCreateStoreProduct() {
		StoreProduct storeProduct = new StoreProduct();
		storeProduct.setId(1L);

		when(storeRepository.findByMobileNo("8675958381")).thenReturn(store);
		when(productRepository.findByCode("I-0001")).thenReturn(product);
		when(storeProductRepository.findByStoreIdMobileNoAndProductIdCode("8675958381", "I-0001")).thenReturn(null);
		when(storeProductRepository.save(new StoreProduct())).thenReturn(storeProduct);

		ApiResponse apiResponse = storeProductServiceImpl.createStoreProduct(storeProductDto);
		assertEquals(AppConstants.SUCCESS, apiResponse.getStatus());
	}

	@Test
	public void testCreateStoreProductForAlreadyExists() {
		StoreProduct storeProduct = new StoreProduct();
		storeProduct.setId(1L);

		when(storeRepository.findByMobileNo("8675958381")).thenReturn(store);
		when(productRepository.findByCode("I-0001")).thenReturn(product);
		when(storeProductRepository.findByStoreIdMobileNoAndProductIdCode("8675958381", "I-0001"))
				.thenReturn(storeProduct);

		ApiResponse apiResponse = storeProductServiceImpl.createStoreProduct(storeProductDto);
		assertEquals(AppConstants.FAILURE, apiResponse.getStatus());

	}

	@Test
	public void testCreateStoreProductForNotFound() {

		when(storeRepository.findByMobileNo("8675958382")).thenReturn(null);
		when(productRepository.findByCode("I-0003")).thenReturn(null);

		ApiResponse apiResponse = storeProductServiceImpl.createStoreProduct(storeProductDto);
		assertEquals(AppConstants.FAILURE, apiResponse.getStatus());
		assertEquals(AppConstants.NO_RECORD_FOUND, apiResponse.getMessage());
	}

}

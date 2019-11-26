package com.hcl.ecommerce.controller;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.ecommerce.common.AppConstants;
import com.hcl.ecommerce.dto.StoreProductDto;
import com.hcl.ecommerce.response.ApiResponse;
import com.hcl.ecommerce.service.StoreProductService;

@RunWith(SpringJUnit4ClassRunner.class)
public class StoreProductControllerTest {

	@InjectMocks
	StoreProductController storeProductController;

	@Mock
	StoreProductService storeProductService;

	@Before
	public void inint() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateStoreProduct() {
		StoreProductDto storeProductDto = new StoreProductDto();
		storeProductDto.setMobileNo("8675958381");
		storeProductDto.setProductCode("I-0001");
		storeProductDto.setPriceAmount(200.00);
		ApiResponse apiResponse = new ApiResponse(AppConstants.SUCCESS, 0, AppConstants.SUCCESS_MESSAGE);

		when(storeProductService.createStoreProduct(storeProductDto)).thenReturn(apiResponse);

		ResponseEntity<ApiResponse> result = storeProductController.createStoreProduct(storeProductDto);
		assertEquals(201, result.getBody().getStatusCode());
	}

	@Test
	public void testCreateStoreProductForNotFound() {
		StoreProductDto storeProductDto = new StoreProductDto();
		storeProductDto.setMobileNo("8675958382");
		storeProductDto.setProductCode("I-0002");
		storeProductDto.setPriceAmount(200.00);

		ApiResponse apiResponse = new ApiResponse(null, 0, null);
		when(storeProductService.createStoreProduct(storeProductDto)).thenReturn(apiResponse);
		ResponseEntity<ApiResponse> result = storeProductController.createStoreProduct(storeProductDto);
		assertEquals(404, result.getBody().getStatusCode());
	}

	@Test
	public void testCreateStoreProductForRecordAlreadyExists() {
		StoreProductDto storeProductDto = new StoreProductDto();
		storeProductDto.setMobileNo("8675958381");
		storeProductDto.setProductCode("I-0001");
		storeProductDto.setPriceAmount(200.00);

		ApiResponse apiResponse = new ApiResponse(AppConstants.FAILURE, 0, AppConstants.STORE_ALREADY_EXISTS);
		when(storeProductService.createStoreProduct(storeProductDto)).thenReturn(apiResponse);

		ResponseEntity<ApiResponse> result = storeProductController.createStoreProduct(storeProductDto);
		assertEquals(409, result.getBody().getStatusCode());
	}

}

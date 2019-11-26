package com.hcl.ecommerce.controller;

import static org.junit.jupiter.api.Assertions.*;
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
import com.hcl.ecommerce.dto.StoreDto;
import com.hcl.ecommerce.response.ApiResponse;
import com.hcl.ecommerce.service.StoreService;

@RunWith(SpringJUnit4ClassRunner.class)
public class StoreControllerTest {

	@InjectMocks
	StoreController storeController;

	@Mock
	StoreService storeService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateStore() {
		StoreDto storeDto = new StoreDto();
		storeDto.setMobileNo("8675958381");
		storeDto.setAddress("BLR");
		storeDto.setLocation("Bangalore");
		storeDto.setName("Moorthy Shirts");
		storeDto.setFaxNo("772732732");

		ApiResponse apiResponse = new ApiResponse(AppConstants.SUCCESS, 0, AppConstants.STORE_SUCCESS_MESSAGE);

		when(storeService.createStore(storeDto)).thenReturn(apiResponse);

		ResponseEntity<ApiResponse> result = storeController.createStore(storeDto);
		assertEquals(201, result.getBody().getStatusCode());
	}

	@Test
	public void testCreateStoreForNotFound() {
		StoreDto storeDto = new StoreDto();
		storeDto.setMobileNo("8675958323");

		ApiResponse apiResponse = new ApiResponse(null, 0, null);
		when(storeService.createStore(storeDto)).thenReturn(apiResponse);
		ResponseEntity<ApiResponse> result = storeController.createStore(storeDto);
		assertEquals(404, result.getBody().getStatusCode());
	}

	@Test
	public void testCreateStoreForRecordAlreadyExists() {
		StoreDto storeDto = new StoreDto();
		storeDto.setMobileNo("8675958381");

		ApiResponse apiResponse = new ApiResponse(AppConstants.FAILURE, 0, AppConstants.STORE_ALREADY_EXISTS);
		when(storeService.createStore(storeDto)).thenReturn(apiResponse);
		ResponseEntity<ApiResponse> result = storeController.createStore(storeDto);
		assertEquals(409, result.getBody().getStatusCode());
	}

}

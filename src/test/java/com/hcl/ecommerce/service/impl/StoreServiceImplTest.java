package com.hcl.ecommerce.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.ecommerce.common.AppConstants;
import com.hcl.ecommerce.dto.StoreDto;
import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.repository.StoreRepository;
import com.hcl.ecommerce.response.ApiResponse;

@RunWith(SpringJUnit4ClassRunner.class)
public class StoreServiceImplTest {

	@InjectMocks
	StoreServiceImpl storeServiceImpl;

	@Mock
	StoreRepository storeRepository;

	Store store = new Store();
	StoreDto storeDto = new StoreDto();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		storeDto.setMobileNo("8675958381");
		storeDto.setEmailAddress("moorthy127@gmail.com");
		storeDto.setLocation("BLR");
		storeDto.setFaxNo("98928392");
		storeDto.setName("Ram Traders");
		storeDto.setAddress("Bangalore");

		store.setId(1L);
		store.setMobileNo("8675958381");
		store.setStoreProduct(null);
	}

	@Test
	public void testCreateStore() {
		when(storeRepository.findByMobileNo("8675958381")).thenReturn(null);
		when(storeRepository.save(store)).thenReturn(store);

		ApiResponse apiResponse = storeServiceImpl.createStore(storeDto);
		assertEquals(AppConstants.SUCCESS, apiResponse.getStatus());
	}

	@Test
	public void testCreateStoreForRecordExists() {
		when(storeRepository.findByMobileNo("8675958381")).thenReturn(store);

		ApiResponse apiResponse = storeServiceImpl.createStore(storeDto);
		assertEquals(AppConstants.FAILURE, apiResponse.getStatus());
	}
}

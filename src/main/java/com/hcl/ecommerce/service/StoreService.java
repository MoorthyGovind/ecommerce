package com.hcl.ecommerce.service;

import com.hcl.ecommerce.dto.StoreDto;
import com.hcl.ecommerce.response.ApiResponse;

@FunctionalInterface
public interface StoreService {

	ApiResponse createStore(StoreDto storeDto);
}

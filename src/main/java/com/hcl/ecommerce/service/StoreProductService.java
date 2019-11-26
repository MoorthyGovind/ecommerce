package com.hcl.ecommerce.service;

import com.hcl.ecommerce.dto.StoreProductDto;
import com.hcl.ecommerce.response.ApiResponse;

@FunctionalInterface
public interface StoreProductService {

	ApiResponse createStoreProduct(StoreProductDto storeProductDto);

}

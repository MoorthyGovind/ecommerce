package com.hcl.ecommerce.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.common.AppConstants;
import com.hcl.ecommerce.dto.StoreProductDto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.entity.StoreProduct;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.repository.StoreProductRepository;
import com.hcl.ecommerce.repository.StoreRepository;
import com.hcl.ecommerce.response.ApiResponse;
import com.hcl.ecommerce.service.StoreProductService;

@Service
public class StoreProductServiceImpl implements StoreProductService {

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	StoreProductRepository storeProductRepository;

	/**
	 * createStore @StoreDto storeDto
	 */
	@Override
	public ApiResponse createStoreProduct(StoreProductDto storeProductDto) {
		ApiResponse apiResponse = new ApiResponse(null, 0, null);
		Store store = storeRepository.findByMobileNo(storeProductDto.getMobileNo());
		Product product = productRepository.findByCode(storeProductDto.getProductCode());
		Optional<Store> isStore = Optional.ofNullable(store);
		Optional<Product> isProduct = Optional.ofNullable(product);
		if (isStore.isPresent() && isProduct.isPresent()) {
			StoreProduct storeProduct = storeProductRepository.findByStoreIdMobileNoAndProductIdCode(
					storeProductDto.getMobileNo(), storeProductDto.getProductCode());
			Optional<StoreProduct> isStoreProduct = Optional.ofNullable(storeProduct);
			if (isStoreProduct.isPresent()) {
				apiResponse.setStatus(AppConstants.FAILURE);
				apiResponse.setMessage(AppConstants.RECORD_ALREADY_EXISTS);
			} else {
				StoreProduct result = new StoreProduct();
				result.setStoreId(store);
				result.setProductId(product);
				result.setPriceAmount(storeProductDto.getPriceAmount());
				result.setCreatedBy("Admin");

				storeProductRepository.save(result);

				apiResponse.setStatus(AppConstants.SUCCESS);
				apiResponse.setMessage(AppConstants.SUCCESS_MESSAGE);
			}
		} else {
			apiResponse.setStatus(AppConstants.FAILURE);
			apiResponse.setMessage(AppConstants.NO_RECORD_FOUND);
		}

		return apiResponse;
	}
}

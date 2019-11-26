package com.hcl.ecommerce.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.common.AppConstants;
import com.hcl.ecommerce.dto.StoreDto;
import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.repository.StoreRepository;
import com.hcl.ecommerce.response.ApiResponse;
import com.hcl.ecommerce.service.StoreService;
import com.hcl.ecommerce.util.ConverterUtil;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreRepository storeRepository;

	/**
	 * createStore @StoreDto storeDto
	 */
	@Override
	public ApiResponse createStore(StoreDto storeDto) {
		ApiResponse apiResponse = new ApiResponse(null, 0, null);
		Store store = storeRepository.findByMobileNo(storeDto.getMobileNo());
		Optional<Store> isStore = Optional.ofNullable(store);
		if (isStore.isPresent()) {
			apiResponse.setStatus(AppConstants.FAILURE);
			apiResponse.setMessage(AppConstants.STORE_ALREADY_EXISTS);
		} else {
			Store result = ConverterUtil.convertDtoToStoreEntity(storeDto);

			// save the store values.
			storeRepository.save(result);

			apiResponse.setStatus(AppConstants.SUCCESS);
			apiResponse.setMessage(AppConstants.STORE_SUCCESS_MESSAGE);
		}
		return apiResponse;
	}
}

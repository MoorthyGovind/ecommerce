package com.hcl.ecommerce.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.common.AppConstants;
import com.hcl.ecommerce.dto.StoreProductDto;
import com.hcl.ecommerce.response.ApiResponse;
import com.hcl.ecommerce.service.StoreProductService;

@RequestMapping("store/products")
@RestController
public class StoreProductController {
	private static final Logger logger = LoggerFactory.getLogger(StoreProductController.class);

	/**
	 * Store Product Controller - Rest Api's for the Products with Store.
	 */
	
	@Autowired
	StoreProductService storeProductService;

	/**
	 * createStoreProduct
	 * @param productDto
	 * @return
	 */
	@PostMapping("")
	public ResponseEntity<ApiResponse> createStoreProduct(@Valid @RequestBody StoreProductDto productDto) {
		logger.info("create store product method executing....");
		ApiResponse apiResponse = storeProductService.createStoreProduct(productDto);
		//Check the response status is success or not.
		Optional<String> isSuccess = Optional.ofNullable(apiResponse.getStatus());
		if (isSuccess.isPresent()) {
			if (apiResponse.getStatus().equals(AppConstants.SUCCESS)) {
				apiResponse.setStatusCode(HttpStatus.CREATED.value());
			} else {
				//Here we can handle that giving store product details are already exists in the database.
				apiResponse.setStatusCode(HttpStatus.CONFLICT.value());
			}
		} else {
			apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
}

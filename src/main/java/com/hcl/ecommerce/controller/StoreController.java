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
import com.hcl.ecommerce.dto.StoreDto;
import com.hcl.ecommerce.response.ApiResponse;
import com.hcl.ecommerce.service.StoreService;

@RestController
@RequestMapping("/stores")
public class StoreController {
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

	/**
	 * Store Controller - Rest Api's for the Store.
	 */

	@Autowired
	StoreService storeService;

	/**
	 * createStore
	 * @param storeDto
	 * @return
	 */
	@PostMapping("")
	public ResponseEntity<ApiResponse> createStore(@Valid @RequestBody StoreDto storeDto) {
		logger.info("create store method executing....");
		//Get the response for creating the store details.
		ApiResponse apiResponse = storeService.createStore(storeDto);
		
		//To check with that response status successful or not.
		Optional<String> isSuccess = Optional.ofNullable(apiResponse.getStatus());
		if (isSuccess.isPresent()) {
			if (apiResponse.getStatus().equals(AppConstants.SUCCESS)) {
				apiResponse.setStatusCode(HttpStatus.CREATED.value());
			} else {
				//Here we can handle that giving store details are already exists in the database.
				apiResponse.setStatusCode(HttpStatus.CONFLICT.value());
			}
		} else {
			apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
}

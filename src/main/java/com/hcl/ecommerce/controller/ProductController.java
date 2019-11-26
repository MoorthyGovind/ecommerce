package com.hcl.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.common.AppConstants;
import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.dto.SearchProductDto;
import com.hcl.ecommerce.dto.ViewProductDto;
import com.hcl.ecommerce.response.ApiResponse;
import com.hcl.ecommerce.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	/**
	 * Product Controller - Rest Api's for the Product.
	 */

	@Autowired
	ProductService productService;

	/**
	 * createProduct
	 * @param productDto
	 * @return
	 */
	@PostMapping("")
	public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody ProductDto productDto) {
		logger.info("create product method executing....");
		ApiResponse apiResponse = productService.createProduct(productDto);
		//Check the apiResponse status is success or not.
		Optional<String> isSuccess = Optional.ofNullable(apiResponse.getStatus());
		if (isSuccess.isPresent()) {
			if (apiResponse.getStatus().equals(AppConstants.SUCCESS)) {
				apiResponse.setStatusCode(HttpStatus.CREATED.value());
			} else {
				apiResponse.setStatusCode(HttpStatus.CONFLICT.value());
			}
		} else {
			apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	/**
	 * findProductsBySearch
	 * @param searchProductDto
	 * @return
	 */
	@PostMapping("/search")
	public ResponseEntity<List<ProductDto>> findProductsBySearch(@Valid @RequestBody SearchProductDto searchProductDto) {
		logger.info("find products by search value method executing....");
		//find the products by search value
		List<ProductDto> productDtos = productService.findProductsBySearch(searchProductDto);
		return new ResponseEntity<>(productDtos, HttpStatus.OK);
	}

	/**
	 * getProductByCode
	 * @param code 
	 * @return
	 */
	@GetMapping("/{code}")
	public ResponseEntity<Object> getProductByCode(@PathVariable String code) {
		logger.info("get product details method executing....");

		//Get the product details by giving the product code.
		ViewProductDto viewProductDto = productService.getProductByCode(code);
		
		//To check the products details is present or not.
		Optional<String> isViewProductDto = Optional.ofNullable(viewProductDto.getProductCode());
		if (isViewProductDto.isPresent()) {
			return new ResponseEntity<>(viewProductDto, HttpStatus.OK);
		} else {
			ApiResponse apiResponse = new ApiResponse(AppConstants.FAILURE, HttpStatus.NOT_FOUND.value(),
					AppConstants.NO_RECORD_FOUND);
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		}
	}
}

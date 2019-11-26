package com.hcl.ecommerce.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.common.AppConstants;
import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.dto.SearchProductDto;
import com.hcl.ecommerce.dto.ViewProductDto;
import com.hcl.ecommerce.dto.ViewStoreProductDto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.StoreProduct;
import com.hcl.ecommerce.entity.StoreProductRating;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.response.ApiResponse;
import com.hcl.ecommerce.service.ProductService;
import com.hcl.ecommerce.util.CommonUtil;
import com.hcl.ecommerce.util.ConverterUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	/**
	 * createStore @StoreDto storeDto
	 */
	@Override
	public ApiResponse createProduct(ProductDto productDto) {
		ApiResponse apiResponse = new ApiResponse(null, 0, null);
		Product product = productRepository.findByCode(productDto.getCode());
		Optional<Product> isProduct = Optional.ofNullable(product);
		if (isProduct.isPresent()) {
			apiResponse.setStatus(AppConstants.FAILURE);
			apiResponse.setMessage(AppConstants.PRODUCT_ALREADY_EXISTS);
		} else {
			Product result = ConverterUtil.convertDtoToProductEntity(productDto);

			// save the product values.
			productRepository.save(result);

			apiResponse.setStatus(AppConstants.SUCCESS);
			apiResponse.setMessage(AppConstants.PRODUCT_SUCCESS_MESSAGE);
		}
		return apiResponse;
	}

	/**
	 * findProductsBySearch
	 * 
	 * @SearchProductDto searchProductDto
	 */
	@Override
	public List<ProductDto> findProductsBySearch(SearchProductDto searchProductDto) {
		List<ProductDto> productDtos = new ArrayList<>();
		List<Product> products = productRepository
				.findProductsBySearchValue("%" + searchProductDto.getSearchValue() + "%");
		products.forEach(product -> productDtos.add(ConverterUtil.convertEntitytoDto(product)));
		return productDtos;
	}

	/**
	 * getProductByCode
	 * 
	 * @code code
	 */
	@Override
	public ViewProductDto getProductByCode(String code) {
		ViewProductDto viewProductDto = new ViewProductDto();
		Product product = productRepository.findByCode(code);
		Optional<Product> isProduct = Optional.ofNullable(product);
		if (isProduct.isPresent()) {
			viewProductDto.setProductCode(product.getCode());
			viewProductDto.setProductSize(product.getSize());
			viewProductDto.setProductGrade(product.getGrade());
			viewProductDto.setProductRevision(product.getRevision());
			viewProductDto.setProductDescription(product.getDescription());
			viewProductDto.setProductSpec(product.getSpecification());

			Set<StoreProduct> storeProducts = product.getStoreProduct();
			Set<ViewStoreProductDto> viewStoreProductDtos = new HashSet<>();
			storeProducts.forEach(storeProduct -> {
				ViewStoreProductDto viewStoreProductDto = ConverterUtil.convertDtoToStoreProductDto(storeProduct);

				Set<StoreProductRating> storeProductRating = storeProduct.getStoreProductRating();
				List<Integer> ratings = storeProductRating.stream().map(e -> e.getRatingValue())
						.collect(Collectors.toList());
				Double productRating = CommonUtil.calculateAverage(ratings);
				BigDecimal productRatingDecimal = new BigDecimal(productRating).setScale(1, RoundingMode.FLOOR);
				viewStoreProductDto.setRating(productRatingDecimal.doubleValue());

				viewStoreProductDtos.add(viewStoreProductDto);
			});

			viewProductDto.setStoreProduct(viewStoreProductDtos);
		}

		return viewProductDto;
	}
}

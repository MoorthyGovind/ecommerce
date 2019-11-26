package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.dto.SearchProductDto;
import com.hcl.ecommerce.dto.ViewProductDto;
import com.hcl.ecommerce.response.ApiResponse;

public interface ProductService {

	ApiResponse createProduct(ProductDto productDto);

	List<ProductDto> findProductsBySearch(SearchProductDto searchProductDto);

	ViewProductDto getProductByCode(String productCode);
}

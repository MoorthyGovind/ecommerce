package com.hcl.ecommerce.util;

import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.dto.StoreDto;
import com.hcl.ecommerce.dto.ViewStoreProductDto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.Store;
import com.hcl.ecommerce.entity.StoreProduct;

public class ConverterUtil {

	/**
	 * ConverterUtil class - its converts the values for DTO to Entity objects 
	 * 
	 */
	
	private ConverterUtil() {
	}

	/**
	 * convertDtoToStoreEntity
	 * 
	 * @param storeDto
	 * @return
	 */
	public static Store convertDtoToStoreEntity(StoreDto storeDto) {
		Store store = new Store();
		store.setName(storeDto.getName());
		store.setMobileNo(storeDto.getMobileNo());
		store.setEmailAddress(storeDto.getEmailAddress());
		store.setFaxNo(storeDto.getFaxNo());
		store.setAddress(storeDto.getAddress());
		store.setLocation(storeDto.getLocation());
		store.setCreatedBy("Admin");
		return store;
	}

	/**
	 * 
	 * @param productDto
	 * @return
	 */
	public static Product convertDtoToProductEntity(ProductDto productDto) {
		Product product = new Product();
		product.setCode(productDto.getCode());
		product.setDescription(productDto.getDescription());
		product.setSize(productDto.getSize());
		product.setGrade(productDto.getGrade());
		product.setRevision(productDto.getRevision());
		product.setUom(productDto.getUom());
		product.setSpecification(productDto.getSpecification());
		product.setCreatedBy("Admin");
		return product;
	}

	/**
	 * 
	 * @param product
	 * @return
	 */
	public static ProductDto convertEntitytoDto(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setCode(product.getCode());
		productDto.setDescription(product.getDescription());
		productDto.setSize(product.getSize());
		productDto.setGrade(product.getGrade());
		productDto.setRevision(product.getRevision());
		productDto.setUom(product.getUom());
		productDto.setSpecification(product.getSpecification());
		return productDto;
	}

	/**
	 * 
	 * @param storeProduct
	 * @return
	 */
	public static ViewStoreProductDto convertDtoToStoreProductDto(StoreProduct storeProduct) {
		ViewStoreProductDto viewStoreProductDto = new ViewStoreProductDto();
		viewStoreProductDto.setPriceAmount(storeProduct.getPriceAmount());
		viewStoreProductDto.setStore(storeProduct.getStoreId().getName());
		viewStoreProductDto.setAddress(storeProduct.getStoreId().getAddress());
		viewStoreProductDto.setLocation(storeProduct.getStoreId().getLocation());
		return viewStoreProductDto;
	}
}

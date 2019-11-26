package com.hcl.ecommerce.dto;

import java.util.Set;

public class ViewProductDto {

	private String productCode;
	private String productDescription;
	private String productSize;
	private String productGrade;
	private String productRevision;
	private String productSpec;
	private Set<ViewStoreProductDto> storeProduct;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public String getProductGrade() {
		return productGrade;
	}

	public void setProductGrade(String productGrade) {
		this.productGrade = productGrade;
	}

	public String getProductRevision() {
		return productRevision;
	}

	public void setProductRevision(String productRevision) {
		this.productRevision = productRevision;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public Set<ViewStoreProductDto> getStoreProduct() {
		return storeProduct;
	}

	public void setStoreProduct(Set<ViewStoreProductDto> storeProduct) {
		this.storeProduct = storeProduct;
	}
}

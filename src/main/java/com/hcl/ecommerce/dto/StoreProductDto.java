package com.hcl.ecommerce.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class StoreProductDto {

	@NotBlank(message = "product code should be mandatory")
	private String productCode;

	@NotBlank(message = "mobileNo should be mandatory")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid mobile no.")
	private String mobileNo;

	private Double priceAmount;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Double getPriceAmount() {
		return priceAmount;
	}

	public void setPriceAmount(Double priceAmount) {
		this.priceAmount = priceAmount;
	}

}

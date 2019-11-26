package com.hcl.ecommerce.dto;

import javax.validation.constraints.NotBlank;

public class SearchProductDto {

	@NotBlank(message = "searchValue should be mandatory")
	private String searchValue;

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

}

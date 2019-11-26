package com.hcl.ecommerce.dto;

public class ViewStoreProductDto {

	private String store;
	private String address;
	private String location;
	private Double priceAmount;
	private Double rating;

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getPriceAmount() {
		return priceAmount;
	}

	public void setPriceAmount(Double priceAmount) {
		this.priceAmount = priceAmount;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
}

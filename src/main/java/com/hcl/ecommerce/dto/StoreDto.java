package com.hcl.ecommerce.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class StoreDto {

	@NotBlank(message = "name should be mandatory")
	private String name;

	@NotBlank(message = "mobileNo should be mandatory")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid mobile no.")
	private String mobileNo;

	@NotBlank(message = "mobileNo should be mandatory")
	@Email(message = "Invalid Email address")
	private String emailAddress;

	private String faxNo;
	private String address;
	private String location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
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
}

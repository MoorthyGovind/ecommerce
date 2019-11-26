package com.hcl.ecommerce.response;

public class ApiResponse {

	/**
	 * common response for e-commerce request api's.
	 */

	private String status;
	private int statusCode;
	private String message;

	public ApiResponse(String status, int statusCode, String message) {
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

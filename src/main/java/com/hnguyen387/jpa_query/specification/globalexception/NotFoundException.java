package com.hnguyen387.jpa_query.specification.globalexception;

public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = -5069401301743180110L;

	public NotFoundException(String message) {
		super(message);
	}
	
}

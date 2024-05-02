package com.hnguyen387.jpa_query.criterialAPI.globalexceptions;

public class CannotUpdateException extends RuntimeException{

	private static final long serialVersionUID = -5982793096146981074L;

	public CannotUpdateException(String message) {
		super(message);
	}
	
}

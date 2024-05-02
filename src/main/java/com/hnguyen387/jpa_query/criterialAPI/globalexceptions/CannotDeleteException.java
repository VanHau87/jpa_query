package com.hnguyen387.jpa_query.criterialAPI.globalexceptions;

public class CannotDeleteException extends RuntimeException{

	private static final long serialVersionUID = 1976519404093094135L;

	public CannotDeleteException(String message) {
		super(message);
	}
	
}

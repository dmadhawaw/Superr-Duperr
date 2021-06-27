package com.superr.duperr.todolistapp.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3850500752256559303L;
	private String errorCode;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String message, ToDoListErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode.getCode();
	}

	public String getErrorCode() {
		return errorCode;
	}

}

package com.superr.duperr.todolistapp.exception;

public enum ToDoListErrorCode {
	NOT_FOUND("404"), BAD_REQUEST("400");

	private String code;

	ToDoListErrorCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}

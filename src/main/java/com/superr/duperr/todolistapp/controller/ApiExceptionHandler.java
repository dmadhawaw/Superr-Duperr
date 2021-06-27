package com.superr.duperr.todolistapp.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.superr.duperr.todolistapp.api.ApiResponse;
import com.superr.duperr.todolistapp.api.ErrorResponse;
import com.superr.duperr.todolistapp.exception.ResourceNotFoundException;
import com.superr.duperr.todolistapp.exception.ToDoListErrorCode;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ApiResponse<List<ErrorResponse>> handleConflict(ResourceNotFoundException ex, WebRequest request) {
		ErrorResponse errorResponse = ErrorResponse.builder().description(ex.getMessage()).errorCode(ex.getErrorCode())
				.build();
		ApiResponse<List<ErrorResponse>> myDataError = ApiResponse.<List<ErrorResponse>>builder()
				.errors(Arrays.asList(errorResponse)).build();
		return myDataError;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse handleConstraintViolationException(ConstraintViolationException ex) {
		List<ErrorResponse> errors = ex.getConstraintViolations().stream()
				.map(error -> apiErrorByConstraintViolation(error)).collect(Collectors.toList());
		return new ApiResponse(null, errors);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());
		List<ErrorResponse> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> apiErrorByFieldError(error)).collect(Collectors.toList());
		body.put("errors", errors);
		return new ResponseEntity<>(body, headers, status);

	}

	private ErrorResponse apiErrorByFieldError(FieldError error) {
		return ErrorResponse.builder().errorCode(ToDoListErrorCode.BAD_REQUEST.toString())
				.description(error.getDefaultMessage()).build();

	}

	private ErrorResponse apiErrorByConstraintViolation(ConstraintViolation error) {
		return ErrorResponse.builder().errorCode(ToDoListErrorCode.BAD_REQUEST.toString())
				.description(error.getMessage()).build();

	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ApiResponse<List<ErrorResponse>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {

		ErrorResponse errorResponse = ErrorResponse.builder().description(ex.getMessage()).errorCode(ex.getErrorCode())
				.build();
		ApiResponse<List<ErrorResponse>> myDataError = ApiResponse.<List<ErrorResponse>>builder()
				.errors(Arrays.asList(errorResponse)).build();
		return myDataError;
	}

}

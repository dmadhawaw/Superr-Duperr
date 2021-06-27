package com.superr.duperr.todolistapp.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ApiResponse<T> {

	private T data;

	private List<ErrorResponse> errors;
}

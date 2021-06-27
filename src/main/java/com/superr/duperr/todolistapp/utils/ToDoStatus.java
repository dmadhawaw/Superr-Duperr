package com.superr.duperr.todolistapp.utils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration of the ToDo Item status
 * 
 * @author dineshwijekoon
 *
 */
public enum ToDoStatus {
	PENDING("Pending"), COMPLETED("Completed"), INACTIVE("Inactive");

	private String status;

	private ToDoStatus(String status) {
		this.status = status;

	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(status);
	}

	public static ToDoStatus findByStatus(String byStatus) {
		for (ToDoStatus toDoSts : ToDoStatus.values()) {
			if (toDoSts.status.equalsIgnoreCase(byStatus)) {
				return toDoSts;
			}
		}
		return null;
	}

}

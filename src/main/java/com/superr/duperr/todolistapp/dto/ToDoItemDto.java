package com.superr.duperr.todolistapp.dto;

import java.util.Date;

import com.superr.duperr.todolistapp.domain.ToDoItem;
import com.superr.duperr.todolistapp.domain.ToDoItemWork;

public class ToDoItemDto {

	private long todoId;

	private String task;

	private String status;

	private Date dateCreated;

	/**
	 * Construct a ToDoItemDto from a fully instantiated ToDoItem.
	 *
	 * @param toDoItem ToDoItem Object
	 */
	public ToDoItemDto(ToDoItem toDoItem) {
		this(toDoItem.getTodoId(), toDoItem.getTask(), toDoItem.getStatus(), toDoItem.getDateCreated());
	}

	public ToDoItemDto(long todoId, String task, String status, Date dateCreated) {
		this.todoId = todoId;
		this.task = task;
		this.status = status;
		this.dateCreated = dateCreated;
	}

	public long getTodoId() {
		return todoId;
	}

	public void setTodoId(long todoId) {
		this.todoId = todoId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

}

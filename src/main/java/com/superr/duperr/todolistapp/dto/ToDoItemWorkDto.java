package com.superr.duperr.todolistapp.dto;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;
import com.superr.duperr.todolistapp.domain.ToDoItemWork;

/**
 * Data Transfer Object for work to do item.
 * 
 * @author dineshwijekoon
 *
 */
public class ToDoItemWorkDto {

	private static final String TODO_TYPE_WORK = "WORK_LIST";

	private long todoId;

	@NotNull
	private String task;

	private String status;

	private String priority;

	@Size(max = 10)
	private String todolist_type;

	/**
	 * Construct a WorkToDoItemDto from a fully instantiated WorkToDoItem.
	 *
	 * @param toDoItem WorkToDoItem Object
	 */
	public ToDoItemWorkDto(ToDoItemWork toDoItem) {
		this(toDoItem.getTodoId(), toDoItem.getTask(), toDoItem.getStatus(), toDoItem.getPriority(), TODO_TYPE_WORK);
	}

	/**
	 * Constructor to fully initialize the WorkToDoItemDto
	 *
	 * @param task          ToDo task
	 * @param priority      priority
	 * @param todolist_type type of ToDo task
	 */
	private ToDoItemWorkDto(long todoId, String task, String status, String priority, String todolist_type) {
		this.todoId = todoId;
		this.task = task;
		this.status = status;
		this.priority = priority;
		this.todolist_type = todolist_type;
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

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTodolist_type() {
		return todolist_type;
	}

	public void setTodolist_type(String todolist_type) {
		this.todolist_type = todolist_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

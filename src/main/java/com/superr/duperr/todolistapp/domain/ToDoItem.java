package com.superr.duperr.todolistapp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Table(name = "todo_list")
@DiscriminatorColumn(name = "TODOLIST_TYPE")
public class ToDoItem {

	private Long todoId;

	private String task;

	private String status;

	private Date dateCreated;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "todo_id")
	public Long getTodoId() {
		return todoId;
	}

	public void setTodoId(Long todoId) {
		this.todoId = todoId;
	}

	@Column(name = "task")
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "date_created")
	@JsonFormat(pattern="yyyy-MM-dd")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

}

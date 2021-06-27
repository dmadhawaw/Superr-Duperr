package com.superr.duperr.todolistapp.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WORK_LIST")
public class ToDoItemWork extends ToDoItem {

	private String priority;

	private long reminderDuration;

	@Column(name = "reminder")
	public long getReminderDuration() {
		return reminderDuration;
	}

	public void setReminderDuration(long reminderDuration) {
		this.reminderDuration = reminderDuration;
	}

	@Column(name = "priority")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	

}

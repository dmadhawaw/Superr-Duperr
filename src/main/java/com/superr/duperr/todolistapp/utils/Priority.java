package com.superr.duperr.todolistapp.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Priority {

	HIGH("High", 10800000), MEDIUM("Medium", 43200000), LOW("Low", 86400000);

	public String getPriority() {
		return priority;
	}

	private final String priority;
	private final long duration;

	Priority(String priority, long duration) {
		this.priority = priority;
		this.duration = duration;
	}

	public long getDuration() {
		return duration;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(priority);
	}

	//TODO Stream
	public static Priority fromString(String text) {
		for (Priority p : Priority.values()) {
			if (p.priority.equalsIgnoreCase(text)) {
				return p;
			}
		}
		return null;
	}

	//TODO
	public static Long getDurationByName(String name) {
		for (Priority e : Priority.values()) {
			if (e.name().equalsIgnoreCase(name))
				return e.getDuration();
		}
		return null;
	}

}

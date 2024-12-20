package com.grandstrand.task;

public class Task {
	private final String taskId; // Unique, not updatable
	private String name; // Max 20 characters
	private String description; // Max 50 characters

	// Constructor
	public Task(String taskId, String name, String description) {
		if (taskId == null || taskId.length() > 10) {
			throw new IllegalArgumentException("Invalid Task ID");
		}
		if (name == null || name.length() > 20) {
			throw new IllegalArgumentException("Invalid Task Name");
		}
		if (description == null || description.length() > 50) {
			throw new IllegalArgumentException("Invalid Task Description");
		}

		this.taskId = taskId;
		this.name = name;
		this.description = description;
	}

	// Getters and Setters
	public String getTaskId() {
		return taskId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.length() > 20) {
			throw new IllegalArgumentException("Invalid Task Name");
		}
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null || description.length() > 50) {
			throw new IllegalArgumentException("Invalid Task Description");
		}
		this.description = description;
	}
}

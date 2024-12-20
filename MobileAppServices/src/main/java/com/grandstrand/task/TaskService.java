package com.grandstrand.task;

import java.util.HashMap;
import java.util.Map;

public class TaskService {
	private final Map<String, Task> tasks = new HashMap<>();

	// Add a new task
	public void addTask(Task task) {
		if (tasks.containsKey(task.getTaskId())) {
			throw new IllegalArgumentException("Task ID already exists.");
		}
		tasks.put(task.getTaskId(), task);
	}

	// Delete a task by ID
	public void deleteTask(String taskId) {
		if (!tasks.containsKey(taskId)) {
			throw new IllegalArgumentException("Task ID not found.");
		}
		tasks.remove(taskId);
	}

	// Update an existing task's fields
	public void updateTask(String taskId, String name, String description) {
		Task task = tasks.get(taskId);

		if (task == null) {
			throw new IllegalArgumentException("Task ID not found.");
		}

		// Update fields with validation
		if (name != null && !name.isBlank()) {
			task.setName(name);
		}
		if (description != null && !description.isBlank()) {
			task.setDescription(description);
		}
	}

	// Retrieve all tasks (optional utility method)
	public Map<String, Task> getAllTasks() {
		return new HashMap<>(tasks);
	}
}

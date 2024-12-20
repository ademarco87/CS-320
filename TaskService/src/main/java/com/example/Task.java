package com.example;

public class Task {
    private final String taskId; // Task ID (cannot be updated)
    private String name;         // Name of the task
    private String description;  // Description of the task

    // Constructor
    public Task(String taskId, String name, String description) {
        // Validate taskId
        if (taskId == null || taskId.length() > 10) {
            throw new IllegalArgumentException("Task ID must be non-null and no longer than 10 characters.");
        }
        // Validate name
        if (name == null || name.length() > 20) {
            throw new IllegalArgumentException("Task name must be non-null and no longer than 20 characters.");
        }
        // Validate description
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Task description must be non-null and no longer than 50 characters.");
        }

        this.taskId = taskId;
        this.name = name;
        this.description = description;
    }

    // Getter for taskId (final - cannot be updated)
    public String getTaskId() {
        return taskId;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        if (name == null || name.length() > 20) {
            throw new IllegalArgumentException("Task name must be non-null and no longer than 20 characters.");
        }
        this.name = name;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Task description must be non-null and no longer than 50 characters.");
        }
        this.description = description;
    }
}

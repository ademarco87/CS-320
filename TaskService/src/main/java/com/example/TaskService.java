package com.example;

import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private final Map<String, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException("Task with the same ID already exists.");
        }
        tasks.put(task.getTaskId(), task);
    }

    public void deleteTask(String taskId) {
        if (!tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("Task with the specified ID does not exist.");
        }
        tasks.remove(taskId);
    }

    public void updateTaskName(String taskId, String name) {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Task with the specified ID does not exist.");
        }
        task.setName(name);
    }

    public void updateTaskDescription(String taskId, String description) {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Task with the specified ID does not exist.");
        }
        task.setDescription(description);
    }

    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }
}

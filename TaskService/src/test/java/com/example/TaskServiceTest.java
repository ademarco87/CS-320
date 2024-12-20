package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    @Test
    public void testAddTask() {
        TaskService taskService = new TaskService();
        Task task = new Task("12345", "Test Task", "Description");
        taskService.addTask(task);

        Task retrievedTask = taskService.getTask("12345");
        assertAll("Add Task",
            () -> assertNotNull(retrievedTask, "Task should be added and retrievable."),
            () -> assertEquals("12345", retrievedTask.getTaskId(), "Task ID should match."),
            () -> assertEquals("Test Task", retrievedTask.getName(), "Task name should match."),
            () -> assertEquals("Description", retrievedTask.getDescription(), "Task description should match.")
        );
    }

    @Test
    public void testDeleteTask() {
        TaskService taskService = new TaskService();
        Task task = new Task("12345", "Test Task", "Description");
        taskService.addTask(task);

        taskService.deleteTask("12345");
        assertAll("Delete Task",
            () -> assertNull(taskService.getTask("12345"), "Task should be deleted."),
            () -> assertThrows(IllegalArgumentException.class, () -> taskService.deleteTask("12345"), "Deleting a non-existent task should throw an exception.")
        );
    }

    @Test
    public void testUpdateTaskName() {
        TaskService taskService = new TaskService();
        Task task = new Task("12345", "Initial Name", "Description");
        taskService.addTask(task);

        taskService.updateTaskName("12345", "Updated Name");
        Task updatedTask = taskService.getTask("12345");

        assertAll("Update Task Name",
            () -> assertEquals("Updated Name", updatedTask.getName(), "Task name should be updated."),
            () -> assertEquals("12345", updatedTask.getTaskId(), "Task ID should remain unchanged."),
            () -> assertEquals("Description", updatedTask.getDescription(), "Task description should remain unchanged.")
        );
    }

    @Test
    public void testUpdateTaskDescription() {
        TaskService taskService = new TaskService();
        Task task = new Task("12345", "Name", "Initial Description");
        taskService.addTask(task);

        taskService.updateTaskDescription("12345", "Updated Description");
        Task updatedTask = taskService.getTask("12345");

        assertAll("Update Task Description",
            () -> assertEquals("Updated Description", updatedTask.getDescription(), "Task description should be updated."),
            () -> assertEquals("12345", updatedTask.getTaskId(), "Task ID should remain unchanged."),
            () -> assertEquals("Name", updatedTask.getName(), "Task name should remain unchanged.")
        );
    }
}

package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testValidTaskCreation() {
        Task task = new Task("12345", "Test Task", "This is a test description.");

        assertAll("Valid Task Creation",
            () -> assertEquals("12345", task.getTaskId(), "Task ID should match the expected value."),
            () -> assertEquals("Test Task", task.getName(), "Task name should match the expected value."),
            () -> assertEquals("This is a test description.", task.getDescription(), "Task description should match the expected value.")
        );
    }

    @Test
    public void testInvalidTaskId() {
        assertAll("Invalid Task ID",
            () -> assertThrows(IllegalArgumentException.class, () -> new Task(null, "Test Task", "Description"), "Task ID should not be null."),
            () -> assertThrows(IllegalArgumentException.class, () -> new Task("12345678901", "Test Task", "Description"), "Task ID should not exceed 10 characters.")
        );
    }

    @Test
    public void testInvalidName() {
        assertAll("Invalid Task Name",
            () -> assertThrows(IllegalArgumentException.class, () -> new Task("12345", null, "Description"), "Task name should not be null."),
            () -> assertThrows(IllegalArgumentException.class, () -> new Task("12345", "This name is way too long for validation", "Description"), "Task name should not exceed 20 characters.")
        );
    }

    @Test
    public void testInvalidDescription() {
        assertAll("Invalid Task Description",
            () -> assertThrows(IllegalArgumentException.class, () -> new Task("12345", "Test Task", null), "Task description should not be null."),
            () -> assertThrows(IllegalArgumentException.class, () -> new Task("12345", "Test Task", "This description is way too long and exceeds the fifty character limit allowed."), "Task description should not exceed 50 characters.")
        );
    }

    @Test
    public void testUpdateName() {
        Task task = new Task("12345", "Initial Name", "Description");
        task.setName("Updated Name");

        assertAll("Update Task Name",
            () -> assertEquals("Updated Name", task.getName(), "Task name should be updated."),
            () -> assertEquals("12345", task.getTaskId(), "Task ID should remain unchanged."),
            () -> assertEquals("Description", task.getDescription(), "Task description should remain unchanged.")
        );
    }

    @Test
    public void testUpdateDescription() {
        Task task = new Task("12345", "Name", "Initial Description");
        task.setDescription("Updated Description");

        assertAll("Update Task Description",
            () -> assertEquals("Updated Description", task.getDescription(), "Task description should be updated."),
            () -> assertEquals("12345", task.getTaskId(), "Task ID should remain unchanged."),
            () -> assertEquals("Name", task.getName(), "Task name should remain unchanged.")
        );
    }
}

package com.grandstrand.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testTaskCreationValidData() {
        Task task = new Task("12345", "Code Review", "Review code for quality.");
        assertAll(
            "Valid Task Creation",
            () -> assertEquals("12345", task.getTaskId(), "Task ID should match"),
            () -> assertEquals("Code Review", task.getName(), "Task name should match"),
            () -> assertEquals("Review code for quality.", task.getDescription(), "Task description should match")
        );
    }

    @Test
    void testTaskCreationInvalidTaskId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Task(null, "Code Review", "Review code for quality.")
        );
        assertAll(
            "Invalid Task ID",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Task ID", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testSetNameValid() {
        Task task = new Task("12345", "Code Review", "Review code for quality.");
        task.setName("Code Inspection");
        assertAll(
            "Set Task Name",
            () -> assertEquals("Code Inspection", task.getName(), "Task name should be updated"),
            () -> assertNotEquals("Code Review", task.getName(), "Old name should not match")
        );
    }

    @Test
    void testSetNameInvalid() {
        Task task = new Task("12345", "Code Review", "Review code for quality.");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> task.setName(null));
        assertAll(
            "Invalid Task Name",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Task Name", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testSetDescriptionValid() {
        Task task = new Task("12345", "Code Review", "Review code for quality.");
        task.setDescription("Inspect the code thoroughly.");
        assertAll(
            "Set Task Description",
            () -> assertEquals("Inspect the code thoroughly.", task.getDescription(), "Task description should be updated"),
            () -> assertNotEquals("Review code for quality.", task.getDescription(), "Old description should not match")
        );
    }

    @Test
    void testSetDescriptionInvalid() {
        Task task = new Task("12345", "Code Review", "Review code for quality.");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> task.setDescription(null));
        assertAll(
            "Invalid Task Description",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Task Description", exception.getMessage(), "Error message should match")
        );
    }
}

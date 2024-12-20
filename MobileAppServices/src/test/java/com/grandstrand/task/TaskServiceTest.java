package com.grandstrand.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    @Test
    void testAddTask() {
        TaskService service = new TaskService();
        Task task = new Task("12345", "Code Review", "Review code for quality.");

        service.addTask(task);
        assertAll(
            "Add Task",
            () -> assertTrue(service.getAllTasks().containsKey("12345"), "Task should be added"),
            () -> assertEquals(task, service.getAllTasks().get("12345"), "Task should match")
        );
    }

    @Test
    void testAddTaskDuplicateId() {
        TaskService service = new TaskService();
        Task task1 = new Task("12345", "Code Review", "Review code for quality.");
        Task task2 = new Task("12345", "Inspection", "Inspect thoroughly.");

        service.addTask(task1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.addTask(task2));
        assertAll(
            "Add Duplicate Task ID",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Task ID already exists.", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testDeleteTask() {
        TaskService service = new TaskService();
        Task task = new Task("12345", "Code Review", "Review code for quality.");

        service.addTask(task);
        service.deleteTask("12345");

        assertAll(
            "Delete Task",
            () -> assertFalse(service.getAllTasks().containsKey("12345"), "Task should be deleted"),
            () -> assertNull(service.getAllTasks().get("12345"), "Deleted task should not exist")
        );
    }

    @Test
    void testDeleteTaskInvalidId() {
        TaskService service = new TaskService();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.deleteTask("99999"));
        assertAll(
            "Delete Nonexistent Task",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Task ID not found.", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testUpdateTask() {
        TaskService service = new TaskService();
        Task task = new Task("12345", "Code Review", "Review code for quality.");

        service.addTask(task);
        service.updateTask("12345", "Code Inspection", "Inspect the code thoroughly.");

        Task updatedTask = service.getAllTasks().get("12345");
        assertAll(
            "Update Task",
            () -> assertEquals("Code Inspection", updatedTask.getName(), "Task name should be updated"),
            () -> assertEquals("Inspect the code thoroughly.", updatedTask.getDescription(), "Task description should be updated")
        );
    }

    @Test
    void testUpdateTaskInvalidId() {
        TaskService service = new TaskService();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            service.updateTask("99999", "Code Inspection", "Inspect the code thoroughly.")
        );
        assertAll(
            "Update Nonexistent Task",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Task ID not found.", exception.getMessage(), "Error message should match")
        );
    }
}

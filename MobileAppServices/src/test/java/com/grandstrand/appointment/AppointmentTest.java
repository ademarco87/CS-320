package com.grandstrand.appointment;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    @Test
    void testAppointmentCreationValidData() {
        Date futureDate = new Date(System.currentTimeMillis() + 86400000); // 1 day in the future
        Appointment appointment = new Appointment("12345", futureDate, "Doctor's appointment");

        assertAll(
            "Valid Appointment Creation",
            () -> assertEquals("12345", appointment.getAppointmentId(), "Appointment ID should match"),
            () -> assertEquals(futureDate, appointment.getAppointmentDate(), "Appointment date should match"),
            () -> assertEquals("Doctor's appointment", appointment.getDescription(), "Appointment description should match")
        );
    }

    @Test
    void testAppointmentCreationInvalidId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Appointment("12345678901", new Date(System.currentTimeMillis() + 86400000), "Doctor's appointment")
        );

        assertAll(
            "Invalid Appointment ID",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Appointment ID", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testAppointmentCreationNullId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Appointment(null, new Date(System.currentTimeMillis() + 86400000), "Doctor's appointment")
        );

        assertAll(
            "Null Appointment ID",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Appointment ID", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testAppointmentCreationPastDate() {
        Date pastDate = new Date(System.currentTimeMillis() - 86400000); // 1 day in the past
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Appointment("12345", pastDate, "Doctor's appointment")
        );

        assertAll(
            "Invalid Appointment Date (Past)",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Appointment Date", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testAppointmentCreationNullDate() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Appointment("12345", null, "Doctor's appointment")
        );

        assertAll(
            "Null Appointment Date",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Appointment Date", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testAppointmentCreationInvalidDescriptionTooLong() {
        String longDescription = "This is a very long description that exceeds the allowed 50 character limit.";
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Appointment("12345", new Date(System.currentTimeMillis() + 86400000), longDescription)
        );

        assertAll(
            "Invalid Appointment Description (Too Long)",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Appointment Description", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testAppointmentCreationNullDescription() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new Appointment("12345", new Date(System.currentTimeMillis() + 86400000), null)
        );

        assertAll(
            "Null Appointment Description",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Appointment Description", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testSetDescriptionValid() {
        Appointment appointment = new Appointment("12345", new Date(System.currentTimeMillis() + 86400000), "Checkup");
        appointment.setDescription("Follow-up appointment");

        assertAll(
            "Set Appointment Description",
            () -> assertEquals("Follow-up appointment", appointment.getDescription(), "Appointment description should be updated"),
            () -> assertNotEquals("Checkup", appointment.getDescription(), "Old description should not match")
        );
    }

    @Test
    void testSetDescriptionInvalidTooLong() {
        Appointment appointment = new Appointment("12345", new Date(System.currentTimeMillis() + 86400000), "Checkup");
        String longDescription = "This is a very long description that exceeds the allowed 50 character limit.";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> appointment.setDescription(longDescription));

        assertAll(
            "Invalid Appointment Description (Too Long)",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Appointment Description", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testSetDescriptionNull() {
        Appointment appointment = new Appointment("12345", new Date(System.currentTimeMillis() + 86400000), "Checkup");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> appointment.setDescription(null));

        assertAll(
            "Null Appointment Description",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Invalid Appointment Description", exception.getMessage(), "Error message should match")
        );
    }
}

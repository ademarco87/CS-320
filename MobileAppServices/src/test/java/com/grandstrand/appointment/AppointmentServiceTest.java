package com.grandstrand.appointment;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;


class AppointmentServiceTest {

    @Test
    void testAddAppointment() {
        AppointmentService service = new AppointmentService();
        Appointment appointment = new Appointment("12345", new Date(System.currentTimeMillis() + 86400000), "Doctor's appointment");

        service.addAppointment(appointment);
        assertAll(
            "Add Appointment",
            () -> assertTrue(service.getAllAppointments().containsKey("12345"), "Appointment should be added"),
            () -> assertEquals(appointment, service.getAllAppointments().get("12345"), "Appointment should match")
        );
    }

    @Test
    void testAddAppointmentDuplicateId() {
        AppointmentService service = new AppointmentService();
        Appointment appointment1 = new Appointment("12345", new Date(System.currentTimeMillis() + 86400000), "Doctor's appointment");
        Appointment appointment2 = new Appointment("12345", new Date(System.currentTimeMillis() + 86400000), "Follow-up appointment");

        service.addAppointment(appointment1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.addAppointment(appointment2));
        assertAll(
            "Add Duplicate Appointment ID",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Appointment ID already exists.", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testDeleteAppointment() {
        AppointmentService service = new AppointmentService();
        Appointment appointment = new Appointment("12345", new Date(System.currentTimeMillis() + 86400000), "Doctor's appointment");

        service.addAppointment(appointment);
        service.deleteAppointment("12345");

        assertAll(
            "Delete Appointment",
            () -> assertFalse(service.getAllAppointments().containsKey("12345"), "Appointment should be deleted"),
            () -> assertNull(service.getAllAppointments().get("12345"), "Deleted appointment should not exist")
        );
    }

    @Test
    void testDeleteAppointmentInvalidId() {
        AppointmentService service = new AppointmentService();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.deleteAppointment("99999"));
        assertAll(
            "Delete Nonexistent Appointment",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Appointment ID not found.", exception.getMessage(), "Error message should match")
        );
    }

    @Test
    void testUpdateAppointmentDescription() {
        AppointmentService service = new AppointmentService();
        Appointment appointment = new Appointment("12345", new Date(System.currentTimeMillis() + 86400000), "Doctor's appointment");

        service.addAppointment(appointment);
        service.updateAppointment("12345", "Follow-up appointment");

        Appointment updatedAppointment = service.getAllAppointments().get("12345");
        assertAll(
            "Update Appointment Description",
            () -> assertEquals("Follow-up appointment", updatedAppointment.getDescription(), "Appointment description should be updated"),
            () -> assertNotEquals("Doctor's appointment", updatedAppointment.getDescription(), "Old description should not match")
        );
    }

    @Test
    void testUpdateAppointmentInvalidId() {
        AppointmentService service = new AppointmentService();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            service.updateAppointment("99999", "Follow-up appointment")
        );
        assertAll(
            "Update Nonexistent Appointment",
            () -> assertNotNull(exception, "Exception should be thrown"),
            () -> assertEquals("Appointment ID not found.", exception.getMessage(), "Error message should match")
        );
    }
}

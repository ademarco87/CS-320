package com.grandstrand.appointment;

import java.util.HashMap;
import java.util.Map;

public class AppointmentService {
    private final Map<String, Appointment> appointments = new HashMap<>();

    // Add a new appointment
    public void addAppointment(Appointment appointment) {
        if (appointments.containsKey(appointment.getAppointmentId())) {
            throw new IllegalArgumentException("Appointment ID already exists.");
        }
        appointments.put(appointment.getAppointmentId(), appointment);
    }

    // Delete an appointment by ID
    public void deleteAppointment(String appointmentId) {
        if (!appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException("Appointment ID not found.");
        }
        appointments.remove(appointmentId);
    }

    // Update an existing appointment's description
    public void updateAppointment(String appointmentId, String description) {
        Appointment appointment = appointments.get(appointmentId);

        if (appointment == null) {
            throw new IllegalArgumentException("Appointment ID not found.");
        }

        // Update description with validation
        if (description != null && !description.isBlank()) {
            appointment.setDescription(description);
        }
    }

    // Retrieve all appointments (optional utility method)
    public Map<String, Appointment> getAllAppointments() {
        return new HashMap<>(appointments);
    }
}

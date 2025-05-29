package com.quantumsoft.insurance.servicei;

import com.quantumsoft.insurance.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment bookAppointment(Appointment appointment);
    Appointment confirmAppointment(Long appointmentId, String consultationLink);
    List<Appointment> getAppointmentsByUserId(Long userId);
}

package com.quantumsoft.insurance.controller;

import com.quantumsoft.insurance.entity.Appointment;
import com.quantumsoft.insurance.exception.UnAuthorizedAccessException;
import com.quantumsoft.insurance.servicei.AppointmentService;
import com.quantumsoft.insurance.serviceimpl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin("*")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/book")
    public Appointment bookAppointment(@RequestBody Appointment appointment) {
        return appointmentService.bookAppointment(appointment);
    }

    @PutMapping("/confirm/{id}")
    public Appointment confirmAppointment(@PathVariable Long id, @RequestParam String consultationLink, @RequestHeader("Authorization")String bearerToken) {
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("HOSPITALADMIN"))
            return appointmentService.confirmAppointment(id, consultationLink);
        else
            throw new UnAuthorizedAccessException("Unauthorized Access");
    }

    @GetMapping("/user/{userId}")
    public List<Appointment> getAppointmentsByUserId(@PathVariable Long userId) {
        return appointmentService.getAppointmentsByUserId(userId);
    }
}

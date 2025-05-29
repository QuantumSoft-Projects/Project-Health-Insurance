package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.entity.Appointment;
import com.quantumsoft.insurance.entity.Doctor;
import com.quantumsoft.insurance.entity.Teleconsultation;
import com.quantumsoft.insurance.entity.Users;
import com.quantumsoft.insurance.enums.AppointmentStatus;
import com.quantumsoft.insurance.enums.PremiumPlan;
import com.quantumsoft.insurance.exception.ResourceNotFoundException;
import com.quantumsoft.insurance.repository.AppointmentRepository;
import com.quantumsoft.insurance.repository.DoctorRepository;
import com.quantumsoft.insurance.repository.TeleconsultationRepository;
import com.quantumsoft.insurance.repository.UserRepository;
import com.quantumsoft.insurance.servicei.AppointmentService;
import com.quantumsoft.insurance.servicei.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TeleconsultationRepository teleconsultationRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Appointment bookAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.PENDING);
        Appointment savedAppointment = appointmentRepository.save(appointment);

        Optional<Doctor> optionalDoctor = doctorRepository.findById(appointment.getDoctor().getDoctorId());
        Optional<Users> usersOptional = userRepository.findById(appointment.getUser().getUserId());

        Map<String, String> notification = new HashMap<>();
        notification.put("message", "Hi " + usersOptional.get().getFullName() + ", your appointment with " + optionalDoctor.get().getName() + " is fixed at " + appointment.getAppointmentDateTime());
        messagingTemplate.convertAndSend("/topic/notifications", notification);

        return savedAppointment;
    }

    @Override
    public Appointment confirmAppointment(Long appointmentId, String consultationLink) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + appointmentId));

        appointment.setStatus(AppointmentStatus.CONFIRMED);
        appointmentRepository.save(appointment);


        Teleconsultation tele = new Teleconsultation();
        tele.setConsultationLink(consultationLink);
        tele.setStartTime(appointment.getAppointmentDateTime());
        tele.setAppointment(appointment);

        teleconsultationRepository.save(tele);


        String emailContent = String.format(
                "Hi %s,\n\nYour teleconsultation with Dr. %s is confirmed.\n\nJoin here: %s\n\nDate & Time: %s\n\nRegards,\nHealth Insurance Portal",
                appointment.getUser().getFullName(),
                appointment.getDoctor().getName(),
                consultationLink,
                appointment.getAppointmentDateTime()
        );

        emailService.sendSimpleMessage(appointment.getUser().getEmail(), "Teleconsultation Confirmed", emailContent);
        return appointment;
    }



    @Override
    public List<Appointment> getAppointmentsByUserId(Long userId) {
        return appointmentRepository.findByUser_UserId(userId);
    }
}

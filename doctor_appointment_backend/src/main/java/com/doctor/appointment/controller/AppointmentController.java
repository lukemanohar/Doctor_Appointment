package com.doctor.appointment.controller;

import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.model.Doctor;
import com.doctor.appointment.model.Patient;
import com.doctor.appointment.repository.AppointmentRepository;
import com.doctor.appointment.repository.DoctorRepository;
import com.doctor.appointment.repository.PatientRepository;
import com.doctor.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

   private final AppointmentService appointmentService;

   public AppointmentController(AppointmentService appointmentService){

       this.appointmentService = appointmentService;

   }

    @GetMapping
    public List<Appointment> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }
    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentForDoctor(@PathVariable Long doctorId){
      return appointmentService.getAppointmentForDoctor(doctorId);
    }
    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentForPatient(@PathVariable Long patientId){
       return appointmentService.getAppointmentForPatient(patientId);
    }


    @PostMapping("/book/{patientId}/{doctorId}")
    public Appointment bookAppointment(@PathVariable Long patientId, @PathVariable Long doctorId, @RequestBody Appointment appointment){
       return appointmentService.bookAppointment(patientId, doctorId, appointment);
    }

    @DeleteMapping("/{id}")
    public String cancelAppointment(@PathVariable Long id){
      appointmentService.cancelAppointment(id);
      return "Appointment cancelled successfully";
    }
}

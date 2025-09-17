package com.doctor.appointment.service;

import com.doctor.appointment.model.Appointment;
import com.doctor.appointment.model.Patient;
import com.doctor.appointment.model.Doctor;
import com.doctor.appointment.repository.AppointmentRepository;
import com.doctor.appointment.repository.DoctorRepository;
import com.doctor.appointment.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository, DoctorRepository doctorRepository){
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Appointment> getAllAppointments(){
       return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentForDoctor(Long doctorId){
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> getAppointmentForPatient(Long patientId){
        return appointmentRepository.findByPatientId(patientId);
    }

    public Appointment bookAppointment(Long patientId, Long doctorId, Appointment appointment){
        Patient patient = patientRepository.findById(patientId).orElseThrow(()->new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()->new RuntimeException("Doctor not found"));

        boolean doctorAlreadyBooked = appointmentRepository.existsByDoctorAndAppointmentTime(doctor, appointment.getAppointmentTime());
        if(doctorAlreadyBooked){
            throw new RuntimeException("Doctor is already booked at this time. Please chose another slot");
        }
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        return appointmentRepository.save(appointment);
    }

    public void cancelAppointment(Long id){
        appointmentRepository.deleteById(id);
    }
}

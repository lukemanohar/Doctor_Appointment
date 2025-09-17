package com.doctor.appointment.service;

import com.doctor.appointment.model.Doctor;
import com.doctor.appointment.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor addDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public Doctor getDoctorById(Long id){
        return doctorRepository.findById(id).orElseThrow(()-> new RuntimeException("Doctor not found"));
    }

    public Doctor updateDoctor(Long id, Doctor doctorDetails){
        Doctor doctor = getDoctorById(id);
        doctor.setName(doctorDetails.getName());
        doctor.setSpecialisation(doctorDetails.getSpecialisation());
        doctor.setPhone(doctorDetails.getPhone());
        doctor.setEmail(doctorDetails.getEmail());
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id){
         doctorRepository.deleteById(id);
    }

}

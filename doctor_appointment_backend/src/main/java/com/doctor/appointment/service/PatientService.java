package com.doctor.appointment.service;

import com.doctor.appointment.model.Patient;
import com.doctor.appointment.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients(){
       return patientRepository.findAll();
    }

    public Patient addPatient(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient getPatientById(Long id){
        return patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Patient not found"));
    }

    public Patient updatePatient(Long id, Patient patientDetails){
        Patient patient = getPatientById(id);
        patient.setName(patientDetails.getName());
        patient.setAge(patientDetails.getAge());
        patient.setEmail(patientDetails.getEmail());
        patient.setPhone(patientDetails.getPhone());
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id){
         patientRepository.deleteById(id);
    }

}

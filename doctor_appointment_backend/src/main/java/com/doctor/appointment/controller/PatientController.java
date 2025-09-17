package com.doctor.appointment.controller;

import com.doctor.appointment.model.Doctor;
import com.doctor.appointment.model.Patient;
import com.doctor.appointment.repository.PatientRepository;
import com.doctor.appointment.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;



    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Patient loginRequest) {
        Patient patient = patientRepository.findByEmail(loginRequest.getEmail());

        if (patient == null || !patient.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }

        return ResponseEntity.ok(patient);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Patient patient) {
        Patient existingPatient = patientRepository.findByEmail(patient.getEmail());
        if (existingPatient != null) {
            return ResponseEntity.badRequest().body("Email already registered. Please login.");
        }

        Patient savedPatient = patientRepository.save(patient);
        return ResponseEntity.ok(savedPatient);
    }

    @GetMapping
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @PostMapping
    public Patient addPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient){
        return patientService.updatePatient(id,patient);
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return "Patient Deleted Successfully";
    }
}

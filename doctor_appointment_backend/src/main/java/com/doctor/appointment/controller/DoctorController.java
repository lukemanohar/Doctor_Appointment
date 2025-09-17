package com.doctor.appointment.controller;

import com.doctor.appointment.service.DoctorService;
import com.doctor.appointment.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor){
        return doctorService.addDoctor(doctor);
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id){
        return doctorService.getDoctorById(id);
    }
    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctorDetails){
        return doctorService.updateDoctor(id, doctorDetails);
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable Long id){
         doctorService.deleteDoctor(id);
         return "Doctor deleted successfully";

    }
}

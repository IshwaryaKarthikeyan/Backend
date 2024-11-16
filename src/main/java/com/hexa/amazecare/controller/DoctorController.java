package com.hexa.amazecare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexa.amazecare.dto.DoctorDTO;
import com.hexa.amazecare.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Get doctor by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        DoctorDTO doctorDTO = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctorDTO);
    }
    
    @GetMapping("/get-user/{id}")
    public ResponseEntity<DoctorDTO> getDoctorByUserId(@PathVariable Long id) {
        DoctorDTO doctorDTO = doctorService.getDoctorByUserId(id);
        return ResponseEntity.ok(doctorDTO);
    }
    
    @GetMapping("/get-specialty/{specialty}") // Ensure this matches with the path variable in the method
    public ResponseEntity<List<DoctorDTO>> getDoctorBySpecialty(@PathVariable("specialty") String specialty) {
        List<DoctorDTO> doctors = doctorService.getDoctorBySpecialty(specialty);
        return ResponseEntity.ok(doctors);
    }


    // Endpoint to get all doctors
    @GetMapping("/get")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    // Create a new doctor
    @PostMapping("/add")
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        DoctorDTO newDoctor = doctorService.createDoctor(doctorDTO);
        return ResponseEntity.ok(newDoctor);
    }

    // Update an existing doctor
    @PutMapping("update/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO) {
        DoctorDTO updatedDoctor = doctorService.updateDoctor(id, doctorDTO);
        return ResponseEntity.ok(updatedDoctor);
    }

    // Delete a doctor by ID
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
    
 // Endpoint to retrieve all specialties
    @GetMapping("/specialties")
    public ResponseEntity<List<String>> getAllSpecialties() {
        List<String> specialties = doctorService.getAllSpecialties();
        return ResponseEntity.ok(specialties);
    }
}

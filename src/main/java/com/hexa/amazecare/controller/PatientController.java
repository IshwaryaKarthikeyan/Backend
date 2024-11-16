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
import org.springframework.web.bind.annotation.RestController;

import com.hexa.amazecare.dto.PatientDTO;
import com.hexa.amazecare.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;
    
    @GetMapping("/get")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> Patients = patientService.getAllpatients();
        return ResponseEntity.ok(Patients);
    }
    
    @GetMapping("get/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }
    
    @GetMapping("getuser/{id}")
    public ResponseEntity<PatientDTO> getPatientByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientByUserId(id));
    }

    @PostMapping("/add")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(patientService.createPatient(patientDTO));
    }
    
    @PutMapping("update/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody PatientDTO PatientDTO) {
        PatientDTO updatedPatient = patientService.updatePatient(id, PatientDTO);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
    
}

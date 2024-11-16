package com.hexa.amazecare.controller;

import com.hexa.amazecare.dto.MedicalRecordDTO;
import com.hexa.amazecare.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    // Retrieve all medical records for a specific patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecordDTO>> getMedicalRecordsByPatientId(@PathVariable Long patientId) {
        List<MedicalRecordDTO> medicalRecords = medicalRecordService.getMedicalRecordsByPatientId(patientId);
        return ResponseEntity.ok(medicalRecords);
    }
    
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<MedicalRecordDTO> getMedicalRecordsByAppointmentId(@PathVariable Long appointmentId) {
        try {
            MedicalRecordDTO medicalRecordDTO = medicalRecordService.getMedicalRecordsByAppointmentId(appointmentId);
            return ResponseEntity.ok(medicalRecordDTO);
        } catch (IllegalArgumentException ex) {
            // Return a 404 if the medical record is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    // Retrieve a specific medical record by its ID
    @GetMapping("/get/{recordId}")
    public ResponseEntity<MedicalRecordDTO> getMedicalRecordById(@PathVariable Long recordId) {
        MedicalRecordDTO medicalRecord = medicalRecordService.getMedicalRecordById(recordId);
        return ResponseEntity.ok(medicalRecord);
    }

    // Add a new medical record for a patient
    @PostMapping("/add/patient/{patientId}")
    public ResponseEntity<MedicalRecordDTO> addMedicalRecord(@PathVariable Long patientId,
                                                             @RequestBody MedicalRecordDTO medicalRecordDTO) {
        MedicalRecordDTO newMedicalRecord = medicalRecordService.addMedicalRecord(patientId, medicalRecordDTO);
        return ResponseEntity.ok(newMedicalRecord);
    }
    
    // Update an existing medical record
    @PutMapping("/update/{recordId}")
    public ResponseEntity<MedicalRecordDTO> updateMedicalRecord(@PathVariable Long recordId,
                                                                @RequestBody MedicalRecordDTO medicalRecordDTO) {
        MedicalRecordDTO updatedMedicalRecord = medicalRecordService.updateMedicalRecord(recordId, medicalRecordDTO);
        return ResponseEntity.ok(updatedMedicalRecord);
    }

    // Delete a medical record by its ID
    @DeleteMapping("/delete/{recordId}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long recordId) {
        medicalRecordService.deleteMedicalRecord(recordId);
        return ResponseEntity.noContent().build();
    }
}

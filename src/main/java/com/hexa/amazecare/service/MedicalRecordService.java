package com.hexa.amazecare.service;

import com.hexa.amazecare.dto.MedicalRecordDTO;
import com.hexa.amazecare.entity.Appointment;
import com.hexa.amazecare.entity.MedicalRecord;
import com.hexa.amazecare.entity.Patient;
import com.hexa.amazecare.repository.AppointmentRepository;
import com.hexa.amazecare.repository.MedicalRecordRepository;
import com.hexa.amazecare.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Retrieve all medical records for a specific patient
    public List<MedicalRecordDTO> getMedicalRecordsByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        List<MedicalRecord> medicalRecords = patient.getMedicalRecords();
        return medicalRecords.stream()
                .map(record -> modelMapper.map(record, MedicalRecordDTO.class))
                .collect(Collectors.toList());
    }

    // Retrieve a specific medical record by its ID
    public MedicalRecordDTO getMedicalRecordById(Long recordId) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Medical record not found"));
        return modelMapper.map(medicalRecord, MedicalRecordDTO.class);
    }

    // Add a new medical record for a specific appointment
    public MedicalRecordDTO addMedicalRecord(Long patientId, MedicalRecordDTO medicalRecordDTO) {
        // Retrieve the appointment using the appointmentId from the DTO
        Appointment appointment = appointmentRepository.findById(medicalRecordDTO.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // Ensure the appointment belongs to the specified patient
        if (!appointment.getPatient().getId().equals(patientId)) {
            throw new RuntimeException("The appointment does not belong to the specified patient.");
        }

        // Create a new MedicalRecord and set the relevant fields
        MedicalRecord medicalRecord = modelMapper.map(medicalRecordDTO, MedicalRecord.class);
        medicalRecord.setAppointment(appointment);
        medicalRecord.setPatient(appointment.getPatient());

        // Save the new medical record
        medicalRecord = medicalRecordRepository.save(medicalRecord);

        return modelMapper.map(medicalRecord, MedicalRecordDTO.class);
    }

    // Update an existing medical record
    public MedicalRecordDTO updateMedicalRecord(Long recordId, MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord existingRecord = medicalRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Medical record not found"));

        // Update the fields of the existing medical record
        existingRecord.setCurrentSymptoms(medicalRecordDTO.getCurrentSymptoms());
        existingRecord.setPhysicalExamination(medicalRecordDTO.getPhysicalExamination());
        existingRecord.setTreatmentPlan(medicalRecordDTO.getTreatmentPlan());
        existingRecord.setRecommendedTests(medicalRecordDTO.getRecommendedTests());
        existingRecord.setPrescribedMedications(medicalRecordDTO.getPrescribedMedications());

        // Save the updated medical record
        existingRecord = medicalRecordRepository.save(existingRecord);

        return modelMapper.map(existingRecord, MedicalRecordDTO.class);
    }

    // Delete a medical record by its ID
    public void deleteMedicalRecord(Long recordId) {
        if (!medicalRecordRepository.existsById(recordId)) {
            throw new RuntimeException("Medical record not found");
        }
        medicalRecordRepository.deleteById(recordId);
    }

    public MedicalRecordDTO getMedicalRecordsByAppointmentId(Long appointmentId) {
        MedicalRecord medicalRecord = medicalRecordRepository.findByAppointmentId(appointmentId);
        
        // Check if the medical record exists
        if (medicalRecord == null) {
            throw new IllegalArgumentException("Medical record not found for appointment ID: " + appointmentId);
        }

        // Map the MedicalRecord to MedicalRecordDTO if record exists
        return modelMapper.map(medicalRecord, MedicalRecordDTO.class);
    }
}

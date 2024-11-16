package com.hexa.amazecare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexa.amazecare.dto.PatientDTO;
import com.hexa.amazecare.entity.GenUser;
import com.hexa.amazecare.entity.Patient;
import com.hexa.amazecare.exception.PatientNotFoundException;
import com.hexa.amazecare.repository.AppointmentRepository;
import com.hexa.amazecare.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);


    @Autowired
    private ModelMapper modelMapper;

    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return modelMapper.map(patient, PatientDTO.class);
    }

    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        patient.getUser().setPassword(passwordEncoder.encode(patientDTO.getUser().getPassword()));
        patient = patientRepository.save(patient);
        return modelMapper.map(patient, PatientDTO.class);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        // Fetch the existing patient
        Patient existingPatient = patientRepository.findById(id)
            .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + id + " not found"));

        // Fetch the existing user (linked to the patient)
        GenUser existingUser = existingPatient.getUser();

        // Update the user fields from the DTO
        existingUser.setEmail(patientDTO.getUser().getEmail());
        existingUser.setPassword(passwordEncoder.encode(patientDTO.getUser().getPassword()));
        existingUser.setRole(patientDTO.getUser().getRole());

        // Update the patient fields using ModelMapper (excluding the user)
        existingPatient.setFullName(patientDTO.getFullName());
        existingPatient.setDateOfBirth(patientDTO.getDateOfBirth());
        existingPatient.setGender(patientDTO.getGender());
        existingPatient.setContactNumber(patientDTO.getContactNumber());

        // Save the updated patient
        existingPatient = patientRepository.save(existingPatient);

        // Return the updated patient as DTO
        return modelMapper.map(existingPatient, PatientDTO.class);
    }


    public List<PatientDTO> getAllpatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
            .map(doctor -> modelMapper.map(doctor, PatientDTO.class))
            .collect(Collectors.toList());
    }

	public PatientDTO getPatientByUserId(Long id) {
        Patient patient = patientRepository.findByUserId(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return modelMapper.map(patient, PatientDTO.class);
		
	}
    
}


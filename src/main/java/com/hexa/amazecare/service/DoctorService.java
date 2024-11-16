package com.hexa.amazecare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexa.amazecare.dto.DoctorDTO;
import com.hexa.amazecare.entity.Doctor;
import com.hexa.amazecare.entity.GenUser;
import com.hexa.amazecare.exception.DoctorNotFoundException;
import com.hexa.amazecare.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    // Get a doctor by ID
    public DoctorDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID " + id + " not found"));
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    // Get all doctors
    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream()
            .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
            .collect(Collectors.toList());
    }

    // Create a new doctor
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
        doctor.getUser().setPassword(passwordEncoder.encode(doctorDTO.getUser().getPassword()));
        doctor = doctorRepository.save(doctor);
        return modelMapper.map(doctor, DoctorDTO.class);
    }

    public DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO) {
        // Fetch the existing doctor
        Doctor existingDoctor = doctorRepository.findById(id)
            .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID " + id + " not found"));

        // Fetch the existing user associated with the doctor
        GenUser existingUser = existingDoctor.getUser();

        // Update the user fields (email, password, role) from the DTO
        existingUser.setEmail(doctorDTO.getUser().getEmail());
        existingUser.setPassword(passwordEncoder.encode(doctorDTO.getUser().getPassword()));
        existingUser.setRole(doctorDTO.getUser().getRole());

        // Update the doctor's fields using ModelMapper or direct field assignment
        existingDoctor.setFullName(doctorDTO.getFullName());
        existingDoctor.setSpecialty(doctorDTO.getSpecialty());
        existingDoctor.setExperience(doctorDTO.getExperience());
        existingDoctor.setQualification(doctorDTO.getQualification());

        // Save the updated doctor
        existingDoctor = doctorRepository.save(existingDoctor);

        // Return the updated doctor as DTO
        return modelMapper.map(existingDoctor, DoctorDTO.class);
    }


    // Delete a doctor by ID
    public void deleteDoctor(Long id) {
        Doctor existingDoctor = doctorRepository.findById(id)
            .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID " + id + " not found"));
        doctorRepository.delete(existingDoctor);
    }
    
    //Retrieve List of Specialties
    public List<String> getAllSpecialties() {
        return doctorRepository.findDistinctSpecialties();
    }

    //Retrieve Doctors by Specialty
	public List<DoctorDTO> getDoctorBySpecialty(String specialty) {
		List<Doctor> doctors = doctorRepository.findAllBySpecialty(specialty);
        return doctors.stream()
            .map(doctor -> modelMapper.map(doctor, DoctorDTO.class))
            .collect(Collectors.toList());
	}

	public DoctorDTO getDoctorByUserId(Long id) {
        Doctor doctor = doctorRepository.findByUserId(id)
        .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID " + id + " not found"));
        return modelMapper.map(doctor, DoctorDTO.class);
	}
	
	
}
	
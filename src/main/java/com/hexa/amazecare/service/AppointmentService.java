package com.hexa.amazecare.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexa.amazecare.dto.AppointmentDTO;
import com.hexa.amazecare.entity.Appointment;
import com.hexa.amazecare.entity.Doctor;
import com.hexa.amazecare.entity.Patient;
import com.hexa.amazecare.exception.AppointmentNotFoundException;
import com.hexa.amazecare.exception.DoctorNotFoundException;
import com.hexa.amazecare.exception.PatientNotFoundException;
import com.hexa.amazecare.repository.AppointmentRepository;
import com.hexa.amazecare.repository.DoctorRepository;
import com.hexa.amazecare.repository.PatientRepository;

@Service
public class AppointmentService {
	 @Autowired
	    private AppointmentRepository appointmentRepository;
	 	private PatientRepository patientRepository;
	 	private DoctorRepository doctorRepository;

	    @Autowired
	    private ModelMapper modelMapper;
	    
		public List<AppointmentDTO> getAllAppointments() {
			List<Appointment> appointments = appointmentRepository.findAll();
	        return appointments.stream().map(appointment -> modelMapper.map(appointment, AppointmentDTO.class)) .collect(Collectors.toList());
		}
		
		public AppointmentDTO getAppointmentsById(Long id) {
		    Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

		    return optionalAppointment
		        .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
		        .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + id));
		}


	    public List<AppointmentDTO> getAppointmentsByDoctorId(Long doctorId) {
	        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
	        return appointments.stream().map(appointment -> modelMapper.map(appointment, AppointmentDTO.class)) .collect(Collectors.toList());
	    }
	        
			public List<AppointmentDTO> getAppointmentsByPatientId(Long patientId) {
				 List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
			     return appointments.stream().map(appointment -> modelMapper.map(appointment, AppointmentDTO.class)) .collect(Collectors.toList());
			} 
	    
	    public AppointmentDTO createAppointment(AppointmentDTO appointmentdto) {
	    	Appointment appointment = modelMapper.map(appointmentdto, Appointment.class);
	        appointment = appointmentRepository.save(appointment);
	        return modelMapper.map(appointment, AppointmentDTO.class);
	    }
	    
	    public AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO) {
	        // Fetch the existing appointment
	        Appointment existingAppointment = appointmentRepository.findById(id)
	            .orElseThrow(() -> new AppointmentNotFoundException("Appointment with ID " + id + " not found"));

	        // Update patient if the ID is provided
	        if (appointmentDTO.getPatientId() != null) {
	            Patient existingPatient = patientRepository.findById(appointmentDTO.getPatientId())
	                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + appointmentDTO.getPatientId() + " not found"));
	            existingAppointment.setPatient(existingPatient);
	        }

	        // Update doctor if the ID is provided
	        if (appointmentDTO.getDoctorId() != null) {
	            Doctor existingDoctor = doctorRepository.findById(appointmentDTO.getDoctorId())
	                .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID " + appointmentDTO.getDoctorId() + " not found"));
	            existingAppointment.setDoctor(existingDoctor);
	        }

	        // Update other fields of the appointment
	        existingAppointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
	        existingAppointment.setSymptoms(appointmentDTO.getSymptoms());
	        existingAppointment.setStatus(appointmentDTO.getStatus());

	        // Save the updated appointment
	        existingAppointment = appointmentRepository.save(existingAppointment);

	        // Return the updated appointment as DTO
	        return modelMapper.map(existingAppointment, AppointmentDTO.class);
	    }
	    
	    
	    public AppointmentDTO updateStatus(Long appointmentId, String status) {
	        Appointment appointment = appointmentRepository.findById(appointmentId)
	            .orElseThrow(() -> new RuntimeException("Appointment not found"));

	        appointment.setStatus(status);
	        appointment = appointmentRepository.save(appointment);

	        // Return the updated appointment as DTO
	        return modelMapper.map(appointment, AppointmentDTO.class);
	    }

	    
	    public void deleteAppointment(Long id) {
	        Appointment appointment = appointmentRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Appointment not found"));
	        appointmentRepository.delete(appointment);
	    }




}
package com.hexa.amazecare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexa.amazecare.dto.AppointmentDTO;
import com.hexa.amazecare.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    
    @GetMapping("/all")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments(){
    	return ResponseEntity.ok(appointmentService.getAllAppointments());
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long id){
    	return ResponseEntity.ok(appointmentService.getAppointmentsById(id));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorId(doctorId));
    }
    
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatientId(patientId));
    }
    
    @PostMapping("/add")
    public ResponseEntity<AppointmentDTO> createOrUpdateAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentService.createAppointment(appointmentDTO));
    }
    
    @PutMapping("update/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO ) {
        AppointmentDTO updatedAppointment = appointmentService.updateAppointment(id, appointmentDTO);
        return ResponseEntity.ok(updatedAppointment);
    }
    
    @PutMapping("/update-status/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointmentStatus(@PathVariable Long id, @RequestBody String status) {
        AppointmentDTO updatedAppointment = appointmentService.updateStatus(id, status);
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
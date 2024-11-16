package com.hexa.amazecare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexa.amazecare.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorIdAndStatus(Long doctorId, String status);
    List<Appointment> findByPatientIdAndStatus(Long patientId, String status);
	List<Appointment> findByDoctorId(Long doctorId);
	List<Appointment> findByPatientId(Long patientId);
}

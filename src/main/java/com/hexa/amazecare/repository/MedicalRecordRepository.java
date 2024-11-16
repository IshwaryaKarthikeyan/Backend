package com.hexa.amazecare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexa.amazecare.dto.MedicalRecordDTO;
import com.hexa.amazecare.entity.MedicalRecord;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

	MedicalRecord findByAppointmentId(Long appointmentId);
}

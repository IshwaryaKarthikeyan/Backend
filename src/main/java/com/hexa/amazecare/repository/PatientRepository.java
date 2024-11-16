package com.hexa.amazecare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexa.amazecare.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	Optional<Patient> findByUserId(Long id);
}

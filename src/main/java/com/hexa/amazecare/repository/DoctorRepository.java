package com.hexa.amazecare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexa.amazecare.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	List<Doctor> findAllBySpecialty(String specialty);
	
    @Query("SELECT DISTINCT d.specialty FROM Doctor d")
    List<String> findDistinctSpecialties();

	Optional<Doctor> findByUserId(Long id);
}
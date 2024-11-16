package com.hexa.amazecare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexa.amazecare.entity.GenUser;

@Repository
public interface GenUserRepository extends JpaRepository<GenUser, Long> {

	GenUser findByEmail(String email);
}

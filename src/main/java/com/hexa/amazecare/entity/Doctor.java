package com.hexa.amazecare.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private GenUser user;

    @NotBlank(message = "Full name is mandatory")
    private String fullName;

    @NotBlank(message = "Specialty is mandatory")
    private String specialty;

    @Positive(message = "Experience must be positive")
    private int experience;

    @NotBlank(message = "Qualification is mandatory")
    private String qualification;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
    
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Doctor(Long id, GenUser user, @NotBlank(message = "Full name is mandatory") String fullName,
			@NotBlank(message = "Specialty is mandatory") String specialty,
			@Positive(message = "Experience must be positive") int experience,
			@NotBlank(message = "Qualification is mandatory") String qualification, List<Appointment> appointments) {
		super();
		this.id = id;
		this.user = user;
		this.fullName = fullName;
		this.specialty = specialty;
		this.experience = experience;
		this.qualification = qualification;
		this.appointments = appointments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GenUser getUser() {
		return user;
	}

	public void setUser(GenUser user) {
		this.user = user;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

    
}
package com.hexa.amazecare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "medical_records")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;
    
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Size(max = 1000, message = "Symptoms cannot exceed 1000 characters")
    private String currentSymptoms;

    @Size(max = 1000, message = "Examination cannot exceed 1000 characters")
    private String physicalExamination;

    @Size(max = 1000, message = "Treatment plan cannot exceed 1000 characters")
    private String treatmentPlan;

    @Size(max = 1000, message = "Recommended tests cannot exceed 1000 characters")
    private String recommendedTests;

    @Size(max = 1000, message = "Prescribed medications cannot exceed 1000 characters")
    private String prescribedMedications;
    
	public MedicalRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedicalRecord(Long id, Appointment appointment,
			@Size(max = 1000, message = "Symptoms cannot exceed 1000 characters") String currentSymptoms,
			@Size(max = 1000, message = "Examination cannot exceed 1000 characters") String physicalExamination,
			@Size(max = 1000, message = "Treatment plan cannot exceed 1000 characters") String treatmentPlan,
			@Size(max = 1000, message = "Recommended tests cannot exceed 1000 characters") String recommendedTests,
			@Size(max = 1000, message = "Prescribed medications cannot exceed 1000 characters") String prescribedMedications) {
		super();
		this.id = id;
		this.appointment = appointment;
		this.currentSymptoms = currentSymptoms;
		this.physicalExamination = physicalExamination;
		this.treatmentPlan = treatmentPlan;
		this.recommendedTests = recommendedTests;
		this.prescribedMedications = prescribedMedications;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public String getCurrentSymptoms() {
		return currentSymptoms;
	}

	public void setCurrentSymptoms(String currentSymptoms) {
		this.currentSymptoms = currentSymptoms;
	}

	public String getPhysicalExamination() {
		return physicalExamination;
	}

	public void setPhysicalExamination(String physicalExamination) {
		this.physicalExamination = physicalExamination;
	}

	public String getTreatmentPlan() {
		return treatmentPlan;
	}

	public void setTreatmentPlan(String treatmentPlan) {
		this.treatmentPlan = treatmentPlan;
	}

	public String getRecommendedTests() {
		return recommendedTests;
	}

	public void setRecommendedTests(String recommendedTests) {
		this.recommendedTests = recommendedTests;
	}

	public String getPrescribedMedications() {
		return prescribedMedications;
	}

	public void setPrescribedMedications(String prescribedMedications) {
		this.prescribedMedications = prescribedMedications;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	

    	
}

package com.hexa.amazecare.dto;

public class MedicalRecordDTO {
    private Long id;
    private Long appointmentId;
    private String currentSymptoms;
    private String physicalExamination;
    private String treatmentPlan;
    private String recommendedTests;
    private String prescribedMedications;
    
	public MedicalRecordDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
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
	public MedicalRecordDTO(Long id, Long appointmentId, String currentSymptoms, String physicalExamination,
			String treatmentPlan, String recommendedTests, String prescribedMedications) {
		super();
		this.id = id;
		this.appointmentId = appointmentId;
		this.currentSymptoms = currentSymptoms;
		this.physicalExamination = physicalExamination;
		this.treatmentPlan = treatmentPlan;
		this.recommendedTests = recommendedTests;
		this.prescribedMedications = prescribedMedications;
	}

    
}

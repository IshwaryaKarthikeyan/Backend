package com.hexa.amazecare.dto;

import java.time.LocalDate;

import com.hexa.amazecare.entity.GenUser;

public class PatientDTO {
	private long id;
	 private GenUser user;
	  private String fullName;
	    private LocalDate dateOfBirth;
	    private String gender;
	    private String contactNumber;
	    
	    
		public PatientDTO(GenUser user, String fullName, LocalDate dateOfBirth, String gender, String contactNumber, long id) {
			super();
			this.user = user;
			this.id = id;
			this.fullName = fullName;
			this.dateOfBirth = dateOfBirth;
			this.gender = gender;
			this.contactNumber = contactNumber;
		}
		
		
		public PatientDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
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
		public LocalDate getDateOfBirth() {
			return dateOfBirth;
		}
		public void setDateOfBirth(LocalDate dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getContactNumber() {
			return contactNumber;
		}
		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}


		@Override
		public String toString() {
			return "Patient [patientId=" + id + ", user=" + user + ", fullName=" + fullName + ", dateOfBirth="
					+ dateOfBirth + ", gender=" + gender + ", contactNumber=" + contactNumber + "]";
		}
		
		
}
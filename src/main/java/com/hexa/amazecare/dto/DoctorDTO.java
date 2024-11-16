package com.hexa.amazecare.dto;

import com.hexa.amazecare.entity.GenUser;

public class DoctorDTO {
	  private long id;
	  private GenUser user;
	  private String fullName;
	  private String specialty;
	  private int experience;
	  private String qualification;
	  
	  
	public DoctorDTO(GenUser user, String fullName, String specialty, int experience, String qualification, long id) {
		super();
		this.user = user;
		this.id = id;
		this.fullName = fullName;
		this.specialty = specialty;
		this.experience = experience;
		this.qualification = qualification;
	}
	
	
	public DoctorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}


	public void setDoctorId(long id) {
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


	@Override
	public String toString() {
		return "Doctor [doctorId=" + id + ", user=" + user + ", fullName=" + fullName + ", specialty="
				+ specialty + ", experience=" + experience + ", qualification=" + qualification + "]";
	}
	
	
}

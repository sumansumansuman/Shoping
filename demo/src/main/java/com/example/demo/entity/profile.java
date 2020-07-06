package com.example.demo.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Embeddable
public class profile {

	@NotNull
	private String name;
	
	@NotNull
	private String email;
	
	private String mobileNo;


	private String gender;

	public profile(String name, String email, String mobileNo, String gender) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.gender = gender;
	}
	
	public profile(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "profile [name=" + name + ", email=" + email + ", mobileNo=" + mobileNo + ", gender=" + gender + "]";
	}
	
	

}

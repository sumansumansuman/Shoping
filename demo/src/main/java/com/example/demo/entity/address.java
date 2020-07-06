package com.example.demo.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class address {

	
	
	@NotNull
	private String name;
	
	@NotNull
	private String mobileNo;
	

	@NotNull
	private String pincode;
	
	@NotNull
	private String fullAddress;
	
	@NotNull
	private String city;
	
	@NotNull
	private String state;
	
	@NotNull
	private String locality;
	
	@NotNull
	private String adressType;
	
	address(){}
	

	public address(@NotNull String name, @NotNull String mobileNo, @NotNull String pincode, @NotNull String fullAddress,
			@NotNull String city, @NotNull String state, @NotNull String locality, @NotNull String adressType) {
		super();
		this.name = name;
		this.mobileNo = mobileNo;
		this.pincode = pincode;
		this.fullAddress = fullAddress;
		this.city = city;
		this.state = state;
		this.locality = locality;
		this.adressType = adressType;
	}


	@Override
	public String toString() {
		return "address [name=" + name + ", mobileNo=" + mobileNo + ", pincode=" + pincode + ", fullAddress="
				+ fullAddress + ", city=" + city + ", state=" + state + ", locality=" + locality + ", adressType="
				+ adressType + "]";
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}
	
	public String getAdressType() {
		return adressType;
	}

	public void setAdressType(String adressType) {
		this.adressType = adressType;
	}
	
	
	
}

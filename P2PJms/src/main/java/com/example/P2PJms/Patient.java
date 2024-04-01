package com.example.P2PJms;

import java.io.Serializable;


public class Patient implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	String id;
	String name;
	String insuranceProvider;
	Double amounttobepaid;
	Double copay;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInsuranceProvider() {
		return insuranceProvider;
	}
	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}
	public Double getAmounttobepaid() {
		return amounttobepaid;
	}
	public void setAmounttobepaid(Double amounttobepaid) {
		this.amounttobepaid = amounttobepaid;
	}
	public Double getCopay() {
		return copay;
	}
	public void setCopay(Double copay) {
		this.copay = copay;
	}	

}

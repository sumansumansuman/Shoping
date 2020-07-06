package com.example.demo.controller.admin;

public class FormCollection {
   private String payerId;
   private String paymentId;
public String getPayerId() {
	return payerId;
}
public void setPayerId(String payerId) {
	this.payerId = payerId;
}
public String getPaymentId() {
	return paymentId;
}
public void setPaymentId(String paymentId) {
	this.paymentId = paymentId;
}
public FormCollection(String payerId, String paymentId) {
	super();
	this.payerId = payerId;
	this.paymentId = paymentId;
}
@Override
public String toString() {
	return "FormCollection [payerId=" + payerId + ", paymentId=" + paymentId + "]";
}
   
   
   
}

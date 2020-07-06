package com.example.demo.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


@Entity
@Table(name="`order`")
public class order {
	
	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderId;
	
    @ElementCollection
	@CollectionTable(name = "order_product_list", joinColumns =@JoinColumn(name = "o_id"))
	private Set<cart> productLists=new HashSet<>();
	
	@Embedded
	private address shippingAddress;
	
	@Column(name="bill")
	private double billingAmount;
	
	@Column(name="payment_mode")
	private String paymentMode;
	
	@Column(name="status")
	private String orderStatus;
	
	@Column(name="order_date")
	private String orderDate;
	
	
	@Column(name="delievery_date")
	private String delieveryDate;
	
	order(){}
	

	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public Set<cart> getProductLists() {
		return productLists;
	}


	public void setProductLists(Set<cart> productLists) {
		this.productLists = productLists;
	}


	public address getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public double getBillingAmount() {
		return billingAmount;
	}


	public void setBillingAmount(double billingAmount) {
		this.billingAmount = billingAmount;
	}


	public String getPaymentMode() {
		return paymentMode;
	}


	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public String getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}


	public String getDelieveryDate() {
		return delieveryDate;
	}


	public void setDelieveryDate(String delieveryDate) {
		this.delieveryDate = delieveryDate;
	}
	
	public void addProduct(cart id) {
		this.productLists.add(id);
	}


	@Override
	public String toString() {
		return "order [orderId=" + orderId + ", productLists=" + productLists + ", shippingAddress=" + shippingAddress
				+ ", billingAmount=" + billingAmount + ", paymentMode=" + paymentMode + ", orderStatus=" + orderStatus
				+ ", orderDate=" + orderDate + ", delieveryDate=" + delieveryDate + "]";
	}
	
	


}

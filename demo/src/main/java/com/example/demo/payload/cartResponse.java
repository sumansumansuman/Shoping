package com.example.demo.payload;

import java.util.List;
import com.example.demo.entity.product;

public class cartResponse {
    private int quantity;
    private String size;
    private product product;
    
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}


	public cartResponse(int quantity, String size, product p) {
		super();
		this.quantity = quantity;
		this.size = size;
		this.product = p;
	}
	public product getProduct() {
		return product;
	}
	public void setProduct(product product) {
		this.product = product;
	}
	
    
}

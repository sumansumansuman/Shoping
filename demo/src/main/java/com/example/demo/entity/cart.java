package com.example.demo.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class cart {
	
   @NotBlank
   private int productId;
	
	@NotBlank
	private String size;
	
	@NotBlank
	private int quantity;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public  int getQuantity() {
		return quantity;
	}

	public void setQuantity( int quantity) {
		this.quantity = quantity;
	}
	
	public cart() {}

	public cart(@NotBlank int productId, @NotBlank String size,  @NotBlank int quantity) {
		super();
		this.productId = productId;
		this.size = size;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "cart [productId=" + productId + ", size=" + size + ", quantity=" + quantity + "]";
	}
	
	public void addQuantity() {
	   this.quantity+=1;	
	}
	
	public void removeQuantity() {
		this.quantity-=1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		result = prime * result + quantity;
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		cart other = (cart) obj;
		if (productId != other.productId)
			return false;
		if (quantity != other.quantity)
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}
	
   
	
	
}

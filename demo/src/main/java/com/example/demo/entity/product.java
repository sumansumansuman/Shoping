package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class product {
	
	public product(){
		
	}
	
	@Id
	@Column(name="product_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int productId;

	@Column(name="image")
	private String imageId;
	
	@Column(name="brand")
	private String brand;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name="price")
	private int price;

	@Column(name="discount")
	private int discount;

	@Column(name="c_type")
	private String type;

	@Column(name="size")
	private String size;

	@Column(name="gender")
	private String gender;

	@Column(name="details")
	private String details;

	@Column(name="stock")
	private int stock;



	
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type =type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "product [productId=" + productId + ", imageId=" + imageId + ", brand=" + brand + ", price=" + price
				+ ", discount=" + discount + ", type=" + type + ", size=" + size + ", Gender=" + gender + ", details="
				+ details + ", stock=" + stock + "]";
	}

	
	public static product createProduct(String[] st,String details,int i) {
		product p=new product();
		p.setImageId(String.valueOf(i));
		p.setPrice(Integer.parseInt(st[1])+(int)Math.ceil(Math.random()*1200));
		p.setDiscount(Integer.parseInt(st[2])+(int)Math.ceil(Math.random()*35));
		p.setType(st[3]);
		p.setSize(st[4]);
		p.setGender(st[5]);
		p.setDetails(details);
		p.setBrand(st[6]);
		p.setStock(Integer.parseInt(st[7])+(int)Math.ceil(Math.random()*50));
		return p;
		
		
		
	}

	
}

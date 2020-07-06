package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class category {
	
	@Id
	@Column(name="category_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
	@Column(name="image")
	private String imageId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private String gender;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="c_id")
	private List<product> products;

	category(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<product> getProducts() {
		return products;
	}

	public void setProducts(List<product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "category [id=" + id + ", imageId=" + imageId + ", name=" + name + ", gender=" + gender + ", products="
				+ products + "]";
	}
	
	public void AddProduct(product p) {
		if(products==null)products=new ArrayList<>();
		products.add(p);
	}
	
	public static category AddCtegory(String image,String name,String gender,String st[],String details,int times) {
		
		category c=new category();
		c.setGender(gender);
		c.setImageId(image);
		c.setName(name);
		for(int i=1;i<=times;i++) {
			product p=product.createProduct(st, details, i);
			c.AddProduct(p);
		}
		return c;
	}
	
}

package com.example.demo.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
@Table(name="user")
public class user {

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="email" ,column=@Column(unique=true)),
		@AttributeOverride(name = "mobileNo", column = @Column(unique=true))
	})
	private profile profile;

	@Column(name="password")
	private String password;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "user_addresses", joinColumns =@JoinColumn(name = "user_id"))
	private Set<address> addresses = new HashSet<>();

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "user_cart", joinColumns =@JoinColumn(name = "user_id"))
	private Set<cart> cart = new HashSet<>();

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "user_wishlist", joinColumns =@JoinColumn(name = "user_id"))
	private Set<String> wishlist = new HashSet<>();

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="u_id")
	private List<order> orders;
	
	public user(){}

	public user(com.example.demo.entity.profile profile, String password) {
		super();
		this.profile = profile;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public Set<address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<address> addresses) {
		this.addresses = addresses;
	}

	public Set<String> getWishlist() {
		return wishlist;
	}

	public void setWishlist(Set<String> wishlist) {
		this.wishlist = wishlist;
	}

	public List<order> getOrders() {
		return orders;
	}

	public void setOrders(List<order> orders) {
		this.orders = orders;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public profile getProfile() {
		return profile;
	}

	public void setProfile(profile profile) {
		this.profile = profile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "user [userId=" + userId + ", profile=" + profile + ", password=" + password + ", addresses=" + addresses
				+ ", cart=" + cart + ", wishlist=" + wishlist + ", orders=" + orders + "]";
	}
	
	public void addAdress(address a) {
		 Optional<address> b=this.addresses.stream().filter(o->o.getAdressType().equals(a.getAdressType())).findFirst();
		    if(b!=null)
			this.addresses.remove(b);
		    this.addresses.add(a);
	}

	public void addOrder(order o) {
		this.orders.add(o);
	}
 
	
	public Set<cart> getCart() {
		return cart;
	}

	public void setCart(Set<com.example.demo.entity.cart> cart) {
		this.cart = cart;
	}

	public void addCart(cart cart) {
		cart b=this.cart.stream().filter(o->o.getProductId()==(cart.getProductId())&&o.getSize().equals(cart.getSize())).findFirst().orElse(null);
		if(b!=null)System.out.println(this.cart.remove(b));
		System.out.println(b);
		this.cart.add(cart);
	}
	
	public void addWishlist(String id) {
	   	this.wishlist.add(id);
	}
	
	public void removeWishlist(String id) {
		this.wishlist.remove(id);
	}
	
	public void removecart(cart cart) {
		boolean b=this.cart.remove(cart);
		System.out.println("removed from cart status->"+b);
	}
	
}

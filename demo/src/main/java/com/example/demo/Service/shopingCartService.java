package com.example.demo.Service;
import com.example.demo.entity.cart;
import com.example.demo.entity.product;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.demo.entity.user;
import com.example.demo.payload.cartResponse;

public interface shopingCartService {
	
	

	void addProduct(user user, int productId, String s,int q);
	void removeProduct(user user, cart cart);
	//List<cartResponse> getCart(user u);
	//List<cartResponse> getCart(user u, List<cart> list);
	List<cartResponse> getCart(Set<cart> set);
	List<product> getWishlist(user u);
	void addProductWishlist(user u,String productId);
	void removeProductWishlist(user u,String productId);
	void updateCart(user u, cart cart, cart newCart);
		
}

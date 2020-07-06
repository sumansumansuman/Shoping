package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.productRepository;
import com.example.demo.dao.userRepository;
import com.example.demo.entity.cart;
import com.example.demo.entity.product;
import com.example.demo.entity.user;
import com.example.demo.payload.cartResponse;

@Service
@Transactional
public class shoopingcartServiceImpl implements shopingCartService {
	  
	@Autowired
	private userRepository rep;
	
	@Autowired
	private productRepository pRep;
	
	@Override
	public void addProduct(user user,int productId,String s,int q) {
		cart c=new cart(productId,s,q);
		user.addCart(c);
	}

	


	@Override
	public void removeProduct(user user,cart cart) {
	       user.removecart(cart);
	}


	@Override
	public List<cartResponse> getCart(Set<cart> list) {
		List<cartResponse> cList=new ArrayList<>();
		for(cart c:list) {
			String s=c.getSize();
			int q=c.getQuantity();
			product p=pRep.findById(c.getProductId());
			cList.add(new cartResponse(q,s,p));
		}
	     return cList;
	}


	@Override
	public List<product> getWishlist(user u) {
		List<String > list=new ArrayList<>(u.getWishlist());
		List<Integer> newList = list.stream()
				.map(s -> Integer.parseInt(s))
				.collect(Collectors.toList());
           
		return pRep.findByProductIdIn(newList);
	}




	@Override
	public void addProductWishlist(user u, String productId) {
		u.addWishlist(productId);
	}




	@Override
	public void removeProductWishlist(user u, String productId) {
		u.removeWishlist(productId);
		
	}




	@Override
	public void updateCart(user u, cart cart, cart newCart) {
		u.removecart(cart);
		u.addCart(newCart);
	}
	
   
	
	
}

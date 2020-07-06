package com.example.demo.controller.user;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.demo.entity.cart;
import javax.validation.Valid;
import com.example.demo.entity.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.shopingCartService;
import com.example.demo.dao.userRepository;
import com.example.demo.entity.address;
import com.example.demo.entity.profile;
import com.example.demo.entity.user;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.SignUpRequest;
import com.example.demo.payload.cartResponse;
import com.example.demo.util.JwtTokenUtil;

@RestController
@RequestMapping("/user/logged")
@CrossOrigin("http://localhost:3000")
public class loggedUserController {
	
	   @Autowired
	   userRepository userRepository;
	   
	   @Autowired
	   shopingCartService cartService;
	   

	    @Autowired
	    private JwtTokenUtil jwtTokenUtil;

	 
	
	@GetMapping("/profile")
	public ResponseEntity<?> getProfile(Principal user,@RequestParam(name="auth_token",required=false) String token ){
		
		user u=userRepository.findByProfileEmail(user.getName());
		return ResponseEntity.ok(u.getProfile());
		
	}
	
	@PostMapping("/profile/update")
	public ResponseEntity<?> signup(Principal user,@Valid @RequestBody profile profile,@RequestParam(name="auth_token",required=false) String token ){
		user u=userRepository.findByProfileEmail(user.getName());
		u.setProfile(profile);
		userRepository.save(u);
		return ResponseEntity.ok( "profile updated successfully");
		
		
	}
	
	@GetMapping("/address")
	public ResponseEntity<?> getAddress(Principal user,@RequestParam(name="auth_token",required=false) String token ){
		return ResponseEntity.ok(userRepository.findByProfileEmail(user.getName()).getAddresses());
	}
	
	@PostMapping("/address/add")
	public ResponseEntity<?> adressAdd(Principal user,@RequestBody address address,@RequestParam(name="auth_token",required=false) String token){
		user u=userRepository.findByProfileEmail(user.getName());
		u.addAdress(address);
		return ResponseEntity.ok( "address updated successfully");
	}
	
	
	
	
	
	/* cart controller ahead*/
	

	
	@GetMapping("/cart")
	public ResponseEntity<?> getCart( Principal user,@RequestParam(name="auth_token",required=false) String token ){
		user u=userRepository.findByProfileEmail(user.getName());
	  return ResponseEntity.ok(cartService.getCart(u.getCart()));
	   	
	}
	
	@GetMapping("/cart/add/{productId}")
	public ResponseEntity<?> addCart( Principal user,@RequestParam(name="auth_token",required=false) String token,
			@RequestParam("q") int q,@RequestParam("s") String s,	@PathVariable int productId ){
		user u=userRepository.findByProfileEmail(user.getName());
		System.out.println("request for cart");
		cartService.addProduct(u, productId,s,q);
		return ResponseEntity.ok("product added to cart");	
	}
	
	@PostMapping("/cart/remove")
	public ResponseEntity<?> removeCart( Principal user,@RequestParam(name="auth_token",required=false) String token,@RequestBody cartResponse cart ){
		user u=userRepository.findByProfileEmail(user.getName());
		System.out.println(cart);
		cart c=new cart(cart.getProduct().getProductId(),cart.getSize(),cart.getQuantity());
	    cartService.removeProduct(u, c);
		return ResponseEntity.ok("product removed from cart ");
		
	}
	
	@PostMapping("/cart/update")
	public ResponseEntity<?> updateCart( Principal user,@RequestParam(name="auth_token",required=false) String token,@RequestBody cartResponse cart,
			@RequestParam(name="q",required=false) Integer q,@RequestParam(name="s",required=false) String s){
		user u=userRepository.findByProfileEmail(user.getName());
		cart newCart=new cart(cart.getProduct().getProductId(),cart.getSize(),cart.getQuantity());
		cart c=new cart(cart.getProduct().getProductId(),cart.getSize(),cart.getQuantity());
		if(q!=null) {
			newCart.setQuantity(q);
		}
		if(s!=null) {
			newCart.setSize(s);
		}
		if(q!=null||s!=null)
		{
			cartService.removeProduct(u, c);
			cartService.addProduct(u, newCart.getProductId(), newCart.getSize(), newCart.getQuantity());
		}
		
		return ResponseEntity.ok("product updated in cart");	
	}
	
	
	@GetMapping("/wishlist")
	public ResponseEntity<?> getWishlist( Principal user,@RequestParam(name="auth_token",required=false) String token ){
		user u=userRepository.findByProfileEmail(user.getName());
	   System.out.println("heeeeeeeeeeee");
	   return ResponseEntity.ok(cartService.getWishlist(u));	   
		
	}
	
	@GetMapping("/wishlist/add/{productId}")
	public ResponseEntity<?> addWishlist( Principal user,@RequestParam(name="auth_token",required=false) String token,
				@PathVariable String productId ){
		System.out.println("request come for wishlist");
		user u=userRepository.findByProfileEmail(user.getName());
		cartService.addProductWishlist(u, productId);
		return ResponseEntity.ok("product added to wishlist");	
	}
	
	@GetMapping("/wishlist/remove/{productId}")
	public ResponseEntity<?> removeWishlist( Principal user,@RequestParam(name="auth_token",required=false) String token,@PathVariable String productId ){
		user u=userRepository.findByProfileEmail(user.getName());
		cartService.removeProductWishlist(u, productId);
		return ResponseEntity.ok("product removed from wishlist ");
		
	}
	
	
	
	
	
	@GetMapping("/order")
	public ResponseEntity<?> order(Principal user){
		user u=userRepository.findByProfileEmail(user.getName());
		Map<String ,Object> map=new HashMap<>();
		return ResponseEntity.ok(u.getOrders());
	}
	
	@PostMapping("/order/place")
	public ResponseEntity<?> placeOrder(Principal user,@RequestBody order order){
		user u=userRepository.findByProfileEmail(user.getName());
		u.addOrder(order);
		userRepository.save(u);
		return ResponseEntity.ok("order placed");
		
	}


}

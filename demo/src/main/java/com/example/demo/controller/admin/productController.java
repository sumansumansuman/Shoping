package com.example.demo.controller.admin;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.PayPalClient;
import com.example.demo.dao.categoryRepository;
import com.example.demo.dao.productRepository;
import com.example.demo.entity.category;
import com.example.demo.entity.product;

@Controller
@RequestMapping("/server")
public class productController {
	
	@Autowired
	categoryRepository repository;
	
	@Autowired
	PayPalClient payPalClient;
	
	@Autowired
	productRepository Prepository;
	
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("product",new product());
		return "home";
	}
	
	@PostMapping("/save")
	 public String save(@RequestParam("details") String details,@RequestParam("all") String all){ 
	
	     //String image,String name,String gender,String st[],String details,int times
	
	//	  category c=category.AddCtegory("1", "skirt","Women", all.split(","), details, 14)	;
	//	  repository.save(c);
		  
	      return "redirect:/server/products";
	 }
	
	@GetMapping("/products")
	 public String products(Model model){
		model.addAttribute("products", Prepository.findAll());
		return "product";
	}

	@PostMapping("/create/payment")
	public String makepayment(@RequestParam("sum") String sum) {
	    Map<String,Object> map=payPalClient.createPayment(sum);
	    return "redirect:"+(String) map.get("redirect_url");
	
	}
	@GetMapping("cc")
	public String cc() {
		return "cc";
	}

	@GetMapping("/success")
	public String success(Model model,@RequestParam("paymentId") String paymentId,@RequestParam("PayerID") String payerId) {
	    FormCollection form =new FormCollection(payerId,paymentId); 
	    model.addAttribute("form",form);
		return "success";
	}
	
	@PostMapping("/make/payment")
	public String donepayment(@ModelAttribute("form") FormCollection form) {
		System.out.println("form      ........"+form);
	    Map<String,Object> map=payPalClient.completePayment(form.getPaymentId(), form.getPayerId());
	    System.out.print(map.get("payment"));
	    return "redirect:/server/products";
	
	}
	
	
}

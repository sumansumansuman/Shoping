package com.example.demo.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.PayPalClient;
import com.example.demo.controller.admin.FormCollection;

@RequestMapping("/secured/payment")
public class UserPaymentController {
	
	@Autowired
	PayPalClient payPalClient;
		
	@PostMapping("/create/payment")
	public ResponseEntity<?> makepayment(@RequestParam("sum") String sum) {
	    Map<String,Object> map=payPalClient.createPayment(sum);
	    return ResponseEntity.ok(map);
	
	}

	
	@PostMapping("/complete/payment")
	public ResponseEntity<?> donepayment(@ModelAttribute("form") FormCollection form) {
		System.out.println("form      ........"+form);
	    Map<String,Object> map=payPalClient.completePayment(form.getPaymentId(), form.getPayerId());
	    System.out.print(map.get("payment"));
	    return ResponseEntity.ok(map);
	
	}
	
	
	
}

package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.ProductService;
import com.example.demo.dao.productRepository;
import com.example.demo.entity.category;
import com.example.demo.entity.product;

import java.util.*;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("http://localhost:3000")
public class UserProductController {
	//
	@Autowired
	ProductService service;
	
	@Autowired
	productRepository rep;
   
	@GetMapping("/clothing/{item}")
	public List<product> search(@PathVariable("item") String item,@RequestParam(value="f",required=false) String filter) {
	
		System.out.println(filter);
		if(filter==null)System.out.println("null");
		
		else {
			List<product> p=service.genderProductrepo(item, item, filter);
			if(p!=null)return service.genderProductrepo(item, item, filter);
		}
		System.out.println("bad filter");
		return service.searchItem(item);
	}
	
	@GetMapping("/categories")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<category> categories(){
		return service.category();
	}
	
	@GetMapping("/categories/{name}")
	public category category(@PathVariable String name) {
		return service.category(name);
	}
	
	@GetMapping("/shop/{gender}")
	public List<category> GenderCategory(@PathVariable("gender") String item){
		return service.genderSearch(item);
	}
	

	@GetMapping("/shop/{gender}/{item}")
	public List<product> GenderCategoryProduct(@PathVariable("gender") String gender,@PathVariable("item") String item){
		return service.genderProduct(gender, item);
	}
	
	
	@GetMapping("/brand/{category}")
	public List<String> getBrand(@PathVariable("category") String cat){
		return rep.getBrands(cat);
	}
	
	
	
	
	
}

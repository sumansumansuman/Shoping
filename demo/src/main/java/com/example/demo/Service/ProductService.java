package com.example.demo.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.categoryRepository;
import com.example.demo.dao.productRepository;
import com.example.demo.entity.product;
import com.example.demo.entity.category;

@Service
public class ProductService {
    
	@Autowired
	productRepository repository;
	
	@Autowired
	categoryRepository catRep;
	
	public List<product> searchItem(String item){
		return repository.findByTypeOrBrand(item,item);
		
	}
	
	public product findById(int id) {
		return repository.findById(id);
	}
	
	public List<category> category(){
		return catRep.findAll();
	}
	
	public List<category> genderSearch(String s){
		return catRep.findByGenderIgnoreCase(s);
	}
	
	public List<product> genderProduct(String s,String item){
		if(catRep.findByGenderIgnoreCaseAndName(s, item)!=null)
	 return   catRep.findByGenderIgnoreCaseAndName(s, item).getProducts();
		return null;
				
	}
	
	
	public List<product> genderProductrepo(String type,String brand,String gender){
		return repository.findByTypeAndGender(type,  gender);
	}

	public category category(String name) {
		return catRep.findByName(name);
	}

}

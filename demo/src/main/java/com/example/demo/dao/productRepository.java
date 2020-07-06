package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

import com.example.demo.entity.product;

	public interface productRepository extends JpaRepository<product, Integer> {

		   product findById(int account);
		   List<product> findAll();
		   List<product> findByTypeOrBrand(String s,String st);
		   List<product> findByTypeAndGender(String type,String gender);
		   List<product> findByProductIdIn(List<Integer> id);
           @Query("select distinct p.brand from product p where p.type=:type")
		   List<String> getBrands(String type);			
}

package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.category;
import com.example.demo.entity.product;

@Repository
public interface categoryRepository extends CrudRepository<category, Integer> {

	   category findByName(String name);
	   List<category> findAll();
	   List<category> findByGenderIgnoreCase(String s);
	   category findByGenderIgnoreCaseAndName(String s,String st);
	   
		
}

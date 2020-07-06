package com.example.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.cart;
import com.example.demo.entity.user;

@Repository
public interface userRepository extends CrudRepository<user,Long> {
	
	public user findByProfileEmail(String email);
	
	public boolean existsByProfileEmail(String email);


	public boolean existsByProfileMobileNo(String mobile);
	
	//public cart findByCartProductId(int id);

}

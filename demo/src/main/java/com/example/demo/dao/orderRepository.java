package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.order;

@Repository
public interface orderRepository extends CrudRepository<order,Long> {

}

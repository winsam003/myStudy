package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Jo;


public interface JoService {

	// ** selectList
	List<Jo> selectList(); // selectList

	// ** selectOne
	Jo selectOne(int jno); // selectOne

	// ** insert, update
	Jo save(Jo Entity); // insert, update

	// ** delete
	void deleteById(int jno); // delete

}
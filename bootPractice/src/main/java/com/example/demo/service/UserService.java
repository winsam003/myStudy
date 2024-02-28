package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.UserDTO;

public interface UserService {
	
	public List<UserDTO> userList();
	
	public UserDTO userDetail(String id);
	
}

package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.UserDTO;

import lombok.AllArgsConstructor;
import mapperInterface.UserMapper;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
	
	UserMapper mapper;
	
	@Override
	public List<UserDTO> userList() {
		// TODO Auto-generated method stub
		return mapper.userList();
	}
}

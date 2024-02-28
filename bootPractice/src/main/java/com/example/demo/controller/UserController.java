package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.UserDTO;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	
	UserService service;
	
	@GetMapping("/userManagement")
	public void userManagement() {
	}
	
	@GetMapping("/userList")
	public String userList(Model model) {
		
		List<UserDTO> list = service.userList();
		log.info("check => "+list);
		
		model.addAttribute("userList", list);
		
		return "user/userList";
	}
	
}

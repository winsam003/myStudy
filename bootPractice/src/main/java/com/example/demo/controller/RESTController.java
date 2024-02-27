package com.example.demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.UserDTO;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/rest")
public class RESTController {
	
	UserService Uservice;
	
	@GetMapping("/userDetail/{id}")
	public ResponseEntity<?> userDetail(@PathVariable("id") String id, UserDTO dto){
		ResponseEntity<UserDTO> result = null;
		
		dto = Uservice.userDetail(id);
		
		if(dto != null) {
			result = ResponseEntity.status(HttpStatus.OK).body(dto);
			log.info("UserList lead 성공");
		}else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(dto);
			log.info("UserList lead 실패");
		}
		return result;
	}
	
}

package com.example.demo.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	PasswordEncoder passwordEncoder;
	
	
	//  ** login
	@PostMapping(value="/login", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> login(@RequestBody UserDTO dto, HttpSession session, HttpServletRequest request) {
		ResponseEntity<String> result = null;
		String password = dto.getPassword();
		dto = Uservice.userDetail(dto.getId());
		
		if(dto != null && passwordEncoder.matches(password, dto.getPassword())) {
			result = ResponseEntity.status(HttpStatus.OK).body("로그인 성공! "+dto.getName()+" 님 안녕하세요 반갑습니다.");
			session.setAttribute("LoginName", dto.getName());
			session.setAttribute("LoginID", dto.getId());
		}else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("로그인 실패! 패스워드 혹은 ID를 확인해주세요.");
		}
		return result;
	}// login
	
	
	// ** userDetail
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
	} // userDetail
	
}

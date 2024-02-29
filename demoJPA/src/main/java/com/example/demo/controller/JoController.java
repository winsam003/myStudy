package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.JoDTO;
import com.example.demo.entity.Jo;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping(value="/jo")
@Controller
@AllArgsConstructor
@Log4j2
public class JoController {
	
	JoService service;
	
	MemberService mservice;
	
	
	@GetMapping("/joList")
	public void jList(Model model) {
		model.addAttribute("joList", service.selectList());
	}
	
	
	@GetMapping("/joDetail")
	public String mDetail(Model model, int jno, String jCode) {
		String uri = "jo/joDetail"; // detail
		if("U".equals(jCode)) {
			uri = "jo/joUpdate";
		}
		model.addAttribute("joDetail", service.selectOne(jno));
		model.addAttribute("userDetail", mservice.findByJno(jno));
		return uri;
	} // Member Detail
	
	
	@GetMapping("/joInsertForm")
	public void jInsertForm() {
	}
	
	@GetMapping("/joInsert")
	public String jInsert(Model model, Jo entity) {
		String uri = "redirect:joList";
		
	    try {
			log.info("** Jo insert 성공 \n => "+service.save(entity));
			model.addAttribute("message", "조 등록에 성공하였습니다!");
		} catch (Exception e) {
			log.info("** Jo insert Exception => "+e.toString());
			model.addAttribute("message", "조 등록에 실패하였습니다! 재 입력 해주세요");
			uri = "jo/joInsertForm";
		}
		
		return uri;
	}
	
	
	@PostMapping("/joUpdate")
	public String joUpdate(HttpSession session, Model model, Jo entity) {
		String uri = "redirect:joList"; 
		model.addAttribute("joDetail", entity);
		
	    try {
			log.info("** Jo update 성공 \n => "+service.save(entity));
			model.addAttribute("message", "조 수정에 성공하였습니다!");
		} catch (Exception e) {
			log.info("** Jo update Exception => "+e.toString());
			model.addAttribute("message", "조 수정에 실패하였습니다! 재 입력 해주세요");
			uri = "jo/joUpdate";
		}
		
		return uri;
	}
	
	// ** Delete
	@GetMapping("/delete")
	public String delete(HttpSession session, Model model, RedirectAttributes rttr, int jno) {
		String uri = "redirect:/";
		
		try {
			service.deleteById(jno);
			session.invalidate();
			log.info("** Jo delete 성공 => "+jno);
			rttr.addFlashAttribute("message", "조 삭제 성공! 다신보지말아요 우리(～￣▽￣)～");
		} catch (Exception e) {
			log.info("** member delete 실패 => "+e.toString());
			rttr.addFlashAttribute("message", "조 삭제 실패! 관리자에게 문의하세욥");
		}
		
		return uri;
	} // Delete
	
}

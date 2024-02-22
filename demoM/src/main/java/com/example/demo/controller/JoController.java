package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.JoDTO;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

@RequestMapping(value="/jo")
@Controller
public class JoController {
	
	@Autowired(required = false)
	JoService service;
	
	@Autowired(required = false)
	MemberService mservice;
	
	
	@RequestMapping(value="/joList", method = RequestMethod.GET)
	public void jList(Model model) {
		model.addAttribute("joList", service.selectList());
	}
	
	
	@RequestMapping(value = "/joDetail", method = RequestMethod.GET)
	public String mDetail(Model model, int jno, String jCode) {
		String uri = "jo/joDetail"; // detail
		if("U".equals(jCode)) {
			uri = "jo/joUpdate";
		}
		model.addAttribute("joDetail", service.selectOne(jno));
		model.addAttribute("userDetail", mservice.selectJoList(jno));
		return uri;
	} // Member Detail
	
	
	@RequestMapping(value="/joInsertForm", method = RequestMethod.GET)
	public void jInsertForm() {
	}
	
	@RequestMapping(value="/joInsert", method = RequestMethod.GET)
	public String jInsert(Model model, JoDTO dto) {
		String uri = "redirect: joList";
		int result = service.insert(dto);
		if(result>0) {
			model.addAttribute("message","조 등록에 성공하였습니다!");
		}else if(result == -1) {
			model.addAttribute("message","중복된 조를 생성하셨습니다. 조 번호를 확인해주세요");
			uri = "jo/joInsertForm";
		}else {			
			model.addAttribute("message","조 등록에 실패하였습니다.!");
			uri = "jo/joInsertForm";
		}
		
		return uri;
	}
	
	
	@RequestMapping(value="/joUpdate", method = RequestMethod.POST)
	public String joUpdate(HttpSession session, Model model, JoDTO dto) {
		String uri = "redirect: joList"; 
		model.addAttribute("joDetail", dto);
		
		if(service.update(dto) > 0) {
			model.addAttribute("message", "정보수정에 성공하였습니다");
		}else {
			uri = "jo/joUpdate";
			model.addAttribute("message", "정보수정에 실패하였습니다! 재 입력 해주세요");
		}
		return uri;
	}
	
	// ** Delete
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpSession session, Model model, RedirectAttributes rttr, int jno) {
		String uri = "redirect:/";
		
		if(service.delete(jno)>0) {
			// 성공
			rttr.addFlashAttribute("message", "조 삭제 성공! 다신보지말아요 우리(～￣▽￣)～");
			session.invalidate();
		}else {
			// 실패
			rttr.addFlashAttribute("message", "조 삭제 실패! 관리자에게 문의하세욥");
		}
		return uri;
	} // Delete
	
}

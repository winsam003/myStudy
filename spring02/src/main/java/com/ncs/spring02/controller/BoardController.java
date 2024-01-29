package com.ncs.spring02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.service.BoardService;

import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {

	BoardService service;
	
	// ** Board List
	@GetMapping("/boardList")
	public void boardList(Model model) {
		model.addAttribute("banana", service.selectList());
	} // boardList
	
	// ** Board Detail
	@GetMapping("/detail")
	public String boardDetail(Model model, @RequestParam("jCode") String jCode, @RequestParam("seq") int seq) {
		String uri = "board/boardDetail";
		if("U".equals(jCode)) {
			uri = "board/boardUpdateForm";			
		}
		model.addAttribute("apple", service.selectOne(seq));
		return uri;
	} // boardDetail

	// ** insertForm
	@GetMapping("/insertForm")
	public void insertForm() {
	} // insertForm
	
	// ** insert
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(BoardDTO dto, Model model){
		String uri = "redirect: /spring02/board/boardList";
		
		if(service.insert(dto)>0) {
			model.addAttribute("message", "글 등록이 완료되었습니다.");
		}
		
		return uri;
	} // insert
	
	
	@GetMapping("/boardUpdateForm")
	public void boardUpdateForm() {
	} // boardUpdateForm
	
	// ** boardUpdate
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
	public String boardUpdate(RedirectAttributes rttr, BoardDTO dto, Model model) {
		String uri = "redirect:boardList";
		
		model.addAttribute("apple", dto);
		
		if(service.update(dto)>0) {
			rttr.addFlashAttribute("message", "게시글 수정이 완료되었습니다.");
		}else {
			uri = "board/boardUpdateForm";
			rttr.addFlashAttribute("message", "게시글 수정이 실패하였습니다..");
		}
		
		return uri;
	}
	
	@GetMapping("/delete")
	public String delete(int seq, RedirectAttributes rttr) {
		String uri = "redirect:home";
		
		
		if(service.delete(seq)>0) {
			rttr.addFlashAttribute("message", "게시글 삭제가 완료되었습니다.");
		}else {
			rttr.addFlashAttribute("message", "게시글 삭제가 실패하였습니다..");
		}
		
		return uri;
	}
	
	
	
	
	
} // class

package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	// ** Reply Insert
	@GetMapping("/replyInsert")
	public void replyInsert(BoardDTO dto) {
		// => 답글 처리를 하기 위해서 부모글의 root, step, indent 를 인자로 전달받은,
		// 	  이 인자에 담겨진 값의 lifeCycle은 requestScope과 동일하다
		//    그러기 때문에 view에서 response 전송 전까지는 서버(JSP)에서 사용가능하다.
		//    단, 객체명의 첫 알파벳 문자를 소문자로 해서 접근가능(BoardDTO.~~)
	}
	
	// => 메서드명과 요청명이 위의 메서드와 동일하지만 Post요청이고 인자가 다르기때문에 무관 함
	@PostMapping("/replyInsert")
	public String replyInsert(Model model, BoardDTO dto, RedirectAttributes rttr) {
		// ** 답글 등록
		String uri = "redirect: boardList";	// 성공 시 -> boardList 로 이동 // 실패 시에는 replyInsert 로 이동해서 재입력 유도
		
		// => dto 값 확인
		// 	-> id, title, content : 그대로 사용 가능
		//	-> 부모글의 root : 그대로 사용 가능
		//	-> 부모글의 step, indent : +1 해줘야 함
		// => Sql 처리
		//	-> replyInsert 처리, step, indent 값 update 처리
		dto.setStep(dto.getStep()+1);
		dto.setIndent(dto.getIndent()+1);
		
		if(service.rinsert(dto)>0) {
			rttr.addFlashAttribute("message", "답글 입력 성공!");
		}else {
			uri = "board/replyInsert";
			rttr.addFlashAttribute("message", "답글 입력 실패! 다시 입력하세요!");
		}
		
		return uri;
	} // replyInsert
	
	
	
	// ** Board List
	@GetMapping("/boardList")
	public void boardList(Model model) {
		model.addAttribute("banana", service.selectList());
	} // boardList
	
	// ** Board Detail
	// => 글보는거 요청들어오고 처리중, 글을 읽기 전 처리중
	// => 여기서 조회수 증가를 처리하고 넘어가야 함
	// 		-> loginID 와 board 의 id 가 다른경우
	@GetMapping("/detail")
	public String boardDetail(HttpSession session, Model model, @RequestParam("jCode") String jCode, @RequestParam("seq") int seq) {
		String uri = "board/boardDetail";
		if("U".equals(jCode)) {
			uri = "board/boardUpdateForm";			
		}
		
		// => 조회수 증가 : selectOne 의 결과를 보관해야 함
		BoardDTO dto = service.selectOne(seq);
		
		if(!dto.getId().equals((String)session.getAttribute("loginID"))) {
			// 조회수 증가의 조건 만족 -> 조회수 증가 코드 처리
			dto.setCnt(dto.getCnt()+1);
			service.update(dto);
		}
		
		model.addAttribute("apple", dto);
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
		String uri = "redirect: detail?jCode=A&seq="+dto.getSeq();
		
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
	public String delete(BoardDTO dto, RedirectAttributes rttr) {
		String uri = "redirect:/home";
		System.out.println("test");
		
		if(service.delete(dto)>0) {
			rttr.addFlashAttribute("message", "게시글 삭제가 완료되었습니다.");
		}else {
			rttr.addFlashAttribute("message", "게시글 삭제가 실패하였습니다..");
		}
		
		return uri;
	}
	
	
	
	
	
} // class

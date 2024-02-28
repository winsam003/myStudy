package com.example.demo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.GuestbookDTO;
import com.example.demo.domain.PageRequestDTO;
import com.example.demo.domain.PageResultDTO;
import com.example.demo.entity.Guestbook;
import com.example.demo.service.GuestbookService;

import lombok.AllArgsConstructor;

/**
 * Handles requests for the application home page.
 */
@Controller
@AllArgsConstructor
public class HomeController {
	
	GuestbookService service;
	
	@GetMapping("/home")
	   //@GetMapping(value={"/", "/home"})
	   // => void : 요청명.jsp 를 viewName 으로 처리함 (home.jsp)
	   //           그러므로 "/" 요청은 .jsp 를 viewName 으로 찾게됨(주의) 
	   // => Boot 의 매핑메서드 에서 "/" 요청은 적용안됨(무시됨) 
	   //     WebMvcConfig 의 addViewControllers 메서드로 해결
		// git
	public void home(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
	} // home
	
	
	@GetMapping("/axtestform")
	public String axTestForm() {
		return "axTest/axTestForm";
	}
		
	
	@GetMapping("/ginsert")
	public String ginsert() {
		
		GuestbookDTO dto = GuestbookDTO.builder()
				.title("JPA Insert Test")
				.content("입력이 잘되나요?")
				.writer("근정아 집중하자")
				.build();
		
		
		System.out.println("** guest Insert  => "+service.register(dto));
		
		return "redirect:/home";
	}
	
	@GetMapping("/glist")
	public String glist() {
		
		List<Guestbook> list = service.selectList();
		
		
		for(Guestbook g : list) {
			System.out.print(g+"regDate="+g.getRegDate()+"modDate="+g.getModDate());
			System.out.println();
		}
		
		return "redirect:/home";
	}
	
	
	@GetMapping("/gupdate")
	public String gupdate() {
		
		GuestbookDTO dto = GuestbookDTO.builder()
				.gno(1L)
				.title("근정아!!!!!")
				.content("근정아 졸지말자")
				.writer("winsam")
				.build();
		
		
		System.out.println("** guest update => "+service.register(dto));
		
		return "redirect:/home";
	}
	
	@GetMapping("/gdetail")
	// => 쿼리스트링으로 Test : /gdetail?gno=2
	public String gdetail(Long gno) {
		
		
		Guestbook guestbook = service.selectOne(gno);
		
		System.out.println("** gdetail 성공~!  => "+gno);
		System.out.println("** gdetail 성공~!  => "+guestbook);
		
		return "redirect:/home";
	}
	
	@GetMapping("/gdelete")
	// => 쿼리스트링으로 Test : /gdelete?gno=6
	public String gdelete(Long gno) {
		try {
			service.delete(gno);
			System.out.println("** 삭제성공~! 삭제번호 => "+gno);
		} catch (Exception e) {
			System.out.println("** gdelete 오류발생 => "+e.toString());
		}
		return "redirect:/home";
	}
	
	
	
	
	
	
	
	
	
	
	// ** JPA Paging & Sort
	@GetMapping("/gpage")
	public String gpage() {
		
		PageRequestDTO requestDTO = PageRequestDTO.builder()								// 몇개를, 몇페이지를 볼지 요청 DTO
				.page(2)
				.size(5)
				.build();
		
		PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.pageList(requestDTO);	// 요청DTO를 가지고 몇개를 보고, 몇페이지를 보는지 규칙과 그 규칙에 맞는 데이터를 resultDTO 저장
		
		System.out.println("** Page List => "+requestDTO.getPage());
		for(GuestbookDTO g:resultDTO.getDtoList())
			System.out.println(g);
		
		
		
		return "redirect:/home";
	}
	
	
	
	
	
} // class

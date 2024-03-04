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
import com.example.demo.entity.Testkey;
import com.example.demo.entity.TestkeyId;
import com.example.demo.service.GuestbookService;
import com.example.demo.service.TestkeyService;

import lombok.AllArgsConstructor;

/**
 * Handles requests for the application home page.
 */
@Controller
@AllArgsConstructor
public class HomeController {
	
	GuestbookService service;
	TestkeyService tservice;
	
	
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   // ** JPA 복합키 실습 (@IdClass 방법)
	   @GetMapping("/tinsert")
	   public String tinsert() {
	      Testkey entity = Testkey.builder()
	               .id("green")
	               .no(1)
	               .name("김그린")
	               .count(1)  // JPA save 에서는 MySql에서 정의한 default 1 적용안됨.
	               .build();
	      try {
	         tservice.save(entity);
	         System.out.println("** Testkey SAVE => "+entity);
	      } catch (Exception e) {
	         System.out.println("** SAVE Exception => "+e.toString());
	      }
	      return "redirect:home" ;
	   }
	   
	   // => Update
	   @GetMapping("/tupdate")
	   public String tupdate() {
	      // => Test Data 작성
	      String id="green";
	      int no=1;
	      int count=10;
	      try {
	         tservice.updateCount(id, no, count);
	         System.out.println("** Testkey Update count값 누적=> "+id+no+", "+count);
	      } catch (Exception e) {
	         System.out.println("** UPDATE Exception => "+e.toString());
	      }
	      return "redirect:home" ;
	   }
	   
	   //=> DUPLICATE KEY UPDATE (장바구니 응용)
	   //   없으면 Save 있으면 Update
	   @GetMapping("/tdupupdate")
	   public String tdupupdate() {
	      // => Test Data 작성
	      String id="banana";
	      int no=2;
	      String name="바나나";
	      int count=1;
	      try {
	         tservice.dupUpdateCount(id, no, name, count);
	         System.out.println("** Testkey Update count값 누적=> "+id+no+", "+count);
	      } catch (Exception e) {
	         System.out.println("** DupUpdate Exception => "+e.toString());
	      }
	      return "redirect:home" ;
	   }
	   
	   // ** default 메서드 활용 update
	   @GetMapping("/tcalcCount")
	   public String tcalcCount() {
	      // => Test Data 작성
	      String id="green";
	      int no=1;
	      int count=10;
	      try {
	         tservice.calcCount(id, no, count);
	         System.out.println("** calcCount count+no+100 => "+id+no+", "+count);
	      } catch (Exception e) {
	         System.out.println("** calcCount Exception => "+e.toString());
	      }
	      return "redirect:home" ;
	   }
	   
	   @GetMapping("/testlist")
	   public String testlist() {
	      
	       List<Testkey> list = tservice.selectList(); 
	       for ( Testkey t:list ) {
	          System.out.println(t);
	       }
	      return "redirect:home" ;
	   }

	   @GetMapping("/tdetail")
	   // => 퀴리스트링으로 Test : /tdetail?id=apple&no=1
	   public String tdetail(TestkeyId testid) {
	      System.out.println("tdetail => "+tservice.selectOne(testid));
	      return "redirect:home" ;
	   }
	   
	   @GetMapping("/tdelete")
	   // => 퀴리스트링으로 Test : /tdelete?id=green&no=1
	   public String tdelete(TestkeyId testid) {
	      try {
	         tservice.delete(testid);
	         System.out.println("** tdelete 삭제성공 **");
	      } catch (Exception e) {
	         System.out.println("** tdelete Exception => "+e.toString());
	      }
	      return "redirect:home" ;
	   }
	   
	   // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	
	
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
				.title("JPA는 굉장히")
				.content("어려운듯 아닌듯")
				.writer("쉽습니다.")
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
				.gno(82L)
				.title("JPA는 굉장히")
				.content("어려운듯 아닌듯")
				.writer("MyBatis 보다 쉬워요~!.")
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
	// => 쿼리스트링으로 Test : /gdelete?gno=82
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

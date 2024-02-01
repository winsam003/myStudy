package com.ncs.spring02.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired(required = false)
	MemberService service;
	
	// ** ID 중복확인
	@GetMapping("/idDupCheck")
	public void idDupCheck(@RequestParam("id") String id, Model model) {
		// 1) newID 존재여부 확인 & 결과처리
		if(service.selectOne(id) != null) {
			// => 사용 불가능
			model.addAttribute("idUse", "F");
		}else {
			// => 사용 가능
			model.addAttribute("idUse", "T");
		}
	}
	
	
	
	
	

	// ** login Form 출력
//	@RequestMapping(value = {"loginForm"}, method = RequestMethod.GET)
	// ver01 : return String
//	public String login(Model model) {
//		return "member/loginForm";
//	} // loginForm

	// ver02 : return void
	// ViewName이 생략됐다는 이야기임 그러면 스프링은 요청명과 똑같은 애로 뷰네임을 찾음
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public void loginForm() {
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, Model model, MemberDTO dto) {
		// 1. 요청분석
		// => requst 로 전달되는 id, password 처리:
		// 매서드 매개변수로 MemberDTO 를 정의해주면 자동 처리
		// ( Parameter name 과 일치하는 setter 를 찾아 값을 할당해줌 )
		// => 전달된 password 보관
		String password = dto.getPassword();
		String uri = "redirect:/"; // 성공 시

		// 2. 서비스 & 결과 처리
		// => id 확인
		// => 존재하면 Password 확인
		// => 성공: id, name은 session에 보관, home 으로
		// => 실패: 재로그인 유도
		dto = service.selectOne(dto.getId());

		if (dto != null && dto.getPassword().equals(password)) {
			// 성공
			session.setAttribute("loginID", dto.getId());
			session.setAttribute("loginName", dto.getName());
			session.setAttribute("loginJno", dto.getJno());
		} else {
			// 실패
			uri = "member/loginForm";
			model.addAttribute("message", "** id 또는 password 오류 !! 다시 입력해주세요. **");
		}
		return uri;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/";
	}

	// ** Member List
	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
	public void mList(Model model) {
		model.addAttribute("banana", service.selectList());
	}

	// ** Member Detail
	// => 단일 Parameter 의 경우 @RequestParam("...")활용
	@RequestMapping(value = "/memberDetail", method = RequestMethod.GET)
	public String mDetail(HttpServletRequest request, HttpSession session, Model model, @RequestParam("jCode") String jCode) {
		// 1. 요청분석
		// => id: session 에서 get
		String id = (String) session.getAttribute("loginID");
		String uri = "member/memberDetail"; // detail
		
		if("U".equals(jCode))
			uri = "member/updateForm";

		// 2. service & 결과 처리
		model.addAttribute("userDetail", service.selectOne(id));

		return uri;
	} // Member Detail
	
	
	// ** Join Form
	@RequestMapping(value = "/joinForm", method = RequestMethod.GET)
	public void joinForm() {
	} // Join Form
	
	// ** Join
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Model model, MemberDTO dto) {
		// 1. 요청분석
		// => 이전에는 한글처리, request 처리해줘야했는데
		// spring에서는 한글을 필터로, request는 매개변수(파라미터)로 자동화 됐음
		String uri = "member/loginForm";
		
		// 2. Service & and 결과
		if(service.insert(dto) > 0) {
			// 성공
			model.addAttribute("message", "회원가입에 성공하였습니다! 로그인 후 이용하세요.");
		}else {
			// 실패
			uri = "member/joinForm";
			model.addAttribute("message", "회원가입에 실패하였습니다! 재 입력 해주세요");
		}
		
		return uri;
	} // Join
	
	
	// ** Update
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpSession session, Model model, MemberDTO dto) {
		// 1. 요청분석
		// 성공: memberDetail, 실패: updateForm
		// 두 경우 모두 출력하려면 dto 객체의 값이 필요 함
		String uri = "member/memberDetail";
		model.addAttribute("userDetail", dto);
		
		// 2. Service & and 결과
		if(service.update(dto) > 0) {
			// 성공
			model.addAttribute("message", "정보수정에 성공하였습니다");
			// => name을 수정할 수도 있으므로 loginName을 수정
			session.setAttribute("loginName", dto.getName());
			session.setAttribute("loginId", dto.getId());
			
		}else {
			// 실패
			uri = "member/updateForm";
			model.addAttribute("message", "정보수정에 실패하였습니다! 재 입력 해주세요");
		}
		
		return uri;
	} // Update
	
	
	// ** Delete
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpSession session, Model model, RedirectAttributes rttr) {
		// 1. 요청분석
		// => id: session 에서 get
		// => delete & session 처리
		String id = (String)session.getAttribute("loginID");
		String uri = "redirect:/";
		
		// 2. Service & 결과 처리
		if(service.delete(id)>0) {
			// 성공
			rttr.addFlashAttribute("message", "회원탈퇴 성공! 다신보지말아요 우리(～￣▽￣)～");
			session.invalidate();
		}else {
			// 실패
			rttr.addFlashAttribute("message", "회원탈퇴 실패! 관리자에게 문의하세욥");
		}
		return uri;
	} // Delete
	
	//** Spring 의 redirect ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	//** RedirectAttributes
	//=> Redirect 할 때 파라메터를 쉽게 전달할 수 있도록 지원하며,
	//   addAttribute, addFlashAttribute, getFlashAttribute 등의 메서드가 제공됨.
	//=> addAttribute
	//   - url에 퀴리스트링으로 파라메터가 붙어 전달됨. 
	//   - 그렇기 때문에 전달된 페이지에서 파라메터가 노출됨.

	//=> addFlashAttribute
	//   - Redirect 동작이 수행되기 전에 Session에 값이 저장되고 전달 후 소멸된다.
	//   - Session을 선언해서 집어넣고 사용 후 지워주는 수고를 덜어주고,
//	      -> url에 퀴리스트링으로 붙지 않기때문에 깨끗하고 f5(새로고침)에 영향을 주지않음.  
//	      -> 주의사항 
//	         받는쪽 매핑메서드의 매개변수로 parameter를 전달받는 VO가 정의되어 있으면
//	         이 VO 생성과 관련된 500 발생 하므로 주의한다.
//	        ( Test : JoController 의 jupdate 성공시 redirect:jdetail )
//	        단, VO로 받지 않는 경우에는 url에 붙여 전달하면서 addFlashAttribute 사용가능함        

	//=> getFlashAttribute
//	      - insert 성공 후 redirect:jlist 에서 Test (JoController, 결과는 null)
//	      - 컨트롤러에서 addFlashAttribute 가 session에 보관한 값을 꺼내는것은 좀더 확인이 필요함 

	//** redirect 로 한글 parameter 전달시 한글깨짐
	//=> 한글깨짐이 발생하는경우 사용함.
	//=> url 파라메터 로 전달되는 한글값 을 위한 encoding
//	      - String message = URLEncoder.encode("~~ member 가 없네용 ~~", "UTF-8");
//	        mv.setViewName("redirect:mlist?message="+message);  
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	//** Model & ModelAndView **

	//=> Model(interface)
	//-> controller처리 후 데이터(Model) 을 담아서 반환 
	//-> 구현클래스 : ConcurrentModel, ExtendedModelMap 등.
	//-> 아래의 매핑 메서드들 처럼, ModelAndView 보다 심플한 코드작성 가능하므로 많이사용됨. 
	//   mv.setViewName("~~~~~") 하지않고 viewName 을 return 

	//=> ModelAndView (class)
	//-> controller처리 후 데이터(Model) 와 viewName 을 담아서 반환
	//-> Object -> ModelAndView
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	//** @RequestMapping
	//=> DefaultAnnotationHandlerMapping에서 컨트롤러를 선택할 때 대표적으로 사용하는 애노테이션. 
	//=> DefaultAnnotationHandlerMapping은 클래스와 메서드에 붙은 @RequestMapping 애노테이션 정보를 결합해 최종 매핑정보를 생성한다.
	//=> 기본적인 결합 방법은 클래스 레벨의 @RequestMapping을 기준으로 삼고, 
//	    메서드 레벨의 @RequestMapping으로 세분화하는 방식으로 사용된다.

	//** @RequestMapping 특징
	//=> url 당 하나의 컨트롤러에 매핑되던 다른 핸들러 매핑과 달리 메서드 단위까지 세분화하여 적용할 수 있으며,
	//   url 뿐 아니라 파라미터, 헤더 등 더욱 넓은 범위를 적용할 수 있다. 
	//=> 요청과 매핑메서드 1:1 mapping 
	//=> value="/mlist" 
	//   : 이때 호출되는 메서드명과 동일하면 value 생략가능 그러나 value 생략시 404 (확인필요함)
	//   : 해당 메서드 내에서 mv.setViewName("...."); 을 생략 
//	    또는 아래의 메서드를 사용하는 경우에는 void 로 작성 (view 를 return 하지않음) 하는 경우
//	     요청명을 viewName 으로 인식 즉, mv.setViewName("mlist") 으로 처리함.
//	    또는 return "mlist" ( 즉, mlist.jsp 를 viewName으로 인식 )

	//** @RequestMapping 속성
	//=> value : URL 패턴 ( 와일드카드 * 사용 가능 )
//	    @RequestMapping(value="/post")
//	    @RequestMapping(value="/post.*")
//	    @RequestMapping(value="/post/**/comment")
//	    @RequestMapping(value={"/post", "/P"}) : 다중매핑 가능

	//=> method 
	//   @RequestMapping(value="/post", method=RequestMethod.GET)
	//   -> url이 /post인 요청 중 GET 메서드인 경우 호출됨
	//   @RequestMapping(value="/post", method=RequestMethod.POST)
	//   -> url이 /post인 요청 중 POST 메서드인 경우 호출됨
//	      GET, POST, PUT, DELETE, OPTIONS, TRACE 총 7개의 HTTP 메서드가 정의되어 있음.
//	      ( 이들은 아래 @GetMapping ... 등으로도 좀더 간편하게 사용가능
//	        그러나 이들은 메서드 레벨에만 적용가능    )  

	//=> params : 요청 파라미터와 값으로도 구분 가능함.
	//   @RequestMapping(value="/post", params="useYn=Y")
	//   -> /post?useYn=Y 일 경우 호출됨
	//   @RequestMapping(value="/post", params="useYn!=Y")
	//   ->  not equal도 가능
	//   @RequestMapping(value="/post", parmas="useYn")
	//   > 값에 상관없이 파라미터에 useYn이 있을 경우 호출됨
	//   @RequestMapping(value="/post", params="!useYn")
	//   > 파라미터에 useYn이 없어야 호출됨
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	//** Lombok 지원 로그메시지  
	//=> @Log4j Test
	//   -> dependency 필요함 (pom.xml 확인)
	//   -> 로깅레벨 단계 준수함 ( log4j.xml 의 아래 logger Tag 의 level 확인)
//	      TRACE > DEBUG > INFO > WARN > ERROR > FATAL(치명적인)
//	      <logger name="com.ncs.green">
//	         <level value="info" />
//	      </logger>   

	//   -> Logger 사용과의 차이점 : "{}" 지원안됨 , 호출명 log
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
} // class

package com.ncs.spring02.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired(required = false)
	MemberService service;

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
	public String update(HttpSession session, Model model) {
		// 1. 요청분석
		// => id: session 에서 get
		// => delete & session 처리
		String id = (String)session.getAttribute("loginID");
		String uri = "redirect:/";
		
		// 2. Service & 결과 처리
		if(service.delete(id)>0) {
			// 성공
			model.addAttribute("message", "회원탈퇴 성공! 다신보지말아요 우리(～￣▽￣)～");
			session.invalidate();
		}else {
			// 실패
			model.addAttribute("message", "회원탈퇴 실패! 관리자에게 문의하세욥");
		}
		return uri;
	} // Delete
	

} // class

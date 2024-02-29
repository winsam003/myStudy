package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@AllArgsConstructor	// 개별적으로 autowired를 안해도 됨
@RequestMapping(value = "/member")
public class MemberController {

	MemberService service;
	
	PasswordEncoder passwordEncoder;
	
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
	
	@GetMapping("/loginForm")
	public void loginForm() {
	}

	@PostMapping("login")
	public String login(HttpSession session, Model model, Member Entity) {
		String password = Entity.getPassword();
		String uri = "redirect:/"; // 성공 시

		Entity = service.selectOne(Entity.getId());

		if(Entity != null && passwordEncoder.matches(password, Entity.getPassword())) {
			// 성공
			session.setAttribute("loginID", Entity.getId());
			session.setAttribute("loginName", Entity.getName());
			session.setAttribute("loginJno", Entity.getJno());
		} else {
			// 실패
			uri = "member/loginForm";
			model.addAttribute("message", "** id 또는 password 오류 !! 다시 입력해주세요. **");
		}
		return uri;
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/";
	}

	// ** Member List
	@GetMapping("/memberList")
	public void mList(Model model) {
		model.addAttribute("banana", service.selectList());
	}

	@GetMapping("/memberDetail")
	public String mDetail(HttpServletRequest request, HttpSession session, Model model, @RequestParam("jCode") String jCode) {
		String id = (String) session.getAttribute("loginID");
		String uri = "member/memberDetail"; // detail
		
		if("U".equals(jCode))
			uri = "member/updateForm";

		model.addAttribute("userDetail", service.selectOne(id));

		return uri;
	} // Member Detail
	
	
	// ** Join Form
	@GetMapping("/joinForm")
	public void joinForm() {
	} // Join Form
	
	// ** Join
	@PostMapping("/join")
	public String join(Model model, Member Entity, HttpServletRequest request) throws IOException {
		String uri = "member/loginForm";
		
		String realPath = request.getRealPath("/");
		System.out.println("** realPath => "+realPath);
		// 1.2) realPath 를 이용해서 물리적저장 위치 (file2) 를 확인 함
		realPath += "resources\\uploadImages\\";
		// 1.3) 폴더 만들기 (폴더가 없을수도 있음)
		File file = new File(realPath);
		if(!file.exists()) {
			// => 저장폴더가 조재하지 않는 경우 만들어 줌
			file.mkdir();
		}
	      // ** File Copy 하기 (IO Stream)
	      // => 기본이미지(basicman4.png) 가 uploadImages 폴더에 없는경우 기본폴더(images) 에서 가져오기
	      // => IO 발생: Checked Exception 처리
		File f1 = new File(realPath+"basicman4.png"); // uploadImages 폴더에 화일존재 확인을 위함
	      if ( !f1.isFile() ) { // 존재하지않는 경우
	         String basicImagePath 
	               = "E:\\Sam\\MyWork\\demoJPA\\src\\main\\webapp\\resources\\images\\basicman4.png";
	         FileInputStream fi = new FileInputStream(new File(basicImagePath));
	         // => basicImage 읽어 파일 입력바이트스트림 생성
	         FileOutputStream fo = new FileOutputStream(f1); 
	         // => 목적지 파일(realPath+"basicman4.png") 출력바이트스트림 생성  
	         FileCopyUtils.copy(fi, fo);
	      }
		
	      String file1="", file2="basicman1.jpg";
	      
	      MultipartFile uploadfilef = Entity.getUploadfilef();
	      if ( uploadfilef!=null && !uploadfilef.isEmpty() ) {
	         file1=realPath+uploadfilef.getOriginalFilename(); //저장경로(relaPath+화일명) 완성
	         uploadfilef.transferTo(new File(file1)); //해당경로에 저장(붙여넣기)
	         
	         file2 = uploadfilef.getOriginalFilename();
	      }
	      Entity.setUploadfile(file2);
	      Entity.setPassword(passwordEncoder.encode(Entity.getPassword()));
		
	      try {
			log.info("** member insert 성공 \n => "+service.save(Entity));
			model.addAttribute("message", "회원가입에 성공하였습니다! 로그인 후 이용하세요.");
		} catch (Exception e) {
			log.info("** member insert Exception => "+e.toString());
			uri = "member/joinForm";
			model.addAttribute("message", "회원가입에 실패하였습니다! 재 입력 해주세요");
			// TODO: handle exception
		}
		return uri;
	} // Join
	
	
	
	// ** Password Update (PasswordEncoder 가 추가되면서 패스워드 수정이 독립적으로 바뀜)
	@GetMapping("/pwUpdate")
	public void pwUpdate() {
		// View_name 생략
	}
	
	@PostMapping("/pwUpdate")
	public String pwUpdate(HttpSession session, Model model, Member Entity) {
		// 1) 요청분석
		// => id 값을 가지고 와야 함 -> session
		Entity.setId((String)session.getAttribute("loginID"));
		Entity.setPassword(passwordEncoder.encode(Entity.getPassword()));
		
		String uri = "member/loginForm";
		
		try {
			service.updatePassword(Entity.getId(), Entity.getPassword());
			session.invalidate();
			log.info("password 변경 성공!");
			model.addAttribute("message", "Password 수정이 완료되었습니다. 재로그인 하세요");
		} catch (Exception e) {
			log.info("password 변경 실패! =>" +e.toString());
			uri = "member/pwUpdate";
			model.addAttribute("message", "Password 수정에 실패하였습니다. 다시 입력해주세요");
		}
		
		return uri;
	}
	
	
	// ** Update
	@PostMapping("/update")
	public String update(HttpServletRequest request, HttpSession session, Model model, Member entity) throws IOException {
		String uri = "member/memberDetail";
		model.addAttribute("userDetail", entity);
		
		
		MultipartFile uploadfilef = entity.getUploadfilef();
		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			// => newImage 를 선택함
			// 1) 물리적 위치 저장(file1)
			String realPath = request.getRealPath("/");
			String file1 = "";
			
			// 2) realPath 를 이용해서 물리적저장 위치 (file1) 를 확인 함
			realPath += "resources\\uploadImages\\";
			
			File delFile = new File(realPath+entity.getUploadfile());
			if(delFile.isFile()) delFile.delete();	// file 존재 시 삭제
			
			
			// 4) newFile 저장
			file1 = realPath + uploadfilef.getOriginalFilename(); // 저장경로(relaPath+화일명) 완성
			uploadfilef.transferTo(new File(file1)); // 해당경로에 저장(붙여넣기)

			// 5) Table 저장경로 완성 (file2)
			entity.setUploadfile(uploadfilef.getOriginalFilename());
		}
		
		// 2. Service & and 결과
		
	    try {
			log.info("** member update 성공 \n => "+service.save(entity));
			model.addAttribute("message", "정보수정에 성공하였습니다!");
		} catch (Exception e) {
			log.info("** member update Exception => "+e.toString());
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
		
		try {
			service.deleteById(id);
			session.invalidate();
			log.info("** member delete 성공 => "+id);
			rttr.addFlashAttribute("message", "회원탈퇴 성공! 다신보지말아요 우리(～￣▽￣)～");
		} catch (Exception e) {
			log.info("** member delete 실패 => "+e.toString());
			rttr.addFlashAttribute("message", "회원탈퇴 실패! 관리자에게 문의하세욥");
			// TODO: handle exception
		}
		
		return uri;
	} // Delete
} // class

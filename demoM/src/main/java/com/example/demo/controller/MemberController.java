package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.example.demo.domain.MemberDTO;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pageTest.PageMaker;
import pageTest.SearchCriteria;

@Controller
@Log4j2
@AllArgsConstructor	// 개별적으로 autowired를 안해도 됨
@RequestMapping(value = "/member")
public class MemberController {

//	@Autowired(required = false)
	MemberService service;
	
//	@Autowired
	PasswordEncoder passwordEncoder;		// Democonfig에서 생성
	
	
	@GetMapping("/axPageList")
	public String axPageList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
		// 1) Criteria 처리
		cri.setSnoEno();
		String mappingName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		
		// 2) Service
		model.addAttribute("banana", service.mPageList(cri));
		
		// 3) View 처리 : PageMaker를 활용
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.totalRowsCount(cri));
		// 여기서 cri 매개변수는 현재는 필요없으나 검색같은걸 하려면 필요함
		model.addAttribute("pageMaker", pageMaker);
		
		return "axTest/axPageList";
	} // mPageList
	
	
	// ** axiMList
	@GetMapping("/axiMList")
	public String axiMList(Model model) {
		
		model.addAttribute("banana", service.selectList());
		
		return "axTest/axMemberList";
	}
	
	
	
	// ** Lombok @Log4j Test
	@GetMapping("/log4jTest")
	public String log4jTest() {
		String name = "banana";
		
		log.error("** Lombok @Log4j Test error= "+name);
		log.warn("** Lombok @Log4j Test warn= "+name);
		log.info("** Lombok @Log4j Test info= "+name);
		log.debug("** Lombok @Log4j Test debug= "+name);
		log.trace("** Lombok @Log4j Test trace= "+name);
		
		
		return "redirect:/";
	}
	
	@GetMapping("/mCheckList")
	public String mCheckList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
		
		String uri= "member/mPageList";
		String mappingName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		
		// 1) Criteria 처리
		cri.setSnoEno();
		
		// 2) Service
	    // => check 의 값을 선택하지 않은경우 check 값을 null 로 확실하게 해줘야함.
	    //    mapper 에서 명확하게 구분할수 있도록해야 정확한 저리가능  
		if(cri.getCheck() != null && cri.getCheck().length<1)
			cri.setCheck(null);
		
		model.addAttribute("banana", service.mCheckList(cri));
		
		// 3) View처리: PageMaker 이용
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.mCheckRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);
		
		return uri;
	} // mCheckList
	
	
	
	
	@GetMapping("/mPageList")
	public void mPageList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
		// 1) Criteria 처리
		cri.setSnoEno();
		String mappingName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		
		// 2) Service
		model.addAttribute("banana", service.mPageList(cri));
		
		// 3) View 처리 : PageMaker를 활용
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.totalRowsCount(cri));
		// 여기서 cri 매개변수는 현재는 필요없으나 검색같은걸 하려면 필요함
		model.addAttribute("pageMaker", pageMaker);
		
		
	} // mPageList
	
	
	
	
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
	public String login(HttpSession session, Model model, MemberDTO dto) {
		String password = dto.getPassword();
		String uri = "redirect:/"; // 성공 시

		System.out.println(dto);
		dto = service.selectOne(dto.getId());

		//if (dto != null && dto.getPassword().equals(password)) {
		if(dto != null && passwordEncoder.matches(password, dto.getPassword())) {
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    // ** *****************************************
    // ** Transaction_AOP 적용 ********************* 
    // 1. 준비: pom.xml (dependency) 확인
    // =>  AspectJ(기본제공), AspectJ Weaver(추가)
    
    // 2. servlet-context.xml AOP 설정
    
    // 3. Rollback Test
      // 3.1) Aop xml 적용전 => insert1 은 입력되고, insert2 에서  500_Dupl..Key  오류 발생
      // 3.2) Aop xml 적용후 => insert2 에서 오류발생시 모두 Rollback 되어 insert1, insert2 모두 입력 안됨 
    
      // 3.1) Transaction 적용전 : 동일자료 2번 insert
     // => 첫번째는 입력완료(commit) 되고, 두번째자료 입력시 Key중복 오류발생 (500 발생)
    // 3.2) Transaction 적용후 : 동일자료 2번 insert
     // => 첫번째는 입력완료 되고, 두번째 자료입력시 Key중복 오류발생 하지만,
     //    rollback 되어 둘다 입력 안됨
    //service.insert(dto); // Transaction_Test, insert1 
	
	// ** Join
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Model model, MemberDTO dto, HttpServletRequest request) throws IOException {
		// 1. 요청분석
		// => 이전에는 한글처리, request 처리해줘야했는데
		// spring에서는 한글을 필터로, request는 매개변수(파라미터)로 자동화 됐음
		String uri = "member/loginForm";
		
		String realPath = request.getRealPath("/");
		System.out.println("** realPath => "+realPath);
		// => E:\Sam\MyWork\demoM\src\main\webapp\
		
		
		
		
		
		// 1.2) realPath 를 이용해서 물리적저장 위치 (file2) 를 확인 함
		if(!realPath.contains("-tomcat-")) { 				// 개발중~(배포전)
			realPath += "resources\\uploadImages\\";
		} else 											// 개발끝~(배포후)
			realPath ="E:\\Sam\\IDESet\\apache-tomcat-9.0.85\\webapps\\demoM\\resources\\uploadImages\\";
		
		
		
		
		
		
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
	               = "E:\\Sam\\MyWork\\demoM\\src\\main\\webapp\\resources\\images\\basicman4.png";
	         FileInputStream fi = new FileInputStream(new File(basicImagePath));
	         // => basicImage 읽어 파일 입력바이트스트림 생성
	         FileOutputStream fo = new FileOutputStream(f1); 
	         // => 목적지 파일(realPath+"basicman4.png") 출력바이트스트림 생성  
	         FileCopyUtils.copy(fi, fo);
	      }
		
		
	      // 1.4) 저장경로 완성
	      // => 기본 이미지 저장
	      String file1="", file2="basicman1.jpg";
	      
	      MultipartFile uploadfilef = dto.getUploadfilef();
	      if ( uploadfilef!=null && !uploadfilef.isEmpty() ) {
	         // => image_File 을 선택함  
	         // 1.4.1) 물리적위치 저장 (file1)
	         file1=realPath+uploadfilef.getOriginalFilename(); //저장경로(relaPath+화일명) 완성
	         uploadfilef.transferTo(new File(file1)); //해당경로에 저장(붙여넣기)
	         
	         // 1.4.2) Table 저장경로 완성 (file2)
	         file2 = uploadfilef.getOriginalFilename();
	      }
		
	      
	      
	      
	      
		dto.setUploadfile(file2);
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		
//		service.insert(dto);			// 트랜젝션 테스트 이중 insert 코드
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ** Password Update (PasswordEncoder 가 추가되면서 패스워드 수정이 독립적으로 바뀜)
	@GetMapping("/pwUpdate")
	public void pwUpdate() {
		// View_name 생략
	}
	
	// ** PasswordUpdate
	// => Service, DAO, 모두 pwUpdate 작업해야 함
	// pwUpdate(MemberDTO dto) 로 만들어야 할듯
	// 성공: 세션 무효화 -> 재 로그인을 유도 -> 로그인창
	// 실패: 재 수정 유도 -> pwUpdate
	@PostMapping("/pwUpdate")
	public String pwUpdate(HttpSession session, Model model, MemberDTO dto) {
		// 1) 요청분석
		// => id 값을 가지고 와야 함 -> session
		dto.setId((String)session.getAttribute("loginID"));
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		String uri = "member/loginForm";
		
		// 2) 서비스 처리
		if(service.pwUpdate(dto) > 0) {
			// 성공
			session.invalidate();
			model.addAttribute("message", "Password 수정이 완료되었습니다. 재로그인 하세요");
		}else {
			// 실패
			uri = "member/pwUpdate";
			model.addAttribute("message", "Password 수정에 실패하였습니다. 다시 입력해주세요");
		}
		
		return uri;
	}
	
	
	
	
	// ** Update
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, HttpSession session, Model model, MemberDTO dto) throws IOException {
		// 1. 요청분석
		// 성공: memberDetail, 실패: updateForm
		// 두 경우 모두 출력하려면 dto 객체의 값이 필요 함
		String uri = "member/memberDetail";
		model.addAttribute("userDetail", dto);
		
		
		// ** uploadFile 처리
		// => newImage 선택 여부 확인
		// => 선택 시 -> old Image 삭제, new Image 저장 : uploadfilef 를 사용 함
		// => 미선택 시 -> oldImage가 이미 uploadfile 으로 전달되기 때문에 아무것도 안하면 됨

		// 1.4) 저장경로 완성
		// => 기본 이미지 저장

		MultipartFile uploadfilef = dto.getUploadfilef();
		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			// => newImage 를 선택함
			// 1) 물리적 위치 저장(file1)
			String realPath = request.getRealPath("/");
			String file1 = "";
			
			// 2) realPath 를 이용해서 물리적저장 위치 (file1) 를 확인 함
			if(!realPath.contains("-tomcat-"))  				// 개발중~(배포전)
				realPath += "resources\\uploadImages\\";
			else											// 배포후
				realPath ="E:\\Sam\\IDESet\\apache-tomcat-9.0.85\\webapps\\spring02\\resources\\uploadImages\\";
			
			// 3) oldFile 삭제
			// => oldFile Name : dto.getUploadfile();
			// => 삭제경로 : realPath+dto.getUploadfile();
			File delFile = new File(realPath+dto.getUploadfile());
			if(delFile.isFile()) delFile.delete();	// file 존재 시 삭제
			
			
			// 4) newFile 저장
			file1 = realPath + uploadfilef.getOriginalFilename(); // 저장경로(relaPath+화일명) 완성
			uploadfilef.transferTo(new File(file1)); // 해당경로에 저장(붙여넣기)

			// 5) Table 저장경로 완성 (file2)
			dto.setUploadfile(uploadfilef.getOriginalFilename());
		}
		
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

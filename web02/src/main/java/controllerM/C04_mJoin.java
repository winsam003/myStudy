package controllerM;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;
@WebServlet("/mjoin")
public class C04_mJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C04_mJoin() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => request 의 Parameter 처리
		// => 성공: 로그인 유도 (loginForm.jsp)
		// => 실패: 재가입 유도 (joinForm.jsp)
		request.setCharacterEncoding("UTF-8");
		
		
		// 2. 서비스 처리
		// => Service, DTO 객체 생성
		MemberService service = new MemberService();
		MemberDTO dto = new MemberDTO();
		
		dto.setId(request.getParameter("id"));
		dto.setPassword(request.getParameter("password"));
		dto.setName(request.getParameter("name"));
		dto.setAge(Integer.parseInt(request.getParameter("age")));
		dto.setJno(Integer.parseInt(request.getParameter("jno")));
		dto.setInfo(request.getParameter("info"));
		dto.setPoint(Double.parseDouble(request.getParameter("point")));
		dto.setBirthday(request.getParameter("birthday"));
		dto.setRid(request.getParameter("rid"));
		
		// 3. View (Response) : Forward
		if(service.insert(dto)>0) {
			request.setAttribute("message", "회원가입 완료!");
			request.getRequestDispatcher("/member/loginForm.jsp").forward(request, response);			
		}else {
			request.setAttribute("message", "회원가입에 실패했습니다. 정보 입력을 확인해주세요");
			request.getRequestDispatcher("/member/joinForm.jsp").forward(request, response);						
		}
		
		
		
	} // doPost

} // class

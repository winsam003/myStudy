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
@WebServlet("/mupdate")
public class C05_mUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C05_mUpdate() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => request 의 Parameter 처리
		// => 성공: mdetail로 (memberDetail.jsp)
		// => 실패: 재수정 유도 (updateForm.jsp)
		// 이때는 리 다이렉트를 해야함!!!
		// => 출력 객체가 필요함 (userDetail)
		//	 -> redirect 또는 전달된 값들을 userDetail에 저장을 하던지 할 수 있음
		request.setCharacterEncoding("UTF-8");
		
		String uri = "member/memberDetail.jsp";
		
		
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
		
		// => 결과 출력을 위해 userDetail에 저장
		request.setAttribute("userDetail", dto);
		
		
		// 3. View (Response) : Forward
		if(service.update(dto)>0) {
			request.getSession().setAttribute("name", dto.getName());
			request.getSession().setAttribute("loginID", dto.getId());
			request.setAttribute("message", "회원정보 수정 완료!");
		}else {
			uri = "member/updateForm.jsp";
			request.setAttribute("message", "회원정보 수정에 실패했습니다. 정보 입력을 확인해주세요");
		}
		
		request.getRequestDispatcher(uri).forward(request, response);						
		
		
	} // doPost

} // class

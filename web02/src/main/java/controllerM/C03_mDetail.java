package controllerM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;


@WebServlet("/mdetail")
public class C03_mDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C03_mDetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청분석
		// => 로그인한 유저의 정보 DTO 저장
		String id = (String)request.getSession().getAttribute("loginID");
		String uri = "member/memberDetail.jsp";
		// => Update 요청시에는 updateForm.jsp 로 가도록 uri 수정
		if("U".equals(request.getParameter("jCode"))) {
			uri = "member/updateForm.jsp";
		}
		
		// 2) 해당하는 service 처리
		// => Service, DTO 객체 생성
		// => 결과를 View가 출력 가능하도록 Attribute에 저장
		MemberService service = new MemberService();
		request.setAttribute("userDetail", service.selectOne(id));
		
		// 3) View (Response) : Forward
		request.getRequestDispatcher(uri).forward(request, response);
		
	} // doGet

} // class

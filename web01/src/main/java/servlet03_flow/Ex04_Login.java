package servlet03_flow;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/login")
public class Ex04_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex04_Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 요청분석
		// => 한글, request 의 Parameter 처리
		
		request.setCharacterEncoding("UTF-8");
		int sno = 0;
		if(request.getParameter("sno")!=null && request.getParameter("sno").length()>0) {
			sno = Integer.parseInt(request.getParameter("sno"));
			// 비밀번호가 숫자인지도 확인하는게 맞는데 프론트에서 다 걸러서 보냈다고 가정
			// 프로그램 효율을 위해서 프론트에서 할 수있는 무결점검사를 다 하고 보내는게 맞음
		}
		String name = request.getParameter("name");
		String uri = "home.jsp";

		// 2. Service 처리
		// => Service, StudentDTO 의 인스턴스로 처리
		// => Service의 selectOne을 이용 : sno를 확인
		// -> sno가 존재하면 name을 확인 함
		// => 완전 성공시 : index.html 으로 감
		// => 실패시 : ~LoginFrom.jsp
		StudentService service = new StudentService();
		StudentDTO dto = new StudentDTO();

		dto = service.selectOne(sno);
		HttpSession session = request.getSession();

		if (dto!=null && dto.getName().equals(name)) {
			session.setAttribute("name", dto.getName());
			session.setAttribute("sno", dto.getSno());
			request.getRequestDispatcher(uri).forward(request, response);
//			response.sendRedirect(uri);
		} else {
			uri = "servletTestForm/flowEx04_LoginForm.jsp";
			request.setAttribute("message", "잘못 입력하셨습니다. 다시 입력해주세요");
			request.getRequestDispatcher(uri).forward(request, response);
//			response.sendRedirect(uri);
		}

		// 3. View(Response) 처리 : Forward
		// => 로그인 성공: 첫 화면으로 감 index.html
		// => 로그인 실패: ~LoginFrom.jsp으로 감 (재 로그인 하도록 유도)

	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	} // doPost

}

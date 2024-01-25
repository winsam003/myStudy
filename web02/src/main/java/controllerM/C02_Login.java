package controllerM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/login")
public class C02_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C02_Login() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청분석
		// => request 의 parameter 처리
		// => id, password 처리
		MemberService service = new MemberService();
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// 2) 해당하는 service 처리
		// => Service, DTO 객체 생성
		// => id 확인 : Service의 SelectOne으로 id 확인
		// => id가 확인되면 password 일치 여부 확인
		// => 성공이면 id와 name을 session에 보관하고 home으로 감
		// => 실패이면 loginForm 으로 가서 재 로그인 유도
		
		MemberDTO dto = service.selectOne(id);
		if(dto != null && dto.getPassword().equals(password)) {
			request.getSession().setAttribute("loginID", dto.getId());
			request.getSession().setAttribute("name", dto.getName());
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "잘못 입력되었습니다. 다시 입력해주세요.");
			request.getRequestDispatcher("/member/loginForm.jsp").forward(request, response);
		}
		
		
		// 3) View (Response) : Forward
		
	} // doPost

} // class

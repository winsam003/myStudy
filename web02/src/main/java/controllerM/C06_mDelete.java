package controllerM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
@WebServlet("/mdelete")
public class C06_mDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public C06_mDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = new MemberService();
		String uri = "home.jsp";
		String id = (String)request.getSession().getAttribute("loginID");
		if(service.delete(id) > 0) {
			request.setAttribute("message", id+"님 탈퇴 성공, 1개월 후 재가입 가능합니다.");
			request.getSession().invalidate();
		}else {
			request.setAttribute("message", id+"님 탈퇴 실패! 관리자에게 문의하세요");
		}
		request.getRequestDispatcher(uri).forward(request, response);
	}

}

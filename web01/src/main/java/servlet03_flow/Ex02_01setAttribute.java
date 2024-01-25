package servlet03_flow;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/01set")
public class Ex02_01setAttribute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex02_01setAttribute() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Request 처리
		// => form 없이 쿼리스트링으로 테스트
		//	  ~~/01set?id=banana&name=홍길동&age=22 (이런 형식으로 넘어옴)
		// => 한글처리 (Post 요청시 필수)
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
//		int age = Integer.parseInt(request.getParameter("age"));
		String age = request.getParameter("age");
		//=> age는 get 처리 오류로 인하여 불편 
		
		
		System.out.println("** setAttribute Test **");
		System.out.printf("** Parameter id=%s, name=%s, age=%s\n",id, name, age);
		
		//2. setAttribute 처리
		// => 보관 가능한 객체, Scope: Page < Request < Session < Application
		
		// 2.1) request
		//      setAttribut("변수", values); 라고 볼 수 있음
		request.setAttribute("rid", id);
		request.setAttribute("rname", name);
		request.setAttribute("rage", age);
		
		// 2.2) session
		// request는 위에 인자로 있어서 그냥 썻는데 session는 없기때문에 인자를 만들어 줘야 함
		// session은 내가 개발할때 임의로 만드는 공간이 아님 누군가 접속했을때 서버(톰캣)이 만들어주는 것임
		// 그래서 session을 request로부터 전달 받아야 함
		HttpSession session = request.getSession();
		
		//      setAttribute("변수", values); 라고 볼 수 있음
		session.setAttribute("sid", id);
		session.setAttribute("sname", name);
		session.setAttribute("sage", age);
		
		// 3. 이동 후 getAttribute
		// 이동은 Forward, Redirect 방식으로 flow 함!
		
		// 3.1) Forward
		String uri = "02get";
		
//		request.getRequestDispatcher(uri).forward(request, response);
		response.sendRedirect(uri);
		
		
		
	}

}

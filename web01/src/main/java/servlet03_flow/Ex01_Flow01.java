package servlet03_flow;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.ldap.PagedResultsResponseControl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//** PageFlow
//=> 서버내에서 웹페이지(Html, Jsp) 또는 Servlet 간의 이동   
//=> 서버외 : 클라이언트의 요청으로 이동 ( a Tag , submit 등 )    
//=> 경우
//  servlet -> servlet
//  servlet <-> jsp , html
//  jsp -> jsp   

//** Forward 와 Redirect
//** Forward : 웹브라우져의 주소창이 안바뀜
//  => 현재의 요청에 대해 서버내에서 page만 이동함.
//** Redirect: 웹브라우져의 주소창이 바뀜
//  => 현재의 요청에 대해 응답 -> 재요청 -> 처리

@WebServlet("/flow01")
public class Ex01_Flow01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex01_Flow01() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Forward
		// => flow01 -> hello Forward방식 flow 해보기

		// => 출력문이 출력되지않음을 확인할 것

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<h2> ** Forward Test ** </h2>");
		// => console로 확인
		System.out.println("** Forward & Redirect Test => flow01");

		// 1.1) Forward : Servlet -> Servlet
//		String uri = "hello";

		// 1.2) Forward : Servlet -> jsp
//		uri = "servletTestForm/form04_Select.jsp";

		// Forward 명령은 request가 가지고있고 reqeustDispatcher 가 실행 함
//		RequestDispatcher dis = request.getRequestDispatcher(uri); // 목적지를 인자로 보냄
//		dis.forward(request, response);
//		request.getRequestDispatcher(uri).forward(request, response); // 이렇게 써도 됨 -> 이렇게 더 많이 씀

		// => 웹브라우져의 주소창은 바뀌지않으며, 요청명과 다른 결과가 실행됨
		// 요청명은 flow01 이지만 실행결과는 hello

		/* ==================================================== */

		// 2. Redirect (재요청 처리)
		// 첫번째 요청 flow01 에서 hellos 라는 요청을 다시 보내줄것을 웹브라우져에게 response 함.
		// 재요청으로 hellos 가 서버로 전달되어 처리하는 방식
		// => 그러므로 웹브라우져에 표시된 요청명이 hellos 로 변경됨
		// 2.1) Servlet -> Servlet
		String uri = "hello";
//		response.sendRedirect(uri);
		
		
		// 2.2) Servlet -> JSP
		uri = "servletTestForm/form04_Select.jsp";
		response.sendRedirect(uri);

		
		
		
		
		
		
		

	} // doGet

} // class

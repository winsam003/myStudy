package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/check")
public class Ex03_Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Ex03_Check() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   // => CheckBox 처리
	      //   -> 하나의 Name 에 복수개의 Value 들이 있음
	      //   -> request.getParameterValues("gift") 를 이용해서 배열로 처리   
		request.setCharacterEncoding("UTF-8");
	      response.setContentType("text/html; charset=UTF-8");
	      PrintWriter out = response.getWriter();
		
		String[] gift = request.getParameterValues("gift");
		
//		if(gift!=null && gift.length > 0) {
//			for(String v:gift) {
//				out.print(v+"<br>");
//			}			
//		}else {
//			out.print("선택사항이 없습니다.");
//		}
		
		if(gift==null) {
			out.print("선택사항이 없습니다.");
		}else {
			for(String v:gift) {
				out.print(v+"<br>");
			}						
		}
		
		
	}	// doGet

}	// class

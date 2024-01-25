package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/select")
public class Ex04_Select extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex04_Select() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String job = request.getParameter("job");
		String[] interest = request.getParameterValues("interest");
		
		if(job=="" || job==null) {
			out.print("입력한 직업이 없습니다.<br>");
		}else {
			out.print(job+"<br>");			
		}
		
		if(interest == null) {
			out.print("입력한 관심분야가 없습니다.");
		}else {
			for(String v:interest) {
				out.print(v + " ");							
			}
		}

	} // doGet

} // class

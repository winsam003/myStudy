package mvcTest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/Detail")
public class Ex02_MVC02Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex02_MVC02Detail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentService service = new StudentService();
		HttpSession session = request.getSession();
		String uri = "mvcTestJsp/ex03_MVC02Detail.jsp";
		int sno = (int) session.getAttribute("sno");
		
		StudentDTO dto = service.selectOne(sno);
		request.setAttribute("dto", dto);
		request.getRequestDispatcher(uri).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

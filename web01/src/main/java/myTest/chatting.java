package myTest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/chatting")
public class chatting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public chatting() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		String chat = request.getParameter("chat");
		
		out.print("입력: " + "<form action='/web01/chatting' method='get'>");
		out.print("<input type='text' mathod='get' name='chat'></input>");
		out.print("<input type='submit' mathod='get' name='chat'></input>");
		out.print("</form>");
		
		out.print("<br>");
		
		out.print(chat+"<br>");
		
	}


}

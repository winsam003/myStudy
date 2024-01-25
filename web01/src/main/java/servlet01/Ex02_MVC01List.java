package servlet01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/list")
public class Ex02_MVC01List extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
       
    public Ex02_MVC01List() {
        super();
    }

    // ** MVC 패턴1 StudentList 출력해보기
    // 1. 요청 Service 처리
    // 2. 결과 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// => 요청 Service 처리
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		StudentService service = new StudentService();
		list = service.selectList();
		
		
		// => 결과 출력 : 출력 내용을 Response 객체의 Body 영역에 Write
		// = 한글처리
		// = 출력객체 생성 & 출력
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		
		out.print("<table border='1'>");
		out.print("<tr>");
		out.print("<th>sno</th>");
		out.print("<th>name</th>");
		out.print("<th>age</th>");
		out.print("<th>jno</th>");
		out.print("<th>info</th>");
		out.print("<th>point</th>");
		out.print("</tr>");
		if(list!=null) {	
			// 내가 한거
//			for(int i = 0 ; i < 26 ; i++) {
//				out.print("<tr>");
//				out.print("<td>"+ list.get(i).getSno() +"</td>");
//				out.print("<td>"+ list.get(i).getName() +"</td>");
//				out.print("<td>"+ list.get(i).getAge() +"</td>");
//				out.print("<td>"+ list.get(i).getJno() +"</td>");
//				out.print("<td>"+ list.get(i).getInfo() +"</td>");
//				out.print("<td>"+ list.get(i).getPoint() +"</td>");
//				out.print("</tr>");
//			}
			// forEach 사용 (샘이 하신거)
			for(StudentDTO s:list) {
				out.print("<tr>");
				out.print("<td>"+ s.getSno() +"</td>");
				out.print("<td>"+ s.getName() +"</td>");
				out.print("<td>"+ s.getAge() +"</td>");
				out.print("<td>"+ s.getJno() +"</td>");
				out.print("<td>"+ s.getInfo() +"</td>");
				out.print("<td>"+ s.getPoint() +"</td>");
				out.print("</tr>");
			}
		}else {
			out.print("<h2> ** 출력할 Data가 없습니다. **");
		}
		
		out.print("</table>");
		out.print("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

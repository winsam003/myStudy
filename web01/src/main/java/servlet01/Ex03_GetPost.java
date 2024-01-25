package servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetPost")
public class Ex03_GetPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    public Ex03_GetPost() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) request 의 Parameter 처리
		// => 한글처리를 해줘야 함, getParameter 전에 해야함
	      //   - Tomcat(WAS) 은 Get 방식요청에서는 "UTF-8" 을 default 로 적용함 
	      //   ( html 문서에서 "UTF-8" 작성되었고 , Get 방식으로 전송되면 생략가능
	      //     단, post 방식에서는 반드시 처리해야함 ) 
		
		request.setCharacterEncoding("UTF-8");
		
	      
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		// => name 이 id 인 input Tag의 value 값으 return
		
		String password = request.getParameter("password");
		// => 해당하는 Parameter 가 없는 경우
		//    null 을 리턴함
		// => Parameter는 존재하지만 값이 없는 경우 : null 이 아님
		// http://localhost:8080/web01/getpost?id=banana&name=%EB%B0%94%EB%82%98%EB%82%98&passwaord= // <- 이런 식으로
		if(password!=null && password.length()>0) {
			System.out.println("** password => "+password.toUpperCase());
		}else {
			System.out.println("** password is null");			
		}
		
		// 컴파일러 에서는 걍 스트링 타입에 스트링형식의 데이터를 넣었을 뿐임
		// 그러니 컴파일에러는 나지 않음
		
		

		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();	// out 객체임 -> 이걸 이용해서 내가 출력하고 싶은 출력물을 만들 수 있음
		out.print("<html><body>");
		out.print("<h2 style='color:blue;'> ** Get/Post Test **</h2>");
		out.print("<h3> => 전달된 Parameter 확인 :  : </h3>");
		out.print("<h3> => id : "+ id +" </h3>");
		out.print("<h3> => name : "+ name +" </h3>");
		out.print("여기는 doGet 메서드 입니다.");
		out.print("</body></html>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

} // clsss
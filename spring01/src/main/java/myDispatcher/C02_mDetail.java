package myDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

public class C02_mDetail implements MyController{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
    	MemberService service = new MemberService();
    	request.setAttribute("userDetail", service.selectOne("bamboo7"));
        return "member/memberDetail";
    }
}

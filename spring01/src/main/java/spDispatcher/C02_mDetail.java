package spDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MemberService;

public class C02_mDetail implements Controller{
	@Autowired
	MemberService service;
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
    	
//    	MemberService service = new MemberService();
    	ModelAndView mv = new ModelAndView();
   
    	mv.addObject("userDetail", service.selectOne("bamboo7"));
    	mv.setViewName("member/memberDetail");
        return mv;
    }
}
